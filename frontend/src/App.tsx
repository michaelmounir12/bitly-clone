import { Link, NavLink, Route, Routes } from 'react-router-dom';
import ShortenPage from './pages/ShortenPage';
import AnalyticsPage from './pages/AnalyticsPage';

export default function App() {
	return (
		<div className="container">
			<header className="header">
				<Link to="/" className="brand">URL Shortener</Link>
				<nav className="nav">
					<NavLink to="/" end className={({ isActive }) => isActive ? 'active' : ''}>
						Home
					</NavLink>
					<NavLink to="/analytics" className={({ isActive }) => isActive ? 'active' : ''}>
						Analytics
					</NavLink>
				</nav>
			</header>
			<main className="main">
				<Routes>
					<Route path="/" element={<ShortenPage />} />
					<Route path="/analytics" element={<AnalyticsPage />} />
				</Routes>
			</main>
			<footer className="footer">Backend: {import.meta.env.VITE_API_BASE_URL || 'http://localhost:8080'}</footer>
		</div>
	);
}





