package com.example.bitly_clone;

import com.example.bitly_clone.domain.entities.Urls;
import com.example.bitly_clone.domain.repos.UrlRepo;
import com.example.bitly_clone.domain.service.UrlService;
import com.example.bitly_clone.domain.service.mappers.UrlMapper;
import com.example.bitly_clone.web.models.UrlRequest;
import com.example.bitly_clone.web.models.UrlResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UrlServiceTest {

    @Mock
    private UrlRepo urlRepo;

    @Mock
    private UrlMapper urlMapper;

    @InjectMocks
    private UrlService urlService;

    @Test
    void shortenUrl_validNewUrl_returnsShortenedUrl() throws Exception {

        String url = "http://test.com";

        UrlRequest request = new UrlRequest();
        request.setLongUrl(url);

        when(urlRepo.findByOriginalUrl(url))
                .thenReturn(Optional.empty());

        Urls savedEntity = new Urls();
        savedEntity.setId(1L);
        savedEntity.setOriginalUrl(url);
        savedEntity.setExpiresAt(LocalDateTime.now().plusYears(1));

        Urls finalEntity = new Urls();
        finalEntity.setId(1L);
        finalEntity.setOriginalUrl(url);
        finalEntity.setExpiresAt(savedEntity.getExpiresAt());
        finalEntity.setShortUrl("1");

        when(urlRepo.save(any(Urls.class)))
                .thenReturn(savedEntity)
                .thenReturn(finalEntity);

        UrlResponse expectedResponse = new UrlResponse();
        expectedResponse.setShortUrl("1");
        expectedResponse.setOriginalUrl(url);

        when(urlMapper.urlToUrlResponse(finalEntity))
                .thenReturn(expectedResponse);

        UrlResponse result = urlService.shortenUrl(request);

        assertEquals(expectedResponse.getShortUrl(), result.getShortUrl());
        assertEquals(expectedResponse.getOriginalUrl(), result.getOriginalUrl());
    }

    @Test
    void shortenUrl_notHttpUrl_throwsAnException() {

        String url = "tcp://test.com";

        UrlRequest request = new UrlRequest();
        request.setLongUrl(url);

        Exception exception = assertThrows(
                Exception.class,
                () -> urlService.shortenUrl(request)
        );

        assertEquals("not a valid url", exception.getMessage());
    }

    @Test
    void getOriginalUrl_validShortUrl_returnsLongUrl() {

        Urls url = new Urls();
        url.setShortUrl("1");
        url.setOriginalUrl("http://test.com");
        url.setExpiresAt(LocalDateTime.now().plusDays(1));

        when(urlRepo.findByShortUrl("1"))
                .thenReturn(Optional.of(url));

        String response = urlService.getOriginalUrl("1");

        assertEquals("http://test.com", response);
    }


}
