#g. List the names, city and state of clients who are not in the state of ‘Maharashtra’.

import mysql.connector 
from mysql.connector import Error

try:
    conn = mysql.connector.connect(host = 'localhost',
                                   database = 'cdac_python_programming_ex_11',
                                   user = 'root',
                                   password = 'Advait@0305')
    
    if conn.is_connected():
        cursor = conn.cursor()

       
        query = "SELECT NAME, CITY, STATE FROM CLIENT_MASTER WHERE STATE NOT IN ('MAHARASHTRA');"
        cursor.execute(query)

       
        results = cursor.fetchall()
        if results:
            print("List of the names, city and state of clients who are not in the state of ‘Maharashtra’")
            for row in results:
                print(row)
        else:
            print("No such clients found!") 

except Error as e:
    print('Error while connecting to MySQL:', e)

finally:
    if 'conn' in locals() and conn.is_connected():
        cursor.close()
        conn.close()
        print('MySQL connection is closed!')
