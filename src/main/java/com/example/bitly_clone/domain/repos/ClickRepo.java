package com.example.bitly_clone.domain.repos;


import com.example.bitly_clone.domain.entities.Clicks;
import com.example.bitly_clone.domain.entities.Urls;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClickRepo extends JpaRepository<Clicks, Long> {
    List<Clicks> findByUrl(Urls url);
    long countByUrl(Urls url);
}
