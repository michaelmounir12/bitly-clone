package com.example.bitly_clone.domain.service;


import com.example.bitly_clone.domain.entities.Clicks;
import com.example.bitly_clone.domain.entities.Urls;
import com.example.bitly_clone.domain.repos.ClickRepo;
import com.example.bitly_clone.domain.repos.UrlRepo;
import com.example.bitly_clone.domain.service.mappers.ClickMapper;
import com.example.bitly_clone.web.models.ClickResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.json.JSONObject;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class ClickService {

    private final ClickRepo clickRepo;
    private final UrlRepo urlRepo;
    private final ClickMapper clickMapper;
    public void recordClick(String shortCode, HttpServletRequest request) {
        Urls url = urlRepo.findByShortUrl(shortCode)
                .orElseThrow(() -> new RuntimeException("URL not found"));

        Clicks click = new Clicks();
        click.setUrl(url);
        click.setClickedAt(LocalDateTime.now());
        click.setIpAddress(request.getRemoteAddr());
        click.setUserAgent(request.getHeader("User-Agent"));

        String country = getCountryFromIp(request.getRemoteAddr());
        click.setCountry(country);

        clickRepo.save(click);
    }

    public List<ClickResponse> getClicks(String shortCode) {
        Urls url = urlRepo.findByShortUrl(shortCode)
                .orElseThrow(() -> new RuntimeException("URL not found"));
        List<Clicks> clicks = clickRepo.findByUrl(url);
        return clickMapper.toClickResponseList(clicks);
    }

    public long getClickCount(String shortCode) {
        Urls url = urlRepo.findByShortUrl(shortCode)
                .orElseThrow(() -> new RuntimeException("URL not found"));
        return clickRepo.countByUrl(url);
    }

    private String getCountryFromIp(String ipAddress) {
        try {
            if (ipAddress.equals("0:0:0:0:0:0:0:1") || ipAddress.equals("127.0.0.1")) {
                return "Localhost";
            }

            String url = "https://ipapi.co/" + ipAddress + "/json/";
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

            JSONObject json = new JSONObject(response.getBody());
            return json.optString("country_name", "Unknown");
        } catch (Exception e) {
            return "Unknown";
        }
    }

}
