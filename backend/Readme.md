# Backend Template

## How to run it

### Start the backed
`mvn spring-boot:run`

### Start the backed with test data
If the database is not clean, the test data won't be inserted

`mvn spring-boot:run -Dspring-boot.run.profiles=generateData`

## Application Secrets Configuration

This project requires a dedicated `application-secret.properties` file to store sensitive configuration values such as JWT secrets and database credentials.
The file **must not be committed to version control** (e.g., add it to `.gitignore`).

---

### 1. File Location

Create the file:
**`src/main/resources/application-secret.properties`**

### 2. Required Properties

The file must contain the following entries:

```properties
# JWT configuration
jwt.secret=your-jwt-secret-key

# Database configuration
db.username=your-database-username
db.password=your-database-password

