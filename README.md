
🍽️ Food Delivery System - Backend
This is a Java Spring Boot backend application for a Food Delivery System, designed to handle user orders, restaurant management, delivery tracking, and payments. It uses MySQL as the database and follows a modular and RESTful architecture.


🧰 Tech Stack
Java 17+

Spring Boot

Spring Data JPA

Spring Web

Spring Security (optional)

MySQL

Hibernate

Maven or Gradle

Lombok

Postman (for API testing)




📦 Features
User Registration & Login

Restaurant Management

Menu & Item Management

Order Placement & Tracking

Payment Handling

Delivery Tracking

Role-based Access Control (optional)



🗃️ Database Schema
The database schema consists of the following main entities:

User

Restaurant

Item

Order

Payment

Delivery

📷 ER Diagram:

![image](https://github.com/user-attachments/assets/8d55ec67-99ae-4792-b1c1-4c91233ed182)

📁 Project Structure
css
Copy
Edit
src/
├── main/
│   ├── java/com/fooddelivery/
│   │   ├── controller/
│   │   ├── service/
│   │   ├── model/
│   │   ├── repository/
│   │   ├── dto/
│   │   └── config/
│   └── resources/
│       ├── application.properties
│       └── data.sql




⚙️ Configuration
application.properties
properties
Copy
Edit
spring.datasource.url=jdbc:mysql://localhost:3306/food_delivery
spring.datasource.username=root
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect



🚀 Getting Started
Prerequisites
Java 17+

MySQL Server

Maven or Gradle

IDE (IntelliJ / Eclipse / VS Code)



API Endpoints
Base URL: http://localhost:8080/api

Sample Endpoints
POST /users/register - Register user

POST /orders - Create order

GET /restaurants - List restaurants

POST /payments - Make payment

GET /deliveries/{orderId} - Track delivery

Use Postman for testing.



Future Improvements
Add authentication using JWT


| Class                           | Role                                                  |
| ------------------------------- | ----------------------------------------------------- |
| `SecurityConfig.java`           | Configures security filters and endpoint access       |
| `JWTUtil.java`                  | Creates and validates JWT tokens                      |
| `JWTFilter.java`                | Intercepts requests to check for a valid JWT          |
| `CustomUserDetailsService.java` | Loads user from DB                                    |
| `CustomUserDetails.java`        | Wrapper around your `User` entity for Spring Security |


Add email notifications

Add Swagger documentation

Integrate with third-party payment gateways


