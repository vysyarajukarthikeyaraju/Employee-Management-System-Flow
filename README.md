# Employee Management System (Spring Boot)

This is a simple **Employee Management System** built using **Spring Boot**.  
The application provides REST APIs to manage **Employees**, **Departments**, and **Leave Requests**.  
RabbitMQ is used to send simple notification messages when certain actions happen (like employee creation).

The goal of this project is to demonstrate a **real-world backend application** using Spring Boot, JPA, REST APIs, and basic messaging.

---

## 1. Technologies Used

- Java 17
- Spring Boot 3.x
- Spring Data JPA (Hibernate)
- Spring Web (REST APIs)
- Spring Validation
- Spring Security (Basic Auth)
- MySQL Database
- RabbitMQ (for notifications)
- Maven
- Postman (for API testing)

---

## 2. Project Modules

### Employee Module
- Create employee
- Update employee
- Get employee by ID
- Get all employees
- Delete employee

### Department Module
- Create department
- Get all departments
- Get employees by department

### Leave Module
- Apply leave
- Approve / Reject leave
- Get leaves by employee

### Notification Module
- Sends simple messages to RabbitMQ when:
  - Employee is created
  - Leave is applied
  - Leave status is updated

---

## 3. Application Architecture

```
Controller  ->  Service  ->  Repository  ->  Database
                    |
                    ->  RabbitMQ (Notifications)
```

### Layer Explanation

- **Controller Layer**
  - Handles HTTP requests
  - Validates request data
  - Calls service methods

- **Service Layer**
  - Contains business logic
  - Handles transactions
  - Calls repository and notification services

- **Repository Layer**
  - JPA repositories
  - Handles database operations

- **Notification Layer**
  - Sends messages to RabbitMQ
  - Uses RabbitTemplate

- **Exception Handling**
  - Global exception handler
  - Handles validation and not-found errors

---

## 4. Setup Instructions

### Step 1: Prerequisites

Make sure you have:
- Java 17 installed
- MySQL installed and running
- RabbitMQ installed or running via Docker
- Maven installed

---

### Step 2: Database Setup

Create a MySQL database:

```sql
CREATE DATABASE employeedb;
```

Update `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/employeedb
spring.datasource.username=root
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

### Step 3: RabbitMQ Setup

Default RabbitMQ configuration:

```properties
spring.rabbitmq.host=localhost
spring.rabbitmq.port=5672
spring.rabbitmq.username=guest
spring.rabbitmq.password=guest
```

Make sure RabbitMQ Management UI is accessible:
```
http://localhost:15672
```

Username: `guest`  
Password: `guest`

---

### Step 4: Run the Application

Using Eclipse / STS:
1. Right-click on `EmployeeManagementSystemApplication`
2. Run As → Spring Boot App

Using Maven:
```bash
mvn spring-boot:run
```

Application will start on:
```
http://localhost:8081
```

---

## 5. API Documentation

### Employee APIs

#### Create Employee
```
POST /api/employees
```
Request Body:
```json
{
  "name": "Sowmya",
  "email": "sowmya@gmail.com",
  "salary": 35000,
  "joiningDate": "2024-08-10",
  "departmentId": 17
}
```

---

#### Get All Employees
```
GET /api/employees
```

---

#### Get Employee by ID
```
GET /api/employees/{id}
```

---

#### Update Employee
```
PUT /api/employees/{id}
```
Request Body:
```json
{
  "name": "Sowmya Updated",
  "email": "sowmya.updated@gmail.com",
  "salary": 42000,
  "joiningDate": "2024-08-15",
  "departmentId": 17
}
```

---

#### Delete Employee
```
DELETE /api/employees/{id}
```
Response:
```text
Employee deleted successfully with id 27
```

---

### Department APIs

#### Create Department
```
POST /api/departments
```

#### Get All Departments
```
GET /api/departments
```

#### Get Employees by Department
```
GET /api/departments/{id}/employees
```

---

### Leave APIs

#### Apply Leave
```
POST /api/leaves
```

#### Update Leave Status
```
PUT /api/leaves/{id}/status
```

#### Get Leaves by Employee
```
GET /api/leaves/employee/{empId}
```

---

## 6. Exception Handling

The application uses **Global Exception Handling**:

- Resource not found (Employee, Department, Leave)
- Validation errors
- Bad request errors

Example error response:
```json
{
  "message": "Employee not found with id: 99"
}
```

---

## 7. Assumptions Made

- Employee must belong to a valid department
- Leave can be applied only for an existing employee
- RabbitMQ is used only for **simple notification messages**, not complex event processing
- Authentication is basic and meant only for demo purposes
- `spring.jpa.hibernate.ddl-auto=update` is used for development
- No pagination is implemented (can be added later)

---

## 8. Enhancements made

- Swagger / OpenAPI documentation
- Pagination and sorting
- Role-based security
- Email notifications
- Retry and dead-letter queues in RabbitMQ

---

## 9. Author

Developed as a **Spring Boot backend project** to demonstrate real-world API design, clean architecture, and messaging integration.

