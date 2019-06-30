package com.jake.url.shortener.component;

import com.jake.url.shortener.repository.url.ShortUrlMapper;
import com.jake.url.shortener.repository.url.ShortUrlRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UrlShortenerTest {

    UrlShortener urlShortener;

    @BeforeEach
    void init(@Mock ShortUrlRepository shortUrlRepository, @Mock ShortUrlMapper shortUrlMapper) {
        urlShortener = new UrlShortener("", shortUrlRepository, shortUrlMapper);

//        when(shortUrlRepository.save());
    }

    @Test
    void generateShortUrl() {
    }

    @Test
    void getOriginalUrlByShortUrlKey() {

        System.out.println("asdfasdf");
    }
}