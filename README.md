# Student Management Application

## Overview

This application is a simple **College Student Management System** built
using **Spring Boot**.

The application exposes a **RESTful Web API** that supports full **CRUD
operations** for managing college students.

------------------------------------------------------------------------

## Architecture

The project follows **Clean Architecture** with clear separation of
concerns:

    com.collage.student
    ├── domain.model
    │   └── Student
    ├── application.service
    │   └── StudentService
    ├── infrastructure
    │   ├── bootstrap
    |   |   └── DataInitializer
    │   ├── config
    |   |   └── WebConfig
    │   ├── persistence
    │   |   ├── StudentEntity
    │   |   └── StudentRepository
    │   └── validation
    │       ├── UniqueStudentId
    │       └── UniqueStudentIdValidator
    ├── presentation
    │   ├── controller
    │   |   └── StudentController  
    │   ├── dto
    │   |   ├── StudentDetailResponse
    |   |   ├── StudentRequest
    │   |   └── StudentResponse
    |   └── exception
    |       └── GlobalExceptionHandler
    └── StudentApplication

### Layer Responsibilities

-   **Domain**
    -   Represents the core business model (Student)
    -   Contains business rules and calculations (e.g. age calculation)
    -   Handles derived attributes such as full name formatting
    -   Fully independent from frameworks, databases, and external libraries
-   **Application**
    -   Implements application use cases and business workflows
    -   Coordinates interactions between domain models and persistence layer
    -   Contains application-level logic (create, update, delete, retrieve students)
    -   Maps persistence entities to domain models
    - Does not contain HTTP or database-specific code
-   **Infrastructure**
    -   Handles technical concerns and external dependencies
    -   Implements database persistence using JPA and H2
    -   Provides repository implementations for data access
    -   Contains database configuration and bootstrap logic
    -   Initializes sample data for development and demonstration purposes
    -   Validation for check data before insert to database
-   **Presentation**
    -   Exposes the application functionality via RESTful HTTP endpoints
    -   Handles incoming requests and outgoing responses
    -   Defines request and response DTOs for API contracts
    -   Applies HTTP status codes and request validation
    -   Contains global exception handling (GlobalExceptionHandler) to translate domain and validation errors into proper HTTP responses
    -   Does not contain business logic or persistence logic

------------------------------------------------------------------------

## Technology Stack

-   Java 17
-   Spring Boot 3
-   Spring Data JPA
-   H2 In-Memory Database
-   Maven
-   REST API (JSON)

------------------------------------------------------------------------

## Database

The application uses **H2 In-Memory Database**.

-   Data is automatically initialized at application startup
-   No external database setup required
-   Database resets every time the application restarts

------------------------------------------------------------------------

## Running the Application

### Run using Maven

``` bash
mvn spring-boot:run
```

### Run from IDE

Run the `StudentApplication` main class.

The application will start on:

    http://localhost:8080

------------------------------------------------------------------------

## API Endpoints

### Create Student

``` http
POST /api/students
```

Request body:

``` json
{
    "id": "A001",
    "firstName": "Budi",
    "lastName": "Santoso",
    "birthDate": "2001-03-15"
}
```

Response: - `201 Created`

------------------------------------------------------------------------

### Get All Students

``` http
GET /api/students
```

Response:

``` json
[
  {
    "id": "A001",
    "fullName": "Budi Santoso",
    "age": 24
  }
]
```

Response code: - `200 OK`

------------------------------------------------------------------------

### Get Student by ID

``` http
GET /api/students/{id}
```

Response code: - `200 OK` - `404 Not Found` (if student does not exist)

------------------------------------------------------------------------

### Update Student

``` http
PUT /api/students/{id}
```

Request body:

``` json
{
    "firstName": "Hendra",
    "lastName": "Santoso",
    "birthDate": "2001-03-15"
}
```

Response code: - `200 OK`

------------------------------------------------------------------------

### Delete Student

``` http
DELETE /api/students/{id}
```

Response code: - `204 No Content`

------------------------------------------------------------------------

## Dummy Data

The application initializes sample student data at startup using
`CommandLineRunner`.\
This allows the API to be tested immediately without manual data entry.

------------------------------------------------------------------------

## Design Decisions

-   DTOs are used to separate API contracts from domain models
-   Domain model is immutable to protect business rules
-   HTTP status codes follow REST conventions
-   Business logic is not placed in controllers
-   Persistence is isolated in the infrastructure layer

------------------------------------------------------------------------

## Notes

-   This project focuses on architecture and code quality
-   UI (Angular SPA) can be added as a separate frontend project
-   H2 database can be easily replaced with PostgreSQL if needed
