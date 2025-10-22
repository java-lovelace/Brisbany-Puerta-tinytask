# TinyTask

A minimal RESTful API for managing tasks built with Spring Boot. It exposes endpoints to create and fetch tasks and persists data in PostgreSQL via Spring Data JPA.

## Stack
- Language: Java 21
- Framework: Spring Boot 3.5.6
- Modules/Starters:
  - spring-boot-starter-web
  - spring-boot-starter-data-jpa
  - spring-boot-starter-test (tests)
- Database: PostgreSQL (JDBC driver)
- Helper libs: Lombok (annotation processing)
- Build/Package manager: Maven (wrapper included: `mvnw`/`mvnw.cmd`)

## Application entry point
- Main class: `com.crudactivity.tinytask.TinytaskApplication`
- Starts an embedded server (default configured to port 5050 in this project) and registers REST controllers.

## REST API overview
Base path: `/api/tasks`

Implemented endpoints (from `TaskController`):
- GET `/api/tasks`
  - Returns: list of all tasks (HTTP 200)
- GET `/api/tasks/{id}`
  - Path variable: `id` (Long)
  - Returns: the task when found (HTTP 200)
  - Errors:
    - 404 Not Found via `NotFoundException` when task is absent
- POST `/api/tasks`
  - Body (JSON): `{ "title": string, "done": boolean? }`
  - Validations: `title` must not be empty (400 Bad Request)
  - Returns: created task (HTTP 200)

Global error handling: `GlobalExceptionHandler` maps custom exceptions such as `BadRequestException` and `NotFoundException` to appropriate HTTP responses.

> TODO: If PUT/PATCH/DELETE endpoints are added later (e.g., update or delete a task), document them here. The `TaskService` contains a `deleteTask` method, but there is currently no controller mapping exposing it.

## Domain model
- `Task` entity with fields:
  - `id` (Long, primary key)
  - `title` (String, required)
  - `done` (Boolean, defaults to false)

There is a `script.sql` with initial DDL/DML you can use to bootstrap a database table and seed one example row.

## Requirements
- Java 21 (ensure `JAVA_HOME` points to a JDK 21+ distribution)
- Maven 3.9+ (optional, Maven Wrapper is included so not strictly required)
- PostgreSQL 14+ (tested version not specified)

## Configuration
Main configuration file: `src/main/resources/application.properties`

Current defaults:
- `server.port=5050`
- `spring.datasource.url=jdbc:postgresql://localhost:5432/tinytask`
- `spring.datasource.username=...`
- `spring.datasource.password=...`
- `spring.jpa.hibernate.ddl-auto=update`
- `spring.jpa.show-sql=true`

Recommended: do not hardcode credentials locally. Override via environment variables or a `application-local.properties` and activate it with a profile.

Examples of environment variable overrides (Spring Boot binding):
- `SERVER_PORT=8080`
- `SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/tinytask`
- `SPRING_DATASOURCE_USERNAME=postgres`
- `SPRING_DATASOURCE_PASSWORD=secret`

To use a different profile:
- Run with `-Dspring.profiles.active=local` (and create `application-local.properties`).

## Database setup
1. Create a PostgreSQL database (default name in config is `tinytask`):
   - `createdb tinytask` or `CREATE DATABASE tinytask;`
2. Ensure a user exists and has privileges, then set the URL/username/password accordingly.
3. Optionally execute the seed script:
   - File: `src/main/resources/script.sql`
   - You can run it manually: `psql -d tinytask -f src/main/resources/script.sql`

Note: `spring.jpa.hibernate.ddl-auto=update` will create/update tables based on the entity model. If you prefer DDL migrations, consider adding Flyway or Liquibase. (Not present in this repository.)

## Build and run
Using Maven Wrapper (recommended):
- Build: `./mvnw clean package`
- Run (dev): `./mvnw spring-boot:run`
- Run (jar): `java -jar target/tinytask-0.0.1-SNAPSHOT.jar`

Using Maven (system):
- Build: `mvn clean package`
- Run: `mvn spring-boot:run`

The server listens on `http://localhost:5050` by default (see `server.port`).

## Example requests
- List tasks
  - `curl -s http://localhost:5050/api/tasks`
- Get by id
  - `curl -s http://localhost:5050/api/tasks/1`
- Create a task
  - `curl -s -X POST http://localhost:5050/api/tasks -H 'Content-Type: application/json' -d '{"title":"Write README","done":false}'`

## Scripts and common commands
- `./mvnw spring-boot:run` — start the app in development
- `./mvnw clean test` — run tests
- `./mvnw clean package` — build a runnable JAR
- `java -jar target/tinytask-0.0.1-SNAPSHOT.jar` — run the packaged app

> TODO: If you standardize scripts (e.g., Makefile, Docker, or npm/yarn scripts for frontend), list them here.

## Tests
- Framework: JUnit 5 with Spring Boot Test
- Entry test: `TinytaskApplicationTests#contextLoads` validates application context can start
- Run tests: `./mvnw test`

> TODO: Add unit/integration tests for `TaskService` and `TaskController` to cover business logic and HTTP behavior.

## Project structure
- `pom.xml` — Maven configuration (Java 21, dependencies, plugins)
- `src/main/java/com/crudactivity/tinytask/`
  - `TinytaskApplication.java` — application entry point
  - `controller/TaskController.java` — REST endpoints for tasks
  - `controller/GlobalExceptionHandler.java` — maps exceptions to HTTP responses
  - `entity/Task.java` — JPA entity for tasks
  - `exception/*.java` — custom exceptions
  - `repository/TaskRepository.java` — Spring Data JPA repository
  - `service/TaskService.java` — business/service layer
- `src/main/resources/`
  - `application.properties` — default configuration
  - `script.sql` — optional DDL/DML bootstrap
- `src/test/java/.../TinytaskApplicationTests.java` — sample context load test

