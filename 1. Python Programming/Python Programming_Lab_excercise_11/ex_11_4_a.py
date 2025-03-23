# a. Printing the description and total quantity sold for each product.

import mysql.connector 
from mysql.connector import Error

try:
    conn = mysql.connector.connect(host = 'localhost',
                                   database = 'cdac_python_programming_ex_11',
                                   user = 'root',
                                   password = 'Advait@0305')
    
    if conn.is_connected():
        cursor = conn.cursor()

       
        query = """SELECT P.DESCRIPTION, SUM(S.QTYORDERED) AS Total_Quantity_Sold
                   FROM PRODUCT_MASTER P
                   JOIN SALES_ORDER_DETAILS S
                   ON P.PRODUCTNO = S.PRODUCTNO
                   GROUP BY P.DESCRIPTION;
        """
        cursor.execute(query)

       
        results = cursor.fetchall()
        if results:
            print("Printing the description and total quantity sold for each product.")
            for row in results:
                print(f'Product Description : {row[0]}, Product Quantity : {row[1]}')
        else:
            print("No records found!") 

except Error as e:
    print('Error while connecting to MySQL:', e)

finally:
    if 'conn' in locals() and conn.is_connected():
        cursor.close()
        conn.close()
        print('MySQL connection is closed!')
