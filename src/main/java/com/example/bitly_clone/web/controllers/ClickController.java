package com.example.bitly_clone.web.controllers;

import com.example.bitly_clone.domain.entities.Clicks;
import com.example.bitly_clone.domain.service.ClickService;
import com.example.bitly_clone.web.models.ClickResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clicks")
@AllArgsConstructor
public class ClickController {

    private final ClickService clickService;



    @GetMapping("/{shortCode}")
    public ResponseEntity<List<ClickResponse>> getClicks(@PathVariable String shortCode) {
        List<ClickResponse> clicks = clickService.getClicks(shortCode);
        return ResponseEntity.ok(clicks);
    }

    @GetMapping("/{shortCode}/count")
    public ResponseEntity<Long> getClickCount(@PathVariable String shortCode) {
        long count = clickService.getClickCount(shortCode);
        return ResponseEntity.ok(count);
    }
}
