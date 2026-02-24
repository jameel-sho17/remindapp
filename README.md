
# 🩺 Elder Reminder & Caregiver Management System

A Spring Boot backend application designed to help elders manage medicines, reminders, health data, and securely connect with caregivers using invite codes.

---

## 🚀 Features

### 👤 User Management

* Register as **ELDER** or **CAREGIVER**
* Get user details
* Role-based validation

### 🔐 Secure Caregiver Linking

* Elder generates **time-limited invite code**
* Caregiver connects using invite code
* Prevents duplicate linking
* Prevents expired/used codes

### 💊 Medicine Management

* Add medicines
* View medicines by elder
* Scheduled reminders

### ⏰ Reminder System

* Auto-generate reminders
* Mark medicine as taken
* Track reminder logs

### ❤️ Health Data Tracking

* Store blood pressure
* Store sugar level
* Store weight
* Retrieve health history

---

## 🏗️ Tech Stack

* **Java 17+**
* **Spring Boot**
* **Spring Data JPA**
* **MySQL**
* **Lombok**
* **Maven**
* (Security planned for future enhancement)

---

## 📂 Project Structure

```
controller/
service/
repository/
model/
dto/
exception/
```

Layered Architecture:

* Controller → API Layer
* Service → Business Logic
* Repository → Database Access
* DTO → Request/Response Handling

---

## 🛠️ Setup Instructions

### 1️⃣ Clone Project

```bash
git clone <your-repository-url>
cd elder-reminder-app
```

### 2️⃣ Configure Database

Update `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/elder_app
spring.datasource.username=root
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### 3️⃣ Run Application

```bash
mvn spring-boot:run
```

Server runs on:

```
http://localhost:8080
```

---

## 📌 API Endpoints Overview

### 👤 User APIs

| Method | Endpoint              | Description    |
| ------ | --------------------- | -------------- |
| POST   | `/api/users/register` | Register user  |
| GET    | `/api/users/{id}`     | Get user by ID |
| GET    | `/api/users`          | Get all users  |

---

### 🔐 Invite Code APIs

| Method | Endpoint                | Description                  |
| ------ | ----------------------- | ---------------------------- |
| POST   | `/invite-code/generate` | Generate invite code         |
| POST   | `/invite-code/connect`  | Connect caregiver using code |

---

### 🔗 Caregiver Link APIs

| Method | Endpoint                          | Description             |
| ------ | --------------------------------- | ----------------------- |
| GET    | `/caregiver-links/elder/{id}`     | Get caregivers of elder |
| GET    | `/caregiver-links/caregiver/{id}` | Get elders of caregiver |

---

### 💊 Medicine APIs

| Method | Endpoint                |
| ------ | ----------------------- |
| POST   | `/medicines`            |
| GET    | `/medicines/elder/{id}` |

---

### ⏰ Reminder APIs

| Method | Endpoint                   |
| ------ | -------------------------- |
| GET    | `/reminders/elder/{id}`    |
| PUT    | `/reminder-logs/{id}/take` |

---

### ❤️ Health Data APIs

| Method | Endpoint             |
| ------ | -------------------- |
| POST   | `/health`            |
| GET    | `/health/elder/{id}` |

---

## 🔄 End-to-End Flow

1. Register Elder
2. Register Caregiver
3. Elder generates invite code
4. Caregiver connects using code
5. Add medicines
6. System generates reminders
7. Mark reminders as taken
8. Add health data

---

## 🧪 Testing

Use:

* Postman
* Swagger (if enabled)

Test cases:

* Invalid invite code
* Expired code
* Duplicate connection
* Invalid role connection
* Non-existing user

---

## 🛡️ Error Handling

Custom Exceptions:

* `ResourceNotFoundException`
* `BadRequestException`

Standard JSON error response:

```json
{
  "status": 404,
  "error": "NOT FOUND",
  "message": "Elder not found",
  "timestamp": "2026-02-23T15:55:55"
}
```

---

## 🔮 Future Enhancements

* JWT Authentication
* Role-based Authorization
* Email-based invite sending
* Mobile app integration
* Docker deployment
* Admin dashboard

---

## 👨‍💻 Author

Developed as a backend Spring Boot project for secure caregiver–elder management.

---
