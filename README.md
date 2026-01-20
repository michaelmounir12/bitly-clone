# 🏷️ Project Title
**Bitly Clone — URL Shortener with Redis Rate Limiting**

---

## 📖 Overview
A URL shortener web app built with **Spring Boot**, **PostgreSQL**, **Redis**, and a simple **React frontend**.  
Users can shorten URLs, track clicks, and prevent abuse using a Redis-based rate limiter.

---

## Archticture diagram 

<img width="1024" height="1024" alt="bitly arch" src="https://github.com/user-attachments/assets/12747d6e-d2e7-4cd8-8c94-8f015f5f547a" />



## 🚀 Features
- ✅ Shorten long URLs into compact links  
- ✅ Redirect users automatically via short codes  
- ✅ Track detailed click analytics (IP, user agent, country, timestamp)  
- ✅ Rate limiting per IP using Redis  
- ✅ Caching for performance  
- ✅ REST API + web UI  
- ✅ Dockerized for easy deployment  

---

## 🏗️ Tech Stack
| Layer | Technologies |
|-------|---------------|
| **Backend** | Spring Boot, JPA, Redis, PostgreSQL |
| **Frontend** | React |
| **DevOps** | Docker, Docker Compose |
| **Others** | Lombok, MapStruct, REST APIs |

---

## ⚙️ Installation & Setup

### 1️⃣ Clone the repo
```bash
git clone https://github.com/michaelmounir12/bitly-clone.git
cd bitly-clone
```

### 2️⃣ Run the backend with Docker
```bash
docker-compose up --build
```

### 3️⃣ Run the frontend
```bash
cd frontend
npm install
npm run dev
```

Your app will be available at 👉 http://localhost:5173

---

## 🧩 API Endpoints
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/shorten` | Create short URL |
| GET | `/{shortCode}` | Redirect to original URL |
| GET | `/api/urls/clicks/{shortcode}` | Get all clicks for that URL |
| GET | `/api/clicks/{shortCode}/count` | Get clicks count |

---

## 📊 Redis Rate Limiting
- Each IP address is limited to **100 clicks per minute** per user.
- If exceeded, the system rejects further clicks for that day to prevent spam.

---

## 💾 Database Schema
- **urls** — stores original & short URLs, creation time, expiration date
- **clicks** — stores analytics data (IP, user agent, timestamp, country)

---

## 🧠 Architecture Overview
- The backend handles URL generation and analytics.
- Redis is used for caching and rate limiting.
- PostgreSQL stores persistent data.
- Docker Compose spins up all services easily.

---

## 🖼️ Screenshots / Demo

![Bitly Clone Screenshot 1](https://github.com/user-attachments/assets/537b9c08-eb7a-4e4e-bc95-6a082547c384)

![Bitly Clone Screenshot 2](https://github.com/user-attachments/assets/43de3b1f-b542-4502-9dd4-d558fad6f1b0)

---

## 🧑‍💻 Author
**Michael Mounir**  
🎓 Computer Science @ Ain Shams University  
💼 Aspiring Backend Engineer  
🌐 [LinkedIn Profile](https://www.linkedin.com/in/michael-william-073092252/)

