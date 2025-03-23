# b. Calculating the average quantity sold for each client that has a maximum order  value of 15000.00.


import mysql.connector 
from mysql.connector import Error

try:
    conn = mysql.connector.connect(host = 'localhost',
                                   database = 'cdac_python_programming_ex_11',
                                   user = 'root',
                                   password = 'Advait@0305')
    
    if conn.is_connected():
        cursor = conn.cursor()

     
        query = """
        WITH OrderValues AS (
        SELECT so.CLIENTNO, sod.ORDERNO, (sod.QTYORDERED * sod.PRODUCTRATE) AS TotalOrderValue
        FROM SALES_ORDER so
        JOIN SALES_ORDER_DETAILS sod ON so.ORDERNO = sod.ORDERNO
        ),
        FilteredClients AS (
        SELECT CLIENTNO FROM OrderValues
        GROUP BY CLIENTNO
        HAVING MAX(TotalOrderValue) <= 15000
        )
        SELECT so.CLIENTNO, AVG(sod.QTYORDERED) AS AvgQuantitySold
        FROM SALES_ORDER so
        JOIN SALES_ORDER_DETAILS sod ON so.ORDERNO = sod.ORDERNO
        WHERE so.CLIENTNO IN (SELECT CLIENTNO FROM FilteredClients)
        GROUP BY so.CLIENTNO;

        """
        cursor.execute(query)

       
        results = cursor.fetchall()
        if results:
            print("Client-wise Average Quantity Sold (for orders of 15000.00):")
            for row in results:
                print(f"Client No: {row[0]}, Average Quantity Sold: {row[1]}")
        else:
            print("No records found!") 

except Error as e:
    print('Error while connecting to MySQL:', e)

finally:
    if 'conn' in locals() and conn.is_connected():
        cursor.close()
        conn.close()
        print('MySQL connection is closed!')
