package com.example.bitly_clone.domain.repos;

import com.example.bitly_clone.domain.entities.Urls;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UrlRepo extends JpaRepository<Urls,Long> {
    Optional<Urls> findByOriginalUrl(String originalUrl);
    Optional<Urls> findByShortUrl(String url);
}
