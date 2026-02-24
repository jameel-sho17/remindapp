# 🩺 ElderCare – Medicine Reminder & Caregiver Management System

## 📌 Project Overview

ElderCare is a Spring Boot backend application designed to help elders manage their medicines, health data, and securely connect with caregivers using a time-limited invite system.

The system ensures secure linking between elders and caregivers while providing medicine reminders and health tracking.

This project follows a layered architecture and is designed to be scalable for future mobile/web integration.

---

# 🎯 Core Objectives

* Allow elders to manage medicine schedules
* Allow caregivers to monitor elders
* Secure caregiver-elder connection using invite codes
* Track reminder logs (taken/missed)
* Store health metrics
* Maintain clean, scalable backend architecture

---

# 🏗️ Architecture Overview

The project follows **Layered Architecture**:

```
Controller Layer → Service Layer → Repository Layer → Database
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

### Responsibilities

* **Controller** → Handles HTTP requests
* **Service** → Contains business logic
* **Repository** → Database interaction (JPA)
* **DTO** → Secure request/response transfer
* **Exception** → Centralized error handling

---

# 🔄 Complete Project Workflow

## 1️⃣ User Registration Flow

### Step 1: Register Elder

* Role: `ELDER`
* Stored in users table

### Step 2: Register Caregiver

* Role: `CAREGIVER`
* Stored in users table

Validation:

* Role-based validation enforced
* Duplicate email prevented

---

## 2️⃣ Caregiver Connection Workflow (Secure Invite System)

### Step 1: Elder Generates Invite Code

* System generates unique code
* Code has expiration time
* Code stored in invite_code table
* Code initially marked as `unused`

### Step 2: Caregiver Connects Using Code

System validates:

* Code exists
* Code not expired
* Code not already used
* Caregiver exists
* User role = CAREGIVER
* No duplicate link

If valid:

* Create entry in caregiver_link table
* Mark invite code as used

This ensures:

* Secure and controlled relationship
* No unauthorized linking

---

## 3️⃣ Medicine Management Workflow

Elder can:

* Add medicine
* Set dosage
* Set frequency
* Set start and end dates

Medicine stored in `medicine` table linked to elder.

---

## 4️⃣ Reminder System Workflow

When medicine is added:

* System generates reminder entries
* Reminders stored in reminder_log table
* Status: `PENDING`

Caregiver or elder can:

* Mark reminder as `TAKEN`

This enables tracking adherence.

---

## 5️⃣ Health Data Tracking Workflow

Elder can:

* Add blood pressure
* Add sugar level
* Add weight

Health data stored in `health_data` table linked to elder.

Caregiver can view elder’s health history.

---

# 🗄️ Database Relationships

## Users

* id
* name
* email
* password
* role (ELDER / CAREGIVER)

## Invite Code

* id
* code
* expires_at
* used
* elder_id (FK → users)

## Caregiver Link

* id
* elder_id (FK → users)
* caregiver_id (FK → users)
* linked_at

## Medicine

* id
* name
* dosage
* frequency
* elder_id (FK → users)

## Reminder Log

* id
* medicine_id (FK)
* status
* scheduled_time

## Health Data

* id
* elder_id (FK)
* blood_pressure
* sugar_level
* weight

---

# 🛡️ Security Design (Planned Implementation)

Currently:

* Basic validation implemented

Future Security Enhancements:

## 🔐 JWT Authentication

* Login endpoint
* Generate JWT token
* Token required for API access
* Stateless authentication

## 🔒 Role-Based Authorization

* ELDER → Manage own medicines & health
* CAREGIVER → View linked elders only
* Admin (optional future role)

## 🔑 Password Encryption

* Use BCryptPasswordEncoder
* Store hashed passwords only

## 🚫 Endpoint Protection

* Secure endpoints using Spring Security
* Prevent unauthorized access

---

# 🌐 Future Enhancements

## 1️⃣ Frontend Integration

Planned:

* React.js Web Dashboard
* Mobile app (Flutter / React Native)

Features:

* Elder dashboard
* Caregiver dashboard
* Real-time reminder tracking
* Graphical health reports

---

## 2️⃣ OCR Medicine Scanner (Advanced Feature)

Future feature:

* Upload prescription image
* Extract medicine names using OCR (Tesseract / Google Vision API)
* Auto-fill medicine form

Workflow:

1. Upload image
2. OCR extracts text
3. Parse medicine names
4. Suggest medicines automatically

---

## 3️⃣ Email Invite Integration

Instead of manual code sharing:

* Elder enters caregiver email
* System sends invite link via email
* One-click connection

---

## 4️⃣ Notification System

* SMS reminders
* Push notifications
* Email reminders

---

## 5️⃣ Production Deployment

Planned:

* Docker containerization
* AWS / Azure deployment
* CI/CD pipeline
* Cloud database

---

# 🧪 Testing Strategy

Manual Testing:

* Postman API testing
* Positive & negative test cases

Future:

* JUnit unit tests
* Integration tests
* Swagger API documentation

---

# 🚀 How To Run

1. Configure MySQL database
2. Update application.properties
3. Run:

```
mvn spring-boot:run
```

Server:

```
http://localhost:8080
```

---

# 📈 Scalability Vision

This project is designed to evolve into:

* Full-stack healthcare monitoring system
* Multi-elder caregiver management
* AI-powered health prediction
* Real-time analytics dashboard

---

# 👨‍💻 Developer Notes

This project demonstrates:

* Clean layered architecture
* Proper DTO usage
* Entity relationships
* Exception handling
* Secure invite-based linking system
* Scalable backend design

---

# 🏁 Conclusion

ElderCare backend provides a strong foundation for a secure and scalable elder health monitoring system.

With future implementation of:

* JWT Security
* Frontend dashboard
* OCR integration
* Cloud deployment

This project can evolve into a production-ready healthcare support platform.
