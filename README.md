

# Product Management REST API

This is a simple RESTful API created with Spring Boot for managing a list of products. The API supports basic CRUD (Create, Read, Update, Delete) operations for managing products. It also includes exception handling for scenarios like "Product not found" and "Invalid input data". Input validation is implemented for fields like `name` and `price`.

## Project Structure

```
product-management/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── com/
│   │   │   │   ├── example/
│   │   │   │   │   ├── product_management/
│   │   │   │   │   │   ├── controller/
│   │   │   │   │   │   ├── exception/
│   │   │   │   │   │   ├── model/
│   │   │   │   │   │   ├── repository/
│   │   │   │   │   │   ├── service/
│   │   ├── resources/
│   │   │   ├── application.properties
└── pom.xml
```

### Folders:
- **controller**: Contains the REST controllers to manage products.
- **exception**: Handles custom exceptions such as `ProductNotFoundException`.
- **model**: Contains the `Product` class with validation annotations.
- **repository**: Defines the `ProductRepository` interface.
- **service**: Contains the business logic for managing products.

---

## Endpoints

1. **GET /api/products** - Get all products.
2. **GET /api/products/{id}** - Get a product by ID.
3. **POST /api/products** - Create a new product.
4. **PUT /api/products/{id}** - Update an existing product.
5. **DELETE /api/products/{id}** - Delete a product by ID.

---

## Exception Handling and Validation

### 1. **Product Not Found Exception (404)**
   - If a product is not found for a GET or DELETE operation, a custom exception will be thrown, returning a `404 Not Found` response with an appropriate message.
  
### 2. **Invalid Input Data (400)**
   - Input validation is applied to ensure:
     - `name` should not be `null` or empty.
     - `price` should be a positive value.
   - If validation fails, a `400 Bad Request` response will be returned with error details.

---

## Setup and Running the Application

### Prerequisites:
- JDK 11 or later.
- Maven installed (or use the one bundled with IntelliJ IDEA).
- IntelliJ IDEA (or any Java IDE).

### Steps to Run:
1. Clone the repository to your local machine:
   ```bash
   git clone https://github.com/your-username/product-management-api.git
   ```
   
2. Navigate to the project folder:
   ```bash
   cd product-management-api
   ```

3. Open the project in IntelliJ IDEA (or your preferred IDE).

4. Build and run the application:
   ```bash
   mvn spring-boot:run
   ```

5. The application will start on `http://localhost:8080`.

---

## Testing with Postman

### Import the Postman Collection:
1. Download the [Product Management API Postman Collection](product-management-api/Product%20Management%20API.postman_collection.json).
2. Open Postman, click on `Import`, and upload the collection file.
3. The collection will automatically load all the available API endpoints. You can now use these pre-configured requests to test each endpoint.

---

## Postman Test Instructions

Here’s how to test the API using Postman:

1. **GET all products**:
   - **Method**: GET
   - **URL**: `http://localhost:8080/api/products`
   - **Action**: Test by sending a GET request to fetch all products.

2. **GET product by ID**:
   - **Method**: GET
   - **URL**: `http://localhost:8080/api/products/{id}`
     - Replace `{id}` with a valid product ID to get the product.
   - **Action**: If the product exists, it should return the product details.

   **To test the "Product Not Found" exception:**
   - Replace `{id}` with an ID that does not exist in the database.
   - Observe the response which should return a 404 error with a message like "Product with ID {id} not found."

3. **POST create product**:
   - **Method**: POST
   - **URL**: `http://localhost:8080/api/products`
   - **Body**: 
     ```json
     {
       "name": "New Product",
       "description": "Product description",
       "price": 20.99
     }
     ```
   - **Action**: Send a POST request to create a new product.

   **To test the "Invalid Input Data" exception (e.g., price is negative or missing):**
   - Modify the body, for example:
     ```json
     {
       "name": "Invalid Product",
       "description": "Invalid price",
       "price": -5.99
     }
     ```
   - Observe the response which should return a 400 error with a message like "Validation failed: price must be a positive value."

4. **PUT update product**:
   - **Method**: PUT
   - **URL**: `http://localhost:8080/api/products/{id}`
     - Replace `{id}` with a valid product ID.
   - **Body**:
     ```json
     {
       "name": "Updated Product",
       "description": "Updated description",
       "price": 25.99
     }
     ```
   - **Action**: Send a PUT request to update an existing product.

   **To test the "Product Not Found" exception:**
   - Replace `{id}` with a non-existent product ID.
   - Observe the response for a 404 error indicating the product was not found.

5. **DELETE product**:
   - **Method**: DELETE
   - **URL**: `http://localhost:8080/api/products/{id}`
     - Replace `{id}` with a valid product ID.
   - **Action**: Send a DELETE request to delete the product.

   **To test the "Product Not Found" exception:**
   - Replace `{id}` with a non-existent product ID.
   - Observe the response for a 404 error indicating the product was not found.

---

## Conclusion

This project provides a RESTful API for managing products with validation and exception handling. You can easily test the API using Postman by importing the pre-configured collection. The application supports basic CRUD operations and has been enhanced with custom exception handling and input validation for a more robust and user-friendly experience.

---
