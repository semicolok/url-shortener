package com.jake.url.shortener.repository;

import com.jake.url.shortener.repository.url.ShortUrl;
import com.jake.url.shortener.repository.url.ShortUrlRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@ExtendWith(SpringExtension.class)
@Transactional
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class ShortUrlRepositoryIntegrationTest {

    private static final String ANY_SHORT_URL = "https://anyshorturl/DLSE12A82LE";
    private static final int ANY_LONG_URL_LENGTH = 2629;

    @Autowired
    ShortUrlRepository shortUrlRepository;

    @Disabled
    @Test
    void getOne() {
        final ShortUrl shortUrl = shortUrlRepository.getOne(ANY_SHORT_URL);

        assertEquals(ANY_SHORT_URL, shortUrl.getShortUrl());
        assertEquals(ANY_LONG_URL_LENGTH, shortUrl.getOriginalUrl().length());
    }
}