# Franchise and Product Management Project

This project provides a REST API for managing franchises, branches, and products, enabling CRUD operations and retrieving products with the highest stock per branch. It is built with Spring Boot, Jakarta EE, and JPA, using MySQL for data persistence.

## Endpoints

### ProductController

- **`POST /products/{branchId}`** - Adds a new product to a specific branch.
    - **Path Parameters**: `branchId` (ID of the branch)
    - **Request Body**: `Product` object

- **`GET /products/{id}`** - Retrieves a product by its ID.
    - **Path Parameters**: `id` (ID of the product)

- **`DELETE /products/{id}`** - Deletes a product by its ID.
    - **Path Parameters**: `id` (ID of the product)

- **`PUT /products/{id}/stock`** - Updates a product's stock.
    - **Path Parameters**: `id` (ID of the product)
    - **Query Parameters**: `stock` (new stock quantity)

- **`GET /products/franchise/{franchiseId}/max-stock`** - Retrieves the product with the highest stock for each branch in a specific franchise.
    - **Path Parameters**: `franchiseId` (ID of the franchise)

### FranchiseController

- **`POST /franchises`** - Adds a new franchise.
    - **Request Body**: `Franchise` object

- **`GET /franchises`** - Retrieves all franchises.

## Project Structure

### Models

- **Franchise**: Represents a franchise containing a list of branches.
- **Branch**: Represents a branch associated with a franchise, containing a list of products.
- **Product**: Represents a product associated with a branch.
- **ProductMaxStockResponse**: Custom response object that includes details of the product with the highest stock in a branch.

### Services

- **FranchiseService**: Manages operations related to franchises.
- **BranchService**: Manages operations related to branches, including adding branches to a franchise.
- **ProductService**: Manages operations related to products, including adding, deleting, and updating product stock.

### Repositories

- **FranchiseRepository**: Extends `JpaRepository` to perform CRUD operations on the `Franchise` entity.
- **BranchRepository**: Extends `JpaRepository` for the `Branch` entity.
- **ProductRepository**: Extends `JpaRepository` for the `Product` entity, with a custom query to retrieve the product with the highest stock per branch within a franchise.

## Minimum Requirements

- Docker: Ensure that Docker and Docker Compose are installed on your system.
- Java 17: Both microservices are built using Java 17.
- Maven: Ensure that Maven is installed to build the project.

## Getting Started

Follow the steps below to get the microservices up and running.

### Step 1: Clone the Repository

Clone the repository to your local machine:

```sh
git clone https://github.com/ASHMEDDF/franchises.git
cd microservices-project
```

### Step 2: Build the Project

Navigate to each microservice directory and build the project using Maven:

```sh
mvn clean package
```

### Step 3: Create Docker Images

Build the Docker images for each microservice using Docker Compose:

```sh
docker-compose build
```

### Step 4: Start the Services

Start the services using Docker Compose:

```sh
docker-compose up
```


### Step 5: Test the Endpoints

Use Postman, curl, or any other HTTP client to test the endpoints exposed by each microservice.
