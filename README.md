🏷️ Project Title

Bitly Clone — URL Shortener with Redis Rate Limiting

📖 Overview

A URL shortener web app built with Spring Boot, PostgreSQL, Redis, and a simple React frontend.
Users can shorten URLs, track clicks, and prevent abuse using a Redis-based rate limiter.

🚀 Features

✅ Shorten long URLs into compact links
✅ Redirect users automatically via short codes
✅ Track detailed click analytics (IP, user agent, country, timestamp)
✅ Rate limiting per IP using Redis
✅ Caching for performance
✅ REST API + web UI
✅ Dockerized for easy deployment

🏗️ Tech Stack
Layer	Technologies
Backend	Spring Boot, JPA, Redis, PostgreSQL
Frontend	React
DevOps	Docker, Docker Compose
Others	Lombok, MapStruct, REST APIs
⚙️ Installation & Setup
Clone the repo
git clone https://github.com/michaelmounir12/bitly-clone.git
cd bitly-clone

Run the backend with Docker
docker-compose up --build
Run the fronted 
cd frontend
npm install
npm run dev

Your app will be available at http://localhost:5173

🧩 API Endpoints 
Method	Endpoint	Description
POST	/api/shorten	Create short URL
GET	/{shortCode}	Redirect to original URL
GET	/api/urls/clicks/{shortcode}	Get all clicks for that url
GET	/api/clicks/{shortCode}/count	get clicks count
📊 Redis Rate Limiting

Each IP address is limited to 100 clicks per minute per each user.
If exceeded, the system rejects further clicks for that day to prevent spam.

💾 Database Schema

urls — stores original & short URLs, creation time, expiration date

clicks — stores analytics data (IP, user agent, timestamp, country)

🧠 Architecture Overview

Explain briefly:

The backend handles URL generation and analytics.
Redis is used for caching and rate limiting.
PostgreSQL stores persistent data.
Docker Compose spins up all services easily.


🖼️ Screenshots / Demo




🧑‍💻 Author

Michael Mounir

🎓 Computer Science @ Ain Shams University

💼 Aspiring Backend Engineer

🌐 LinkedIn Profile
