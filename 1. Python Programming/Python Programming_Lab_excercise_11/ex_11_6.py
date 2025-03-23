#f. List the Order No & day on which clients placed their order.

import mysql.connector 
from mysql.connector import Error

try:
    conn = mysql.connector.connect(host = 'localhost',
                                   database = 'cdac_python_programming_ex_11',
                                   user = 'root',
                                   password = 'Advait@0305')
    
    if conn.is_connected():
        cursor = conn.cursor()

       
        query = "SELECT ORDERNO, DAYNAME(STR_TO_DATE(ORDERDATE, '%Y-%m-%d')) AS Order_Day FROM SALES_ORDER;"
        cursor.execute(query)

       
        results = cursor.fetchall()
        if results:
            print("List the Order No & day on which clients placed their order.")
            for row in results:
                print(row)
        else:
            print("No orders found.") 

except Error as e:
    print('Error while connecting to MySQL:', e)

finally:
    if 'conn' in locals() and conn.is_connected():
        cursor.close()
        conn.close()
        print('MySQL connection is closed!')
