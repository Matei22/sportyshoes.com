Sporty Shoes is a company that manufactures and sells sports shoes.
● The Java concepts that I used in the project 
1) Core Java concepts: variables, data types, operators, class, objects, access specifiers, and core keywords like final, this, and static.
2) I used an application named Postman that can handle the requests (GET, PUT, POST and DELETE)
3) Hibernate to integrate the web app with an SQL database (MySQL)
4) Spring Boot and Spring Boot annotations

● Let discuss the generic features of the product:
	When the web application is starting, there will be created 4 tables. First one with details of the users, second one with details of the products, third one that is containing 2 foreign key from the previous two tables (the id of the product and the email of the user, which are the primary keys in each tables), and the forth one where can have only one admin user which can only have the email and password.
	There are several types of request:
First step is to POST the request “Sign up admin”, else if you try to add a product, a user or any other requests, there will be displayed several exceptions: 
-	“AdminNotFound”
-	“ProductNotFound”
-	“UniqueAdminException” (if you try to enter more than one admin)
Second step, you can request to change the admin password with: PUT request “Update admin password” and then change it back with the PUT request “Change back admin pass” to proceed further. 
Third step is to use the POST requests “AddProduct1”, “AddProduct2”, “AddProduct3”, “AddProduct4”, “AddProduct5”, “AddUser1”, “AddUser2”, “AddUser3” for testing purpose.
You can: 
- get all products (GET request “Get all products”)
- get product by a specific Id (GET request “Get product id”)
- get product by a specific name (GET request “Get product by name”)
- update user (PUT request “Update user”)
- add to cart an item (ex: PUT request “AddToCartUser1Product1”)
, as User.
      You can:
-delete product (ex: DELETE request “Delete Product”)
-delete user (ex: DELETE request “DeleteUser1”)
-update product (ex: PUT request “Update product”)
-map all products and number of purchase by users (GET request “List all purchase from all users”)
-list every product that is purchased by users (GET request “Product purchase map”)
-map all users and list of products that they purchased (GET request “Users purchase map”)
, as admin.
