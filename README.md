# 🏷️ Project Title
**Bitly Clone — URL Shortener with Redis Rate Limiting**

---

## 📖 Overview
A URL shortener web app built with **Spring Boot**, **PostgreSQL**, **Redis**, and a simple **React frontend**.  
Users can shorten URLs, track clicks, and prevent abuse using a Redis-based rate limiter.

---

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
2️⃣ Run the backend with Docker
bashdocker-compose up --build
3️⃣ Run the frontend
bashcd frontend
npm install
npm run dev
Your app will be available at 👉 http://localhost:5173

🧩 API Endpoints
MethodEndpointDescriptionPOST/api/shortenCreate short URLGET/{shortCode}Redirect to original URLGET/api/urls/clicks/{shortcode}Get all clicks for that URLGET/api/clicks/{shortCode}/countGet clicks count

📊 Redis Rate Limiting

Each IP address is limited to 100 clicks per minute per user.
If exceeded, the system rejects further clicks for that day to prevent spam.


💾 Database Schema

urls — stores original & short URLs, creation time, expiration date
clicks — stores analytics data (IP, user agent, timestamp, country)


🧠 Architecture Overview

The backend handles URL generation and analytics.
Redis is used for caching and rate limiting.
PostgreSQL stores persistent data.
Docker Compose spins up all services easily.


🖼️ Screenshots / Demo
<img width="957" height="377" alt="bitlyclone 2" src="https://github.com/user-attachments/assets/d50c52ae-744c-456a-91f4-1c6a633bac06" />

<img width="938" height="368" alt="bitly clone3" src="https://github.com/user-attachments/assets/a6b328a8-caa2-4f3e-bc06-ff0b56352743" />


🧑‍💻 Author
Michael Mounir
🎓 Computer Science @ Ain Shams University
💼 Aspiring Backend Engineer
🌐 LinkedIn Profile
