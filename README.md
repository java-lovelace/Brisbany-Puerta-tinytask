

# ✨ Spring Boot Task Management Application

This is a **Full-Stack Task Management** application built with a Spring Boot backend (for a REST API) and a modern JavaScript/frontend.

---

## 🚀 Getting Started

To run this project locally, you need a Java environment and a PostgreSQL instance.

### ⚙️ Prerequisites

* **Java 17+**
* **Maven** or **Gradle** (The `mvnw` wrapper is included)
* **PostgreSQL** Database

### 🔑 Configuration

1.  **Database Setup:** Update the file `backend/src/main/resources/application.properties` with your local PostgreSQL credentials (username and password).
2.  **API URL:** The frontend is configured to communicate with the backend at `http://localhost:5050`. If you change the backend port, ensure you update the `API_URL` variable in `frontend/src/main.js`.

### 🛠️ Build and Run

| Component | Command | Description |
| :--- | :--- | :--- |
| **Backend (Spring Boot)** | `./mvnw spring-boot:run` | Starts the Spring Boot application on port `5050`. |
| **Frontend (JS)** | `pnpm install` or `npm install` | Install dependencies. |
| | `pnpm build` or `npm run build` | Compiles the frontend assets. |
| | `pnpm preview` or `npm run preview` | Serves the frontend application. |

---

## 🌐 REST API Specification

The API follows a RESTful design pattern for managing `Task` resources.

**Base Path:** `/api/tasks`

| Method | Path | Description | HTTP Status | Notes |
| :--- | :--- | :--- | :--- | :--- |
| **GET** | `/api/tasks` | Retrieve a list of all tasks. | `200 OK` | |
| **GET** | `/api/tasks/{id}` | Retrieve a specific task by its ID. | `200 OK` / `404 Not Found` | |
| **POST** | `/api/tasks` | Create a new task. | `201 Created` / `400 Bad Request` | **Request Body:** `{ "title": string, "done"?: boolean }` |
| **PATCH** | `/api/tasks/toggle/{id}` | Toggle the completion status (`done`) of a task. | `200 OK` / `404 Not Found` | Uses `PATCH` for partial update of a single attribute. |
| **DELETE** | `/api/tasks/{id}` | Permanently delete a task by ID. | `204 No Content` / `404 Not Found` | |

### Domain Model: `Task`

The core data structure representing a persistent task entity.

| Field | Type | Constraint | Description |
| :--- | :--- | :--- | :--- |
| `id` | `Long` | Primary Key | Unique identifier. |
| `title` | `String` | Required, Not Empty | The content of the task. |
| `done` | `Boolean` | Defaults to `false` | Completion status of the task. |

### Error Handling

Custom exceptions (`BadRequestException`, `NotFoundException`) are utilized and mapped to the appropriate **HTTP status codes** using a **`GlobalExceptionHandler`** (`@ControllerAdvice`). This ensures a consistent and clean error response format.

---

## 📋 cURL Examples

Use these examples to quickly test the API endpoints from your terminal (assuming the backend runs on `http://localhost:5050`).

| Operation | Command |
| :--- | :--- |
| **List Tasks** | `curl -s http://localhost:5050/api/tasks` |
| **Get By ID** | `curl -s http://localhost:5050/api/tasks/1` |
| **Create Task** | `curl -s -X POST http://localhost:5050/api/tasks -H 'Content-Type: application/json' -d '{"title":"Write README","done":false}'` |
| **Toggle Status** | `curl -s -X PATCH http://localhost:5050/api/tasks/toggle/1` |
| **Delete Task** | `curl -s -X DELETE http://localhost:5050/api/tasks/1` |

---

## 📁 Project Architecture

```
.
├── backend/
│   ├── mvnw
│   ├── mvnw.cmd
│   ├── pom.xml
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/crudactivity/tinytask/
│   │   │   │   ├── TinytaskApplication.java
│   │   │   │   ├── config/
│   │   │   │   │   └── CorsConfig.java
│   │   │   │   ├── controller/
│   │   │   │   │   ├── TaskController.java
│   │   │   │   │   └── GlobalExceptionHandler.java
│   │   │   │   ├── entity/
│   │   │   │   │   └── Task.java
│   │   │   │   ├── exception/
│   │   │   │   │   ├── BadRequestException.java
│   │   │   │   │   └── NotFoundException.java
│   │   │   │   ├── repository/
│   │   │   │   │   └── TaskRepository.java
│   │   │   │   └── service/
│   │   │   │       └── TaskService.java
│   │   │   └── resources/
│   │   │       ├── application.properties
│   │   │       └── script.sql
│   │   └── test/java/com/crudactivity/tinytask/
│   │       └── TinytaskApplicationTests.java
│
├── frontend/
│   ├── index.html
│   ├── package.json
│   ├── pnpm-lock.yaml
│   └── src/
│       ├── main.js
│       └── style.css
│
└── README.md
```
