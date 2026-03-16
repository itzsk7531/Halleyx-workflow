# Halleyx Challenge 1 – Workflow Expense Approval System

## Overview

This project is a **Workflow-based Expense Approval System** developed as part of the **Halleyx Full Stack Engineer Challenge I – 2026**.

The system allows employees to submit expense requests which then move through a **multi-level approval workflow** involving a **Manager and CEO**. The application tracks requests, approvals, and status updates in real time.

The goal of this project is to demonstrate **workflow automation, role-based access, and process tracking** using a full-stack Java application.

---

## Features

### Authentication & Role Management

* Secure login system
* Role-based access control
* Supported roles:

  * Admin
  * Manager
  * CEO
  * Employee

### Admin Dashboard

* Add new employees
* View all employees
* Manage user records

### Employee Module

* Submit expense requests
* Track approval status
* View past expense history

### Manager Module

* View employee expense requests
* Approve or reject requests

### CEO Module

* Final approval stage
* View manager-approved requests
* Approve or reject requests

### Workflow Automation

Employee → Manager Approval → CEO Approval → Final Status

---

## Tech Stack

### Backend

* Java
* Spring Boot
* Spring MVC
* Spring Data JPA

### Frontend

* Thymeleaf
* HTML
* CSS

### Database

* MySQL

### Build Tool

* Maven

---

## Project Structure

```
Halleyx/
│
├── controller/
│   ├── AuthController
│   ├── EmployeeController
│   ├── ManagerController
│   └── CeoController
│
├── model/
│   ├── User
│   └── Expense
│
├── repository/
│   ├── UserRepository
│   └── ExpenseRepository
│
├── service/
│   ├── AuthService
│   └── ExpenseService
│
├── templates/
│   ├── admin-dashboard.html
│   ├── manager-dashboard.html
│   ├── ceo-dashboard.html
│   ├── employee-dashboard.html
│   └── login.html
│
└── application.properties
```

---

## Workflow Example

1. Employee creates an expense request
2. Manager reviews and approves/rejects
3. If approved → sent to CEO
4. CEO gives final approval
5. Employee can track the final status

---

## Installation & Setup

### 1. Clone the Repository

```
git clone https://github.com/itzsk7531/Halleyx-challenge-1.git
```

### 2. Open the Project

Import the project into:

* IntelliJ IDEA
* VS Code
* Eclipse

### 3. Configure Database

Update `application.properties`

```
spring.datasource.url=jdbc:mysql://localhost:3306/halleyx
spring.datasource.username=root
spring.datasource.password=yourpassword
```

### 4. Run the Application

Run the main class:

```
HalleyxApplication.java
```

Application will start on:

```
http://localhost:8080
```

---

## Screens (Modules)

* Login Page
* Admin Dashboard
* Manager Approval Dashboard
* CEO Approval Dashboard
* Employee Expense Submission
* Expense Status Tracking

---

## Future Improvements

* Email notifications for approvals
* Workflow rule customization
* API support for integrations
* Audit logging
* UI improvements



Author

SANJAY KRISHNA

Developed for **Halleyx Full Stack Engineer Challenge – 2026**
