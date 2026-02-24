# 🩺 ElderCare Backend System

### Secure Elder–Caregiver Management & Medicine Reminder API

---

## 📌 Project Overview

ElderCare is a backend REST API system built using Spring Boot that enables secure management of elder–caregiver relationships, medicine reminders, and health tracking.

The application is designed using layered architecture principles and focuses on secure relationship linking via time-limited invite codes.

This project demonstrates backend design, business rule enforcement, entity relationships, and scalable architecture planning.

---

# 🎯 Core Features

## 👤 User Management

* Register users with role: `ELDER` or `CAREGIVER`
* Role-based validation
* Structured DTO-based request/response handling
* Global exception handling

---

## 🔐 Secure Invite-Based Linking System

Instead of direct relationship creation, the system enforces secure linking:

### Workflow

1️⃣ Elder generates time-limited invite code
2️⃣ Code stored with expiration & unused status
3️⃣ Caregiver connects using the invite code
4️⃣ System validates:

* Code exists
* Code not expired
* Code not already used
* User role is CAREGIVER
* No duplicate connection

5️⃣ Relationship stored in caregiver_link table
6️⃣ Invite code marked as used

This ensures controlled and secure caregiver-elder connection.

---

## 💊 Medicine Management

* Add medicines for elders
* Configure dosage and frequency
* Link medicines to specific elder accounts

---

## ⏰ Reminder Tracking System

* Auto-generated reminder logs
* Track medicine adherence
* Mark reminders as TAKEN
* Historical tracking capability

---

## ❤️ Health Data Management

* Store blood pressure
* Store sugar level
* Store weight
* Retrieve elder health history
* Linked caregiver visibility

---

# 🏗️ Architecture

This project follows Clean Layered Architecture:

```
Controller → Service → Repository → Database
```

### 📂 Package Structure

```
controller/
service/
repository/
model/
dto/
exception/
```

### Architectural Highlights

* Separation of concerns
* DTO usage to prevent entity exposure
* Centralized exception handling
* Business rule validation inside service layer
* JPA entity relationship mapping
* Scalable API design

---

# 🗄️ Database Design

### Users

* id
* name
* email
* password
* role (ELDER / CAREGIVER)

### Invite Code

* id
* code
* expires_at
* used
* elder_id (FK)

### Caregiver Link

* id
* elder_id (FK)
* caregiver_id (FK)
* linked_at

### Medicine

* id
* name
* dosage
* frequency
* elder_id (FK)

### Reminder Log

* id
* medicine_id (FK)
* scheduled_time
* status

### Health Data

* id
* elder_id (FK)
* blood_pressure
* sugar_level
* weight

---

# 🛡️ Security Design (Planned Enhancements)

Although current version focuses on business logic validation, the architecture is prepared for:

## 🔐 JWT Authentication

* Login endpoint
* Stateless token-based authentication
* Secure API access

## 🔒 Role-Based Authorization

* ELDER → Manage own data
* CAREGIVER → Access only linked elders
* Endpoint-level access control

## 🔑 Password Encryption

* BCrypt hashing before database storage

---

# 🔔 Firebase Integration (Planned Enhancement)

Future integration with **Firebase Cloud Messaging (FCM)** to enable:

* Real-time medicine reminder notifications
* Push notifications to caregivers
* Emergency health alerts
* Missed-dose alerts

### Planned Architecture

Spring Boot Backend → Firebase Cloud Messaging → React Native Mobile App

Firebase will act as a notification gateway while Spring Boot remains the primary business logic engine.

---

# 🌐 Frontend Integration (Planned)

This backend system is designed to integrate with:

* React Native mobile application
* React.js web dashboard

Planned frontend features:

* Elder dashboard
* Caregiver monitoring panel
* Real-time health charts
* Reminder tracking UI
* Notification display

The backend APIs are fully REST-compliant and frontend-ready.

---

# 🚀 How to Run

1️⃣ Configure MySQL database
2️⃣ Update `application.properties`

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/elder_app
spring.datasource.username=root
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

3️⃣ Run:

```
mvn spring-boot:run
```

Server runs on:

```
http://localhost:8080
```

---

# 🧪 Testing

* Postman API testing
* Positive and negative validation cases
* Business rule verification
* Invite expiration handling
* Duplicate prevention testing

---

# 📈 Scalability Vision

This project is designed to evolve into:

* Secure production-grade healthcare support platform
* Mobile-enabled elder monitoring system
* Push-notification driven reminder engine
* JWT-secured multi-user system
* Cloud-deployed microservice-ready backend

---

# 🏁 Conclusion

ElderCare Backend System is a structured, scalable, and security-focused Spring Boot application designed to manage elder-caregiver relationships and health monitoring workflows.

The system emphasizes clean architecture, secure invite-based linking, and future extensibility with JWT security and Firebase notification services.
