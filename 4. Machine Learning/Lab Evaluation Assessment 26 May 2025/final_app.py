from flask import Flask, jsonify
import pandas as pd
import numpy as np
import xgboost as xgb
import pickle
import os

app = Flask(__name__)

# Define file paths
MODEL_PATH = 'xgb_model_1.pkl'
SALES_DATA_PATH = 'merged.csv'



def train_model_if_not_exists():
    """
    Loads the daily sales data, aggregates it into monthly figures,
    creates lag features, and trains an XGBoost model.
    Then saves the trained model using pickle.
    """
    # Load daily sales data
    df = pd.read_csv(SALES_DATA_PATH)
    df['date'] = pd.to_datetime(df['date'], format='%Y-%m-%d')
    
    # Create a monthly index: date_block_num where 0 = January 2013
    df['date_block_num'] = (df['date'].dt.year - 2013) * 12 + (df['date'].dt.month - 1)
    
    # Aggregate daily sales into monthly sales for each shop and product
    monthly_sales = (
        df.groupby(['date_block_num', 'shop_id', 'item_id'], as_index=False)['item_cnt_day'].sum()
        .rename(columns={'item_cnt_day': 'monthly_sales'})
    )
    
    print("After Aggregation (monthly_sales):", monthly_sales.columns)
    
    # Create a grid with every combination of month, shop, and product
    all_months = np.arange(monthly_sales['date_block_num'].min(), monthly_sales['date_block_num'].max() + 1)
    all_shops = monthly_sales['shop_id'].unique()
    all_items = monthly_sales['item_id'].unique()
    full_grid = pd.MultiIndex.from_product(
        [all_months, all_shops, all_items],
        names=['date_block_num', 'shop_id', 'item_id']
    ).to_frame(index=False)
    
    print("After Creating Full Grid:", full_grid.columns)
    
    monthly_sales = full_grid.merge(monthly_sales, on=['date_block_num', 'shop_id', 'item_id'], how='left')
    monthly_sales['monthly_sales'] = monthly_sales['monthly_sales'].fillna(0)
    
    print("After Merging Full Grid (monthly_sales):", monthly_sales.columns)



    lags = [1, 2, 3]
    monthly_sales = create_lag_features(monthly_sales, lags, 'monthly_sales')

    monthly_sales['rolling_mean_3'] = monthly_sales.groupby(['shop_id', 'item_id'])['monthly_sales'] \
    .transform(lambda x: x.shift(1).rolling(window=3, min_periods=1).mean())
    
    # Fill NaN values after shifting and rolling calculations
    monthly_sales.fillna(0, inplace=True)
    
    print("After Feature Engineering (monthly_sales):", monthly_sales.columns)
    
    # Use all months except the last observed month for training
    max_month = monthly_sales['date_block_num'].max()
    train_data = monthly_sales[monthly_sales['date_block_num'] < max_month]
    features = ['monthly_sales_lag_1', 'monthly_sales_lag_2', 'monthly_sales_lag_3', 'rolling_mean_3']
    
    print("Training Data Columns:", train_data.columns)
    
    X_train = train_data[features]
    y_train = train_data['monthly_sales']
    
    # Train the XGBoost model
    model = xgb.XGBRegressor(objective='reg:squarederror', random_state=42, n_estimators=100)
    model.fit(X_train, y_train)
    
    # Save the trained model
    with open(MODEL_PATH, 'wb') as f:
        pickle.dump(model, f)
    
    print("Model trained and saved.")
    return model

def create_lag_features(df, lags, col):
        """
        For each lag in "lags", create a new column with shifted values of `col`.
        """
        for lag in lags:
            df[f'{col}_lag_{lag}'] = df.groupby(['shop_id', 'item_id'])[col].shift(lag)
        return df

def load_model():
    """Load the XGBoost model from disk; train one if the file does not exist."""
    if not os.path.exists(MODEL_PATH):
        return train_model_if_not_exists()
    with open(MODEL_PATH, 'rb') as f:
        model = pickle.load(f)
    return model

def forecast_next_month():
    """
    Processes historical daily sales data to forecast next month's 
    total monthly sales for every (shop_id, item_id) combination.
    """
    # Load and preprocess daily sales data
    df = pd.read_csv(SALES_DATA_PATH)
    df['date'] = pd.to_datetime(df['date'], format='%Y-%m-%d')
    df['date_block_num'] = (df['date'].dt.year - 2013) * 12 + (df['date'].dt.month - 1)
    
    # Aggregate daily to monthly sales per shop and product
    monthly_sales = (
        df.groupby(['date_block_num', 'shop_id', 'item_id'], as_index=False)['item_cnt_day'].sum()
        .rename(columns={'item_cnt_day': 'monthly_sales'})
    )
    
    print("Test Data After Aggregation:", monthly_sales.columns)
    
    # Create the full grid of (month, shop, item)
    max_month = monthly_sales['date_block_num'].max()
    next_month = max_month + 1

    test_grid = pd.MultiIndex.from_product(
        [[next_month], monthly_sales['shop_id'].unique(), monthly_sales['item_id'].unique()],
        names=['date_block_num', 'shop_id', 'item_id']
    ).to_frame(index=False)
    
    last_month_features = monthly_sales[monthly_sales['date_block_num'] == max_month][[
        'shop_id', 'item_id', 'monthly_sales', 'monthly_sales_lag_1', 'monthly_sales_lag_2', 'monthly_sales_lag_3', 'rolling_mean_3'
    ]]

    print("Last Month Features:", last_month_features.columns)

    # Ensure test set has the same feature structure
    test = test_grid.merge(last_month_features, on=['shop_id', 'item_id'], how='left')

    # Fill missing values to ensure stability in prediction
    test.fillna(0, inplace=True)

    print("Final Test Data Before Prediction:", test.columns)

    feature_columns = ['monthly_sales_lag_1', 'monthly_sales_lag_2', 'monthly_sales_lag_3', 'rolling_mean_3']
    
    # Extract feature inputs for prediction
    X_test = test[feature_columns]
    
    # Load the trained model
    model = load_model()
    
    # Forecast next month's sales
    test['predicted_sales'] = model.predict(X_test)
    test['predicted_sales'] = test['predicted_sales'].round(2)
    
    return test[['date_block_num', 'shop_id', 'item_id', 'predicted_sales']]

@app.route('/')
def index():
    return "Sales Forecasting API is running. Use the /forecast endpoint to get predictions."

@app.route('/forecast', methods=['GET'])
def forecast():
    try:
        predictions = forecast_next_month()
        # Convert the DataFrame to a list of records; each record is a dictionary.
        result = predictions.to_dict(orient='records')
        return jsonify({"forecast": result})
    except Exception as e:
        return jsonify({"error": str(e)}), 500

if __name__ == '__main__':
    # Run the Flask API on port 5000 with debug mode enabled
    app.run(host='0.0.0.0', port=5000, debug=True)
