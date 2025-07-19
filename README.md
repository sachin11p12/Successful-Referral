# Referral System Backend

A simple Spring Boot-based backend system for user referrals. Users can refer others using a unique referral code. A referral is only considered **successful** when the referred user **completes their profile**. A CSV report of all users and their referrals can also be generated.

---

## 📌 Features

- User registration with unique referral codes
- Referral tracking
- Profile completion status
- Successful referral logic
- Generate CSV report of users and referrals

---

## 🛠️ Tech Stack

- Java 17
- Spring Boot
- PostgreSQL
- Lombok
- Maven
- Postman (for API testing)

---

## 🚀 Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/sachin-tiwari/referral-system.git
cd referral-system
```

### 2. Set up PostgreSQL

- Create a database:

```sql
CREATE DATABASE referral_db;
```

- Update the `application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/referral_db
spring.datasource.username=your_username
spring.datasource.password=your_password
```

### 3. Run the Application

- In IntelliJ: Run `ReferralSystemApplication.java`

- Or from terminal:

```bash
./mvnw spring-boot:run
```

---

## 📬 API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| `POST` | `/api/users` | Register a new user |
| `PUT` | `/api/users/{id}/complete-profile` | Mark user profile as completed |
| `GET` | `/api/users/report` | Download CSV of all users and their referrals |

---

## 🧪 Example cURL Request

```bash
curl -X POST http://localhost:8081/api/users -H "Content-Type: application/json" -d '{"name": "Aanand", "email": "Aanand@example.com"}'
```

---

## 📁 Output CSV

Sample Output:
```
User Name,Email,Referral Code,Referred By,Profile Completed
Aanand,aanand@example.com,1DB532AB,,true
Sita,sita@example.com,,Aanand,true
```

---

## ✅ Deployment Notes

- Can be deployed on any public server (Heroku, Railway, Render, EC2)
- Supports any SQL/NoSQL DB with slight modifications

---

## 🧠 Author

**Sachin Tiwari**  
B.Tech (IT)  
Email: `sachin11p12@gmail.com`  
GitHub: [[github.com/sachin-tiwari](https://github.com/sachin-tiwari](https://github.com/sachin11p12))



---

## ✅ Assignment Notes

- ✅ Public API tested using Postman and cURL
- ✅ Code is clean, readable, and well-commented
- ✅ Bonus: CSV report generation feature included

---
