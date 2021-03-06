package com.jake.url.shortener.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import com.jake.url.shortener.component.UrlShortener;
import com.jake.url.shortener.service.ShortUrlKeyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Map;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = UrlShortenerController.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UrlShortenerControllerTest {

    private static final String ANY_SHORT_URL_KEY = "e72kehxb";

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ShortUrlKeyService shortUrlKeyService;

    @MockBean
    UrlShortener urlShortener;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void redirectToOriginalUrl() throws Exception {
        mockMvc.perform(get("/{shortUrlKey}", ANY_SHORT_URL_KEY).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is3xxRedirection());

        verify(urlShortener, times(1)).getOriginalUrlByShortUrlKey(eq(ANY_SHORT_URL_KEY));
    }

    @Test
    void generateShortUrl() throws Exception {
        final String originalUrl = "http://originalurl.com/looooooooooong?length=long";

        final Map<String, String> requestMap = Maps.newHashMap();
        requestMap.put("url", originalUrl);

        when(shortUrlKeyService.generateKey()).thenReturn(ANY_SHORT_URL_KEY);

        mockMvc.perform(post("/url-shorteners").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(requestMap)))
                .andDo(print())
                .andExpect(status().isOk());

        verify(shortUrlKeyService, times(1)).generateKey();
        verify(urlShortener, times(1)).generateShortUrl(eq(ANY_SHORT_URL_KEY), eq(originalUrl));
    }
}