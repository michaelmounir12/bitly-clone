import { FormEvent, useMemo, useState } from 'react';
import { shortenUrl } from '../services/apiClient';

export default function ShortenPage() {
	const [longUrl, setLongUrl] = useState('');
	const [loading, setLoading] = useState(false);
	const [error, setError] = useState<string | null>(null);
	const [result, setResult] = useState<{
		shortUrl: string;
		originalUrl: string;
		createdAt?: string;
		expiresAt?: string;
	} | null>(null);

	const isValid = useMemo(() => {
		try {
			if (!longUrl) return false;
			new URL(longUrl);
			return true;
		} catch {
			return false;
		}
	}, [longUrl]);

	async function onSubmit(e: FormEvent) {
		e.preventDefault();
		setError(null);
		setResult(null);
		setLoading(true);
		try {
			const res = await shortenUrl({ longUrl });
			setResult(res);
		} catch (err: any) {
			setError(err?.message || 'Failed to shorten URL');
		} finally {
			setLoading(false);
		}
	}

	return (
		<section>
			<h1>Shorten a URL</h1>
			<form onSubmit={onSubmit} className="card">
				<label htmlFor="longUrl">Long URL</label>
				<input
					id="longUrl"
					type="url"
					placeholder="https://example.com/very/long/path"
					value={longUrl}
					onChange={(e) => setLongUrl(e.target.value)}
					required
				/>
				<button type="submit" disabled={!isValid || loading}>
					{loading ? 'Shortening…' : 'Shorten'}
				</button>
			</form>
			{error && <div className="error">{error}</div>}
			{result && (
				<div className="card">
					<h2>Short URL</h2>
					<p>
						<a href={result.shortUrl} target="_blank" rel="noreferrer">
							{result.shortUrl}
						</a>
					</p>
					<p>Original: {result.originalUrl}</p>
					{result.createdAt && <p>Created: {new Date(result.createdAt).toLocaleString()}</p>}
					{result.expiresAt && <p>Expires: {new Date(result.expiresAt).toLocaleString()}</p>}
				</div>
			)}
		</section>
	);
}





