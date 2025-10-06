import { FormEvent, useState } from 'react';
import { getClickCount, getClicks } from '../services/apiClient';

type ClickResponse = {
	clickedAt: string;
	userAgent?: string;
	country?: string;
	url?: {
		shortUrl: string;
		originalUrl: string;
	};
};

export default function AnalyticsPage() {
	const [shortCode, setShortCode] = useState('');
	const [loading, setLoading] = useState(false);
	const [error, setError] = useState<string | null>(null);
	const [count, setCount] = useState<number | null>(null);
	const [clicks, setClicks] = useState<ClickResponse[] | null>(null);

	async function onQuery(e: FormEvent) {
		e.preventDefault();
		setError(null);
		setLoading(true);
		try {
			const [c, list] = await Promise.all([
				getClickCount(shortCode),
				getClicks(shortCode),
			]);
			setCount(c);
			setClicks(list);
		} catch (err: any) {
			setError(err?.message || 'Failed to fetch analytics');
		} finally {
			setLoading(false);
		}
	}

	return (
		<section>
			<h1>Analytics</h1>
			<form onSubmit={onQuery} className="card row">
				<input
					value={shortCode}
					onChange={(e) => setShortCode(e.target.value.trim())}
					placeholder="Enter short code e.g. abc123"
					required
				/>
				<button type="submit" disabled={!shortCode || loading}>
					{loading ? 'Loading…' : 'Query'}
				</button>
			</form>
			{error && <div className="error">{error}</div>}
			{count !== null && (
				<div className="card">
					<h2>Total Clicks: {count}</h2>
				</div>
			)}
			{clicks && clicks.length > 0 && (
				<div className="card">
					<h3>Recent Clicks</h3>
					<ul className="list">
						{clicks.map((c, idx) => (
							<li key={idx} className="list-item">
								<div>{new Date(c.clickedAt).toLocaleString()}</div>
								<div className="muted">{c.country || 'Unknown country'}</div>
								<div className="muted" title={c.userAgent}>{c.userAgent?.slice(0, 60) || 'Unknown agent'}</div>
							</li>
						))}
					</ul>
				</div>
			)}
		</section>
	);
}





