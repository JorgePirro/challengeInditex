# Inditex Prices Application

This Spring Boot REST service allows you to query the final price and applicable rate for a product based on an application date, product ID, and brand ID. The application uses an in-memory H2 database that is preloaded with sample data.

---

## Requirements

- **Java 17**
- **Maven 3.6+**
- Your preferred IDE or terminal

---

## Getting Started

### 1. Clone the Repository

```bash
git clone <repository-URL>
```

### 2. Navigate to the Project Directory

```bash
cd inditex-prices-application
```

### 3. Run the Application

Compile and run the application using Maven:

```bash
mvn clean spring-boot:run
```

The application will start on [http://localhost:8080](http://localhost:8080).

---

## Database Configuration

The application uses an H2 in-memory database. The initialization scripts are located in **src/main/resources/db**. Make sure your `application.yml` includes the following configuration to load these scripts:

```yaml
spring:
  datasource:
    url: jdbc:h2:mem:testdb
    driverClassName: org.h2.Driver
    username: sa
    password:
  jpa:
    database-platform: org.hibernate.dialect.H2Dialect
    hibernate:
      ddl-auto: none
  h2:
    console:
      enabled: true
  sql:
    init:
      mode: always
      schema-locations: classpath:db/schema.sql
      data-locations: classpath:db/data.sql
```

> **Note:** Ensure that both `schema.sql` and `data.sql` are placed in the `src/main/resources/db` directory.

---

## API Endpoint

### GET `/api/v1/prices`

#### Query Parameters:

- **applicationDate:** The date and time for price application (ISO format, e.g., `2020-06-14T10:00:00`).
- **productId:** The product identifier (e.g., `35455`).
- **brandId:** The brand identifier (e.g., `1`).
- **page** and **size:** Pagination parameters.

#### Response:

The endpoint returns a JSON response containing details such as:
- Product ID
- Brand ID
- Applicable rate (`priceList`)
- Application start and end dates
- Final price (`price`)
- Other related fields

---

## Testing with CURL

Below are sample CURL commands to test various scenarios:

### Test 1: 10:00 AM on June 14 for product 35455 and brand 1 (ZARA)

```bash
curl --location --request GET "http://localhost:8080/api/v1/prices?applicationDate=2020-06-14T10:00:00&productId=35455&brandId=1&page=0&size=10" \
--header "Accept: application/json"
```

### Test 2: 4:00 PM on June 14 for product 35455 and brand 1 (ZARA)

```bash
curl --location --request GET "http://localhost:8080/api/v1/prices?applicationDate=2020-06-14T16:00:00&productId=35455&brandId=1&page=0&size=10" \
--header "Accept: application/json"
```

### Test 3: 9:00 PM on June 14 for product 35455 and brand 1 (ZARA)

```bash
curl --location --request GET "http://localhost:8080/api/v1/prices?applicationDate=2020-06-14T21:00:00&productId=35455&brandId=1&page=0&size=10" \
--header "Accept: application/json"
```

### Test 4: 10:00 AM on June 15 for product 35455 and brand 1 (ZARA)

```bash
curl --location --request GET "http://localhost:8080/api/v1/prices?applicationDate=2020-06-15T10:00:00&productId=35455&brandId=1&page=0&size=10" \
--header "Accept: application/json"
```

### Test 5: 9:00 PM on June 16 for product 35455 and brand 1 (ZARA)

```bash
curl --location --request GET "http://localhost:8080/api/v1/prices?applicationDate=2020-06-16T21:00:00&productId=35455&brandId=1&page=0&size=10" \
--header "Accept: application/json"
```

---

## Running Tests

To execute unit and integration tests, run:

```bash
mvn test
```

---

## H2 Console

The H2 console is available at [http://localhost:8080/h2-console](http://localhost:8080/h2-console).

Use the following connection settings:

- **JDBC URL:** `jdbc:h2:mem:testdb`
- **User Name:** `sa`
- **Password:** *(leave blank)*

---

## Additional Notes

- Ensure Java 17 is properly installed and configured.
- If you modify the SQL scripts, restart the application to reload the data.
- This project adheres to hexagonal architecture and follows Clean Code and SOLID principles.

Happy testing!
```