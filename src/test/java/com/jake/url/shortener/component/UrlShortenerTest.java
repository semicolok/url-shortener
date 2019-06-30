package com.jake.url.shortener.component;

import com.jake.url.shortener.controller.dto.GenerateShortUrlResponseDto;
import com.jake.url.shortener.exception.UrlShortenerException;
import com.jake.url.shortener.repository.url.ShortUrl;
import com.jake.url.shortener.repository.url.ShortUrlMapper;
import com.jake.url.shortener.repository.url.ShortUrlRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UrlShortenerTest {

    final String originalUrl = "http://originalurl.com";

    UrlShortener urlShortener;

    @Mock
    ShortUrlRepository shortUrlRepository;

    @Mock
    ShortUrlMapper shortUrlMapper;

    @BeforeEach
    void init() {
        final String shortRootUrl = "http://localhost:8080";

        urlShortener = new UrlShortener(shortRootUrl, shortUrlRepository, shortUrlMapper);
    }

    @Test
    void generateShortUrl() {
        ShortUrl shortUrl = mock(ShortUrl.class);
        when(shortUrlRepository.save(any())).thenReturn(shortUrl);

        GenerateShortUrlResponseDto responseDto = mock(GenerateShortUrlResponseDto.class);
        when(responseDto.getShortUrl()).thenReturn("http://localhost:8080/b");
        when(shortUrlMapper.toGenerateShortUrlResponseDto(shortUrl)).thenReturn(responseDto);

        final GenerateShortUrlResponseDto generateShortUrlResponseDto = urlShortener.generateShortUrl("b", originalUrl);

        verify(shortUrlRepository, times(1)).save(any());
        verify(shortUrlMapper, times(1)).toGenerateShortUrlResponseDto(any());
        assertEquals("http://localhost:8080/b", generateShortUrlResponseDto.getShortUrl());
    }

    @Test
    void getOriginalUrlByShortUrlKey() {
        ShortUrl shortUrl = mock(ShortUrl.class);
        when(shortUrl.getOriginalUrl()).thenReturn(originalUrl);
        when(shortUrlRepository.findById(anyString())).thenReturn(Optional.of(shortUrl));

        final String url = urlShortener.getOriginalUrlByShortUrlKey("b");
        assertEquals(originalUrl, url);
    }

    @Test
    void getOriginalUrlByShortUrlKeyThrowException() {
        when(shortUrlRepository.findById(anyString())).thenThrow(UrlShortenerException.class);

        assertThrows(UrlShortenerException.class, () -> urlShortener.getOriginalUrlByShortUrlKey("b"));
    }
}