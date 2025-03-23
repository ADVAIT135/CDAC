# e. List all information from the Sales_order table for order placed in the month of June.

import mysql.connector 
from mysql.connector import Error

try:
    conn = mysql.connector.connect(host = 'localhost',
                                   database = 'cdac_python_programming_ex_11',
                                   user = 'root',
                                   password = 'Advait@0305')
    
    if conn.is_connected():
        cursor = conn.cursor()

       
        query = "SELECT * FROM SALES_ORDER WHERE MONTH(ORDERDATE) = 6;"
        cursor.execute(query)

       
        results = cursor.fetchall()
        if results:
            print("List of information from the Sales_order table for order placed in the month of June.")
            for row in results:
                print(row)
        else:
            print("No orders found from the Sales_order table for order placed in the month of June.") 

except Error as e:
    print('Error while connecting to MySQL:', e)

finally:
    if 'conn' in locals() and conn.is_connected():
        cursor.close()
        conn.close()
        print('MySQL connection is closed!')
