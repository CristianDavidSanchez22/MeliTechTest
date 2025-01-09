# MeliTechTest
Meli tech test solved

## Project Structure

### Nivel1
Contains a Java program that calculates the optimal combination of items that can be purchased with a given coupon amount using dynamic programming.

### Nivel2
Contains a Spring Boot application that exposes an API to calculate the optimal items for a given coupon amount. It includes:
- **Controller**: Handles HTTP requests.
- **Service**: Contains the business logic.
- **Repository**: Interacts with external APIs and caches data using Redis.
- **Model**: Defines the data structures.
- **Exception**: Custom exceptions for error handling.
- **Util**: Utility classes for solving the knapsack problem.
- **Tests**: Unit tests for the application components.

## Dependencies

### Nivel1
- Java Development Kit (JDK) 8 or higher

### Nivel2
- Docker
- Docker Compose
- Spring Boot 2.5.4
- Spring Data Redis
- Lombok
- Swagger 2

## How to Run

### Nivel1
1. Navigate to the `Nivel1` directory.
2. Compile the Java program:
    ```sh
    javac Coupon.java
    ```
3. Run the program:
    ```sh
    java Coupon
    ```

### Nivel2
1. Navigate to the `Nivel2/coupon-api` directory.
2. Start the application:
    ```sh
    docker-compose up
    ```
3. The API will be available at `http://localhost:8080`.

## API Endpoints

### Health Check
- **URL**: `/coupon`
- **Method**: `GET`
- **Description**: Returns a health check message.

### Calculate Optimal Items
- **URL**: `/coupon`
- **Method**: `POST`
- **Description**: Calculates the optimal items to purchase with the given amount.
- **Request Body**:
  ```json
  {
     "itemIds": ["item1", "item2"],
     "amount": 100.0
  }
  ```
- **Responses**:
  - **200 OK**: Returns the optimal items and total amount spent.
  - **404 Not Found**: Insufficient funds to purchase any items.

### Swagger
- **URL**: `http://localhost:8080/swagger-ui/index.html#/`
- **Description**: Access the Swagger UI for API documentation and testing.

## Configuration
- **Redis**: Configure Redis connection using environment variables `SPRING_REDIS_HOST` and `SPRING_REDIS_PORT`.

## Testing
Run the unit tests using Gradle:
```sh
gradle test
```

## Diagrams considerations

### Use Case Diagrams
Before diving into the sequence diagrams, review the use case diagrams to understand the system's functionality from a user's perspective.

### Sequence Diagrams
After reviewing the use case diagrams, examine the sequence diagrams to understand the interactions between different components of the system.