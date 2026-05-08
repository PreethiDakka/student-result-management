# Student Result Management System

A full stack web application built with Java Spring Boot and MySQL.

## Features
- Admin login — add students, manage results
- Student login — view personal results
- Auto grade calculation (A+, A, B, C, D, F)
- Auto pass/fail status
- Role based access control (Admin & Student)
- Secure authentication with Spring Security

## Tech Stack
- Java 17
- Spring Boot 3.5
- Spring Security
- Spring Data JPA / Hibernate
- MySQL
- Thymeleaf
- HTML / CSS

## How to Run
1. Clone the repository
2. Create MySQL database: `CREATE DATABASE srms_db;`
3. Update `application.properties` with your MySQL password
4. Run the application
5. Go to `http://localhost:8080`
6. Login with admin / admin123

## Screenshots
Admin Dashboard — manage students and results
Student Portal — view personal results and grades