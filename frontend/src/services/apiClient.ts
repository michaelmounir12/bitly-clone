const DEFAULT_BASE_URL = '';

function getBaseUrl(): string {
	const fromEnv = import.meta.env.VITE_API_BASE_URL as string | undefined;
	const chosen = (fromEnv && fromEnv.trim().length > 0) ? fromEnv : DEFAULT_BASE_URL;
	return chosen.replace(/\/$/, '');
}

function buildUrl(path: string): string {
	const base = getBaseUrl();
	return `${base}${path.startsWith('/') ? path : `/${path}`}`;
}

type UrlRequest = { longUrl: string };
type UrlResponse = {
	shortUrl: string;
	originalUrl: string;
	createdAt?: string;
	expiresAt?: string;
};

export async function shortenUrl(body: UrlRequest): Promise<UrlResponse> {
	const res = await fetch(buildUrl('/api/shorten'), {
		method: 'POST',
		headers: { 'Content-Type': 'application/json' },
		body: JSON.stringify(body),
	});
	if (!res.ok) {
		throw new Error(`Shorten failed: ${res.status}`);
	}
	return res.json();
}

export async function getClickCount(shortCode: string): Promise<number> {
	const res = await fetch(buildUrl(`/api/clicks/${encodeURIComponent(shortCode)}/count`));
	if (!res.ok) {
		throw new Error(`Get count failed: ${res.status}`);
	}
	return res.json();
}

export async function getClicks(shortCode: string): Promise<any[]> {
	const res = await fetch(buildUrl(`/api/clicks/${encodeURIComponent(shortCode)}`));
	if (!res.ok) {
		throw new Error(`Get clicks failed: ${res.status}`);
	}
	return res.json();
}





