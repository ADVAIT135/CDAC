# c. List all clients who stay in ‘Bangalore’ or ‘Mangalore’.

import mysql.connector 
from mysql.connector import Error

try:
    conn = mysql.connector.connect(host = 'localhost',
                                   database = 'cdac_python_programming_ex_11',
                                   user = 'root',
                                   password = 'Advait@0305')
    
    if conn.is_connected():
        cursor = conn.cursor()

       
        query = "SELECT NAME, CITY FROM CLIENT_MASTER WHERE CITY IN ('BANGALORE','MANGALORE');"
        cursor.execute(query)

       
        results = cursor.fetchall()
        if results:
            print("List of all clients who stay in ‘Bangalore’ or ‘Mangalore’.")
            for row in results:
                print(f"Name: {row[0]}, City: {row[1]}")
        else:
            print("No clients found in cities starting with 'M'.") 

except Error as e:
    print('Error while connecting to MySQL:', e)

finally:
    if 'conn' in locals() and conn.is_connected():
        cursor.close()
        conn.close()
        print('MySQL connection is closed!')
