# URL Shortener UI

React + TypeScript UI for your URL shortener backend.

## Running

1. Install dependencies:

```
npm install
```

2. Start dev server:

```
npm run dev
```

The app will open on http://localhost:5173

## Config

Set `VITE_API_BASE_URL` to point to your backend (defaults to `http://localhost:8080`). Create `.env` in project root:

```
VITE_API_BASE_URL=http://localhost:8080
```

## Features

- Create short URLs via `POST /api/shorten`
- View analytics: `GET /api/clicks/{shortCode}/count` and list `GET /api/clicks/{shortCode}`



