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

## Improvement Points

### Horizontal Scaling with Kubernetes
To enhance the scalability of the application, Kubernetes can be implemented for horizontal scaling. By deploying the application on a Kubernetes cluster, we can ensure that it can handle increased traffic and load by automatically scaling the number of instances based on demand. This will improve the application's availability and reliability.

### Price Optimization
The current algorithm may lose efficiency when dealing with prices in currencies with high inflation. To address this, we can implement a feature to convert item prices to a stable currency like USD before processing. This will ensure that the algorithm performs optimally regardless of the currency's inflation rate. By using a currency conversion service, we can dynamically convert prices to USD, improving the accuracy and efficiency of the optimal item calculation.
### Example of Kubernetes Horizontal Pod Autoscaler (HPA) YAML

To implement horizontal scaling with Kubernetes, you can use the Horizontal Pod Autoscaler (HPA). Below is an example of an HPA configuration YAML file:

```yaml
apiVersion: autoscaling/v1
kind: HorizontalPodAutoscaler
metadata:
    name: coupon-api-hpa
spec:
    scaleTargetRef:
        apiVersion: apps/v1
        kind: Deployment
        name: coupon-api
    minReplicas: 2
    maxReplicas: 10
    targetCPUUtilizationPercentage: 50
```

This configuration will automatically scale the `coupon-api` deployment between 2 and 10 replicas based on the CPU utilization, aiming to keep it at 50%.

### Detailed Price Optimization

To optimize the algorithm for handling prices in currencies with high inflation, we can implement a price conversion feature. This involves converting item prices to a stable currency like USD before processing. Here are the steps to achieve this:

1. **Currency Conversion Service**: Integrate a currency conversion API (e.g., OpenExchangeRates, CurrencyLayer) to fetch the latest exchange rates.
2. **Price Conversion**: Before calculating the optimal items, convert the prices of all items to USD using the fetched exchange rates.
3. **Algorithm Adjustment**: Modify the algorithm to work with the converted USD prices to ensure consistent performance.

#### Example Implementation

1. **Fetch Exchange Rates**:
        ```java
        public class CurrencyConverter {
                private static final String API_URL = "https://api.exchangeratesapi.io/latest?base=USD";

                public Map<String, Double> getExchangeRates() {
                        // Call the API and parse the response to get exchange rates
                }
        }
        ```

2. **Convert Prices**:
        ```java
        public class PriceConverter {
                private CurrencyConverter currencyConverter;

                public PriceConverter(CurrencyConverter currencyConverter) {
                        this.currencyConverter = currencyConverter;
                }

                public double convertToUSD(double price, String currency) {
                        Map<String, Double> rates = currencyConverter.getExchangeRates();
                        return price / rates.get(currency);
                }
        }
        ```

3. **Adjust Algorithm**:
        ```java
        public class CouponService {
                private PriceConverter priceConverter;

                public CouponService(PriceConverter priceConverter) {
                        this.priceConverter = priceConverter;
                }

                public List<Item> calculateOptimalItems(List<Item> items, double amount, String currency) {
                        double amountInUSD = priceConverter.convertToUSD(amount, currency);
                        // Use amountInUSD and converted item prices in the algorithm
                }
        }
        ```

By implementing these steps, the algorithm will handle prices more efficiently, regardless of the currency's inflation rate.