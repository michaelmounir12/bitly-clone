package com.example.bitly_clone.web.controllers;

import com.example.bitly_clone.domain.service.UrlService;
import com.example.bitly_clone.web.models.UrlRequest;
import com.example.bitly_clone.web.models.UrlResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class UrlApiController {

    private final UrlService urlService;

    @PostMapping("/shorten")
    public ResponseEntity<UrlResponse> shortenUrl(@RequestBody UrlRequest urlRequest) throws Exception {
        return ResponseEntity.ok(urlService.shortenUrl(urlRequest));
    }
}
