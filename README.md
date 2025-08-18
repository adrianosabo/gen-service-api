# gen-service-api

## Overview
A Spring Boot REST API for managing clients, using MySQL as the database. Supports running via Maven/IDE or Docker Compose.

## Dependencies
- Java 17
- Spring Boot 3.5.4
- Spring Data JPA
- Spring Web
- SpringDoc OpenAPI (Swagger)
- MySQL Connector/J
- Lombok
- Hibernate Validator
- Docker & Docker Compose

## How to Run

### 1. Running via Maven/IDE
- Ensure MySQL is running locally on port 3306.
- Configure `src/main/resources/application.properties` with your local DB credentials.
- Build and run:
  ```sh
  ./mvnw clean package -DskipTests
  ./mvnw spring-boot:run
  ```
- Or run the JAR:
  ```sh
  java -jar target/gen-service-api-0.0.1-SNAPSHOT.jar
  ```

### 2. Running via Docker Compose
- Edit `src/main/resources/application-docker.properties` for Docker DB settings if needed.
- Start all services:
  ```sh
  docker compose up --build
  ```
- The app will be available at `http://localhost:8080`.
- MySQL will be available at `localhost:3306` (from WSL, use WSL IP).

## API Documentation
- Swagger UI: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

## Project Structure
- `src/main/java/com/i3v/server_api/` — Source code
- `src/main/resources/` — Configurations
- `compose.yaml` — Docker Compose file

## Useful Commands

## Authentication & Testing Endpoints

### 1. Register a New User
Send a POST request to `/auth/register` with JSON body:
```json
{
  "username": "testuser",
  "password": "testpass"
}
```
Example using curl:
```sh
curl -X POST http://localhost:8080/auth/register \
  -H "Content-Type: application/json" \
  -d '{"username":"testuser","password":"testpass"}'
```

### 2. Log In
Send a POST request to `/auth/login` with the same credentials:
```json
{
  "username": "testuser",
  "password": "testpass"
}
```
Example using curl:
```sh
curl -X POST http://localhost:8080/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"testuser","password":"testpass"}'
```
The response will include a JWT token:
```json
{
  "token": "<JWT_TOKEN>"
}
```

### 3. Call Protected Endpoints
Use the token in the `Authorization` header:
```sh
curl -X GET http://localhost:8080/clients/search \
  -H "Authorization: Bearer <JWT_TOKEN>"
```

### 4. Swagger UI
You can also use Swagger UI at [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html) to interact with endpoints. For protected endpoints, click "Authorize" and enter `Bearer <JWT_TOKEN>`.

- The database data is persisted in a Docker volume (`db_data`).

## License
MIT
