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
    ├── infrastructure.persistence
    │   ├── StudentEntity
    │   └── StudentRepository
    ├── infrastructure.bootstrap
    │   └── DataInitializer
    ├── presentation.controller
    │   ├── StudentController
    │   ├── StudentRequest
    │   └── StudentResponse
    └── StudentApplication

### Layer Responsibilities

-   **Domain**
    -   Core business logic
    -   Age calculation
    -   Full name formatting
    -   Independent of framework and database
-   **Application**
    -   Use cases and application flow
    -   Orchestrates domain and persistence layers
-   **Infrastructure**
    -   Database persistence (JPA / H2)
    -   Repository implementations
    -   Data initialization (dummy data)
-   **Presentation**
    -   REST controllers
    -   Request & response DTOs
    -   HTTP status code handling

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
  "id": "S001",
  "namaDepan": "Budi",
  "namaBelakang": "Santoso",
  "tanggalLahir": "2000-03-15"
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
    "id": "S001",
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
  "namaDepan": "Budi",
  "namaBelakang": "Sentosa",
  "tanggalLahir": "2000-03-15"
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
