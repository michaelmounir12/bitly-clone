package com.example.bitly_clone.web.models;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ClickResponse {

    private UrlResponse url;

    private LocalDateTime clickedAt;



    private String userAgent;

    private String country;


}
