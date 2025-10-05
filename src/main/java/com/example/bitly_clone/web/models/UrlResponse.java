package com.example.bitly_clone.web.models;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UrlResponse {
    private String shortUrl;
    private String originalUrl;
    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;
    private long clicks;
}
