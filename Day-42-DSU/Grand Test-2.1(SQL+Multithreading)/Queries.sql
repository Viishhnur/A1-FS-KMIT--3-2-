/* 1) Query to List Products with No Orders
 
 Expected Output Columns:
 ------------------------
 +-----------+--------------+
 | ProductID | Name         |
 +-----------+--------------+
 
 Note: 
 -----
 Database name: customer_orders
 
 Tables in customer_orders:  Customers                                                                       
 OrderItems                                                                      
 Orders                                                                          
 Products
 
 */
USE customer_orders;
SELECT p.productid as ProductID,
    p.name as Name
FROM Products p
    LEFT JOIN OrderItems o ON p.ProductID = o.ProductID
WHERE o.OrderId IS NULL;

/* 2) Query to Get Total Sales for Each Product
 
 Expected Output Columns:
 ------------------------
 +--------------+------------+
 | Name         | TotalSales |
 +--------------+------------+
 
 Note: 
 -----
 Database name: customer_orders
 
 Tables in customer_orders:  Customers                                                                       
 OrderItems                                                                      
 Orders                                                                          
 Products
 
 */
SELECT p.Name AS Name,
    SUM(o.UnitPrice * o.Quantity) AS TotalSales
FROM Products p
    LEFT JOIN OrderItems o ON p.ProductID = o.ProductID
GROUP BY p.ProductID;

/* 3) Query Using a Correlated Subquery to Get the Latest Order for Each Customer
 
 Expected Output Columns:
 ------------------------
 +---------+------------+------------+-----------+
 | OrderID | CustomerID | OrderDate  | TotalCost |
 +---------+------------+------------+-----------+
 
 Note: 
 -----
 Database name: customer_orders
 
 Tables in customer_orders:  Customers                                                                       
 OrderItems                                                                      
 Orders                                                                          
 Products
 
 */
USE customer_orders;
SELECT o1.OrderID,
    o1.CustomerID,
    o1.OrderDate,
    o1.TotalCost
FROM Orders o1
WHERE o1.OrderDate = (
        SELECT MAX(o2.OrderDate)
        FROM Orders o2
        WHERE o2.CustomerID = o1.CustomerID
    );

/* 4) Query to Get Monthly Sales Summary
 
 Expected Output Columns:
 ------------------------
 +---------+--------------+
 | Month   | MonthlySales |
 +---------+--------------+
 | yyyy-mm |      xxxx.xx |
 +---------+--------------+
 
 Note: 
 -----
 Database name: customer_orders
 
 Tables in customer_orders:  Customers                                                                       
 OrderItems                                                                      
 Orders                                                                          
 Products
 
 */
USE customer_orders;

SELECT 
    DATE_FORMAT(o.OrderDate, '%Y-%m') AS Month,
    SUM(o.TotalCost) AS MonthlySales
FROM 
    Orders o
GROUP BY 
    DATE_FORMAT(o.OrderDate, '%Y-%m');

/* 5) Query to Find the Most Frequently Ordered Product
 
 Expected Output Columns:
 ------------------------
 +-------+---------------+
 | Name  | TotalQuantity |
 +-------+---------------+
 
 Note: 
 -----
 Database name: customer_orders
 
 Tables in customer_orders:  Customers                                                                       
 OrderItems                                                                      
 Orders                                                                          
 Products
 
 */
USE customer_orders;
SELECT p.name as Name,
    SUM(o.quantity) AS TotalQuantity
FROM Products p
    JOIN OrderItems o ON p.productid = o.productid
GROUP BY p.productid
ORDER BY TotalQuantity DESC
LIMIT 1;

/* 6) Query to Display customer names in reverse order along with their email domains
 
 Expected Output Columns:
 ------------------------
 +----------------+-------------+
 | ReversedName   | EmailDomain |
 +----------------+-------------+
 
 Note: 
 -----
 Database name: customer_orders
 
 Tables in customer_orders:  Customers                                                                       
 OrderItems                                                                      
 Orders                                                                          
 Products
 
 */
USE customer_orders;
SELECT REVERSE(c.name) AS ReversedName,
    SUBSTRING(c.email, LENGTH(c.email) -11) AS EmailDomain
FROM Customers c;
/*Approach-ii)
USE customer_orders;

SELECT 
    REVERSE(c.Name) AS ReversedName,
    SUBSTRING_INDEX(c.Email, '@', -1) AS EmailDomain
FROM 
    Customers c;
*/

/* 7) Calculate the number of days since each customer's last order and display the name in title case
 
 Expected Output Columns:
 ------------------------
 +----------------+--------------------+
 | TitleCaseName  | DaysSinceLastOrder |
 +----------------+--------------------+
 
 Note: 
 -----
 Database name: customer_orders
 
 Tables in customer_orders:  Customers                                                                       
 OrderItems                                                                      
 Orders                                                                          
 Products
 
 */
USE customer_orders;
select UPPERCASE("Hello");

/* 8) 
 Write a SQL query to find the top 3 most expensive products never ordered, using `NOT IN` and `ORDER BY`.
 
 Expected Output Columns:
 ------------------------
 +------------+--------+
 | Name       | Price  |
 +------------+--------+
 
 Note: 
 -----
 Database name: customer_orders
 
 Tables in customer_orders:  Customers                                                                       
 OrderItems                                                                      
 Orders                                                                          
 Products
 
 */
USE customer_orders;
SELECT p.name AS Name,
    p.price as Price
FROM Products p
    LEFT JOIN OrderItems o ON p.productid = o.productid
WHERE o.OrderId IS NULL;

/* 9) Write a SQL query to calculate the total revenue from orders placed in the last 3 days of the dataset, using `DATE_SUB`.
 
 Expected Output Columns:
 ------------------------
 +---------------+
 | RecentRevenue |
 +---------------+
 
 Note: 
 -----
 Database name: customer_orders
 
 Tables in customer_orders:  Customers                                                                       
 OrderItems                                                                      
 Orders                                                                          
 Products
 
 */
USE customer_orders;

SELECT 
    SUM(o.TotalCost) AS RecentRevenue
FROM 
    Orders o
WHERE 
    o.OrderDate >= DATE_SUB(
        (SELECT MAX(OrderDate) FROM Orders),
        INTERVAL 3 DAY
    );

/* 10) Write a SQL query to find products ordered in every status type, using `NOT EXISTS` and `DISTINCT`.
 
 Expected Output Columns:
 ------------------------
 +-------+
 | Name  |
 +-------+
 
 Note: 
 -----
 Database name: customer_orders
 
 Tables in customer_orders:  Customers                                                                       
 OrderItems                                                                      
 Orders                                                                          
 Products
 
 */
USE customer_orders;

SELECT DISTINCT p.Name AS Name
FROM Products p
WHERE NOT EXISTS (
    SELECT DISTINCT o.Status
    FROM Orders o
    WHERE o.Status NOT IN (
        SELECT DISTINCT o2.Status
        FROM OrderItems oi
        JOIN Orders o2 ON oi.OrderID = o2.OrderID
        WHERE oi.ProductID = p.ProductID
    )
);
