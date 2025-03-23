# a. List the names of all clients having ‘a’ as the second letter in their names.

import mysql.connector 
from mysql.connector import Error

try:
    conn = mysql.connector.connect(host = 'localhost',
                                   database = 'cdac_python_programming_ex_11',
                                   user = 'root',
                                   password = 'Advait@0305')
    
    if conn.is_connected():
        cursor = conn.cursor()

       
        query = "SELECT name FROM CLIENT_MASTER WHERE name LIKE '_a%';"
        cursor.execute(query)

       
        results = cursor.fetchall()
        print("Clients with 'a' as the second letter in their name:")
        for row in results:
            print(row[0])  

except Error as e:
    print('Error while connecting to MySQL:', e)

finally:
    if 'conn' in locals() and conn.is_connected():
        cursor.close()
        conn.close()
        print('MySQL connection is closed!')
