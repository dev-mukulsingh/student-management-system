# Student Management System

A web application to manage student records and academic performance, built with Java Spring Boot, MySQL, and Thymeleaf.

This project was built during my internship to practice CRUD operations, working with a relational database, and building a multi-page web application using Spring MVC.

## Features

- Add, view, edit, and delete student records
- Enter and manage marks for each student across multiple subjects
- Automatic report generation showing total marks, percentage, and grade
- Simple web interface built with Thymeleaf

## Tech Stack

- Java 17
- Spring Boot 3
- Spring Data JPA
- MySQL
- Thymeleaf
- HTML, CSS

## Project Structure

```
student-management-system/
├── pom.xml
├── README.md
└── src/main/
    ├── java/com/college/studentmanagement/
    │   ├── StudentManagementSystemApplication.java  - main class
    │   ├── model/
    │   │   ├── Student.java        - student entity
    │   │   ├── Marks.java          - marks entity
    │   │   └── ReportSummary.java  - holds calculated report data
    │   ├── repository/
    │   │   ├── StudentRepository.java
    │   │   └── MarksRepository.java
    │   ├── service/
    │   │   ├── StudentService.java
    │   │   └── MarksService.java   - includes report generation logic
    │   ├── controller/
    │   │   ├── HomeController.java
    │   │   ├── StudentController.java  - CRUD for students
    │   │   └── MarksController.java    - CRUD for marks
    │   └── util/
    │       └── GradeCalculator.java    - converts percentage to a letter grade
    └── resources/
        ├── application.properties      - database configuration
        ├── templates/students/
        │   ├── list.html
        │   ├── form.html
        │   ├── details.html
        │   └── marks-form.html
        └── static/css/style.css
```

## Setup Instructions

### 1. Requirements

- Java 17 or higher
- Maven
- MySQL Server (running locally)

### 2. Create the database

The application will create the `student_management` database automatically on startup if it does not already exist, as long as the MySQL user has permission to create databases.

### 3. Configure the database connection

Open `src/main/resources/application.properties` and update the username and password to match your MySQL setup:

```properties
spring.datasource.username=root
spring.datasource.password=your_mysql_password
```

### 4. Run the application

```bash
mvn clean install
mvn spring-boot:run
```

The app will start on:

```
http://localhost:8080
```

### 5. Using the app

- The home page shows a list of all students
- Click "Add New Student" to create a new record
- Click "View" on any student to see their details, enter marks, and view their report summary
- Click "Add Marks" on the student details page to enter marks for a subject

## Grading Scale

| Percentage | Grade |
|---|---|
| 90 and above | A+ |
| 75 - 89 | A |
| 60 - 74 | B |
| 45 - 59 | C |
| 33 - 44 | D |
| Below 33 | F |

## Possible Improvements

- Add authentication for admin/teacher login
- Export report as a PDF
- Add pagination and search for the student list
- Add validation to prevent duplicate subject entries per student
