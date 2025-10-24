
# ✅ TinyTask: A Minimal Task Management API

**TinyTask** is a minimal RESTful API built to manage tasks. It provides core endpoints to create, read, toggle the status, and delete tasks. The application uses **Spring Boot 3.5.6** and persists data in a **PostgreSQL** database via **Spring Data JPA**.

## 💻 Technical Stack

| Component | Technology/Library | Version/Details |
| :--- | :--- | :--- |
| **Language** | Java | 21 (JDK 21) |
| **Framework** | Spring Boot | 3.5.6 |
| **Persistence** | Spring Data JPA | Handles ORM via Hibernate |
| **Database** | PostgreSQL | JDBC driver included |
| **Build Tool** | Maven Wrapper | `mvnw`/`mvnw.cmd` included |
| **Helper** | Lombok | Boilerplate code reduction |

The application's entry point is the main class: `com.crudactivity.tinytask.TinytaskApplication`.

---

## 📌 Domain Model & Validation

The API manages a single entity: **`Task`**.

| Field | Type | Description |
| :--- | :--- | :--- |
| `id` | `Long` | Primary key. |
| `title` | `String` | The task description. **Required and must not be empty**. |
| `done` | `Boolean` | Completion status (e.g., `true` or `false`). Defaults to `false`. |

### Error Handling

Custom exceptions (`BadRequestException`, `NotFoundException`) are handled by the **`GlobalExceptionHandler`** to map errors to appropriate HTTP status codes (e.g., **400 Bad Request**, **404 Not Found**).

---

## 🌐 REST API Overview

The base path for all task operations is **`/api/tasks`**. This is defined in the `TaskController`.

| Action | Method | Path | Body / Params | HTTP Status | Notes |
| :--- | :--- | :--- | :--- | :--- | :--- |
| **List All** | `GET` | `/api/tasks` | None | `200 OK` | Returns a list of all tasks. |
| **Get By ID** | `GET` | `/api/tasks/{id}` | Path variable: `id` (`Long`) | `200 OK` or `404 Not Found` | Returns the task or an error if the ID is not found. |
| **Create** | `POST` | `/api/tasks` | JSON: `{ "title": string, "done": boolean? }` | `200 OK` or `400 Bad Request` | `title` validation applies (cannot be empty). |
| **Toggle Status** | `PATCH` | `/api/tasks/toggle/{id}` | Path variable: `id` (`Long`) | Implicit `200 OK` or `404 Not Found` | Toggles the boolean state of the `done` field. |
| **Delete** | `DELETE` | `/api/tasks/{id}` | Path variable: `id` (`Long`) | Implicit `200 OK` or `404 Not Found` | Deletes the specified task. |

### CORS Configuration

A global CORS mapping is registered, allowing methods **GET**, **POST**, **PUT**, **DELETE**, and **PATCH** on all paths. Other CORS attributes follow Spring MVC defaults.

---

## ⚙️ Requirements & Configuration

### Prerequisites

* **Java 21** (Ensure `JAVA_HOME` is set).
* **PostgreSQL 14+** instance running.
* Maven Wrapper is included, so a system install of Maven is optional.

### Configuration Details

The default configuration is in `src/main/resources/application.properties`:

| Property | Default Value | Purpose |
| :--- | :--- | :--- |
| `server.port` | `5050` | The port the application runs on. |
| `spring.datasource.url` | `jdbc:postgresql://localhost:5432/tinytask` | PostgreSQL connection URL (default DB name is `tinytask`). |
| `spring.jpa.hibernate.ddl-auto` | `update` | Automatically creates/updates tables based on the entity model. |
| `spring.jpa.show-sql` | `true` | Logs all SQL statements to the console. |


### Database Setup

1.  **Create Database:** Use `createdb tinytask` or `CREATE DATABASE tinytask;` in your PostgreSQL client.
2.  **User Privileges:** Ensure the user in `spring.datasource.username`/`password` has access.
3.  **Seed Data (Optional):** Manually execute `src/main/resources/script.sql` to bootstrap the table and add an example row.

---

## 🚀 Build and Run

Use the Maven Wrapper (`./mvnw`) for these common commands:

| Command | Purpose |
| :--- | :--- |
| `./mvnw clean package` | Build a runnable JAR package. |
| `./mvnw spring-boot:run` | Start the application in development mode. |
| `java -jar target/tinytask-0.0.1-SNAPSHOT.jar` | Run the packaged application. |
| `./mvnw clean test` | Run all unit tests (uses JUnit 5 and Spring Boot Test). |

The server will be available at **`http://localhost:5050`**.

## 📋 Example Requests (Using cURL)

| Operation | cURL Command |
| :--- | :--- |
| **List tasks** | `curl -s http://localhost:5050/api/tasks` |
| **Get by id** | `curl -s http://localhost:5050/api/tasks/1` |
| **Create a task** | `curl -s -X POST http://localhost:5050/api/tasks -H 'Content-Type: application/json' -d '{"title":"Write README","done":false}'` |
| **Toggle task done** | `curl -s -X PATCH http://localhost:5050/api/tasks/toggle/1` |
| **Delete a task** | `curl -s -X DELETE http://localhost:5050/api/tasks/1` |