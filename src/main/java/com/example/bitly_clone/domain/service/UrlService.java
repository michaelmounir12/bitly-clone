package com.example.bitly_clone.domain.service;

import com.example.bitly_clone.domain.entities.Urls;
import com.example.bitly_clone.domain.repos.UrlRepo;
import com.example.bitly_clone.domain.service.mappers.UrlMapper;
import com.example.bitly_clone.web.models.UrlRequest;
import com.example.bitly_clone.web.models.UrlResponse;
import io.seruco.encoding.base62.Base62;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigInteger;
import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UrlService {

    private UrlRepo urlRepo;
    private UrlMapper urlMapper;
    public UrlResponse shortenUrl(UrlRequest urlRequest) throws Exception{
        String longUrl = urlRequest.getLongUrl();
//      Validate syntax
        if(!isValidUrl(longUrl)) throw new Exception("not a valid url");
 //     Enforce http/https.
        if(!isHttpOrHttps(longUrl)) throw new Exception("not a valid url");
//      Block private/internal IPs.
        if(isInternalAddress(longUrl)) throw new Exception("not a valid url");
//      Enforce max length.
        if(longUrl.length() > 2000) throw new Exception("not a valid url");
//      Normalize (lowercase host, trim).
        longUrl = normalizeUrl(longUrl);
//      Check if it already exists in DB.
        Optional<Urls> existing = urlRepo.findByOriginalUrl(longUrl);
        if(existing.isPresent()) {
           return urlMapper.urlToUrlResponse(existing.get());
        }
        System.out.println(longUrl);
        Urls url = new Urls();
        url.setOriginalUrl(longUrl);
        url.setExpiresAt(LocalDateTime.now().plusYears(1));
        Urls saved = urlRepo.save(url);
        byte[] input = BigInteger.valueOf(saved.getId()).toByteArray();

        String shortCode = new String(Base62.createInstance().encode(input));
        saved.setShortUrl(shortCode);
        saved = urlRepo.save(saved);
        return urlMapper.urlToUrlResponse(saved);


    }
    public String getUrl(String shortCode) {
        Urls url = urlRepo.findByShortUrl(shortCode)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "URL not found"));

        if (url.getExpiresAt() != null && url.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new ResponseStatusException(HttpStatus.GONE, "URL expired");
        }

        return url.getOriginalUrl();
    }

    public boolean isValidUrl(String url) {
        try {
            new URL(url).toURI();  // parse & validate syntax
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isHttpOrHttps(String url) {
        try {
            URL parsed = new URL(url);
            String protocol = parsed.getProtocol().toLowerCase();
            return protocol.equals("http") || protocol.equals("https");
        } catch (Exception e) {
            return false;
        }
    }
    public boolean isInternalAddress(String url){
        try {
            URL parsed = new URL(url);
            String host = parsed.getHost();
            InetAddress inetAddress = InetAddress.getByName(host);
            String ip = inetAddress.getHostAddress();
            boolean isBlocked = inetAddress.isAnyLocalAddress()   // 0.0.0.0
                    || inetAddress.isLoopbackAddress() // 127.0.0.1
                    || inetAddress.isSiteLocalAddress(); // 10.x.x.x, 192.168.x.x, 172.16–31.x.x
            return isBlocked;
        }
        catch (Exception e){
           return false;
        }


    }
    public static String normalizeUrl(String url) throws URISyntaxException {
        URI uri = new URI(url.trim()); // trim whitespace

        String scheme = uri.getScheme().toLowerCase();
        String host = uri.getHost().toLowerCase();

        int port = uri.getPort();
        // Remove default ports
        if ((scheme.equals("http") && port == 80) ||
                (scheme.equals("https") && port == 443)) {
            port = -1;
        }

        // Rebuild normalized URL
        URI normalized = new URI(
                scheme,
                null,
                host,
                port,
                uri.getPath() != null && uri.getPath().equals("/") ? "" : uri.getPath(), // handle root slash
                uri.getQuery(),
                uri.getFragment()
        );

        return normalized.toString();
    }
}
