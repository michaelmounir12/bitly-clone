package com.example.bitly_clone;

import com.example.bitly_clone.domain.entities.Urls;
import com.example.bitly_clone.domain.repos.UrlRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class UrlRepositoryTest {
    @Autowired
    private UrlRepo urlRepo;

    @Test
    void findByShortUrl_existingShortUrl_returnsUrl() {

        Urls url = new Urls();
        url.setOriginalUrl("https://example.com");
        url.setShortUrl("abc123");
        url.setExpiresAt(LocalDateTime.now().plusDays(1));

        urlRepo.save(url);

        Optional<Urls> result =
                urlRepo.findByShortUrl("abc123");

        assertTrue(result.isPresent());
        assertEquals("https://example.com",
                result.get().getOriginalUrl());
        assertEquals("abc123",
                result.get().getShortUrl());
    }

    @Test
    void findByShortUrl_nonExistingShortUrl_returnsEmpty() {

        Optional<Urls> result =
                urlRepo.findByShortUrl("does-not-exist");

        assertTrue(result.isEmpty());
    }

    @Test
    void findByOriginalUrl_existingUrl_returnsUrl() {

        Urls url = new Urls();
        url.setOriginalUrl("https://example.com");
        url.setShortUrl("abc123");
        url.setExpiresAt(LocalDateTime.now().plusDays(1));

        urlRepo.save(url);

        Optional<Urls> result =
                urlRepo.findByOriginalUrl("https://example.com");

        assertTrue(result.isPresent());
        assertEquals("abc123",
                result.get().getShortUrl());
    }

    @Test
    void findByOriginalUrl_nonExistingUrl_returnsEmpty() {

        Optional<Urls> result =
                urlRepo.findByOriginalUrl("https://does-not-exist.com");

        assertTrue(result.isEmpty());
    }
}
