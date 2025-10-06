package com.example.bitly_clone.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class ClickLimiterService {

    private static final String CLICK_KEY_PREFIX = "url_clicks:";
    private static final int MAX_CLICKS = 100;

    @Autowired
    private StringRedisTemplate redisTemplate;

    public boolean registerClick(String urlId,String ipAddress) {
        String key = CLICK_KEY_PREFIX + urlId + ":ip:" + ipAddress;


        Long count = redisTemplate.opsForValue().increment(key);

        if (count != null && count == 1) {
            redisTemplate.expire(key, Duration.ofMinutes(1));
        }

        return count != null && count <= MAX_CLICKS;
    }
}
