package com.example.bitly_clone;

import com.example.bitly_clone.domain.service.UrlService;
import com.example.bitly_clone.web.controllers.UrlApiController;
import com.example.bitly_clone.web.models.UrlRequest;
import com.example.bitly_clone.web.models.UrlResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@WebMvcTest(UrlApiController.class)
public class UrlControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private UrlService urlService;


    @Test
    void shortenUrl_validRequest_returnsShortUrl() throws Exception {

        UrlRequest request = new UrlRequest();
        request.setLongUrl("https://example.com");

        UrlResponse response = new UrlResponse();
        response.setOriginalUrl("https://example.com");
        response.setShortUrl("abc123");

        when(urlService.shortenUrl(any(UrlRequest.class)))
                .thenReturn(response);

        mockMvc.perform(
                        post("/api/shorten")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.originalUrl")
                        .value("https://example.com"))
                .andExpect(jsonPath("$.shortUrl")
                        .value("abc123"));
    }

}
