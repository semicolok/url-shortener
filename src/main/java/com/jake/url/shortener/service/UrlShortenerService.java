package com.jake.url.shortener.service;

import com.jake.url.shortener.controller.dto.GenerateShortUrlResponseDto;
import com.jake.url.shortener.exception.UrlShortenerException;
import com.jake.url.shortener.repository.url.ShortUrl;
import com.jake.url.shortener.repository.url.ShortUrlMapper;
import com.jake.url.shortener.repository.url.ShortUrlRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class UrlShortenerService {

    private final String shortRootUrl;
    private final ShortUrlRepository shortUrlRepository;
    private final ShortUrlMapper shortUrlMapper;

    public UrlShortenerService(@Value("${url.short.root}") String shortRootUrl, ShortUrlRepository shortUrlRepository, ShortUrlMapper shortUrlMapper) {
        this.shortRootUrl = shortRootUrl;
        this.shortUrlRepository = shortUrlRepository;
        this.shortUrlMapper = shortUrlMapper;
    }

    public GenerateShortUrlResponseDto generateShortUrl(String shortUrlKey, String originalUrl) {
        final ShortUrl shortUrl = shortUrlRepository.save(ShortUrl.of(buildShortUrlWithKey(shortUrlKey), originalUrl));

        return shortUrlMapper.toGenerateShortUrlResponseDto(shortUrl);
    }

    public String getOriginalUrlByShortUrlKey(String shortUrlKey) {
        final ShortUrl shortUrl = shortUrlRepository.findById(buildShortUrlWithKey(shortUrlKey))
                .orElseThrow(() -> new UrlShortenerException("Not found URL with shortUrlKey: " + shortUrlKey, HttpStatus.NOT_FOUND));

        return shortUrl.getOriginalUrl();
    }

    private String buildShortUrlWithKey(String shortUrlKey) {
        if (StringUtils.isBlank(shortUrlKey)) {
            throw new IllegalArgumentException("'shortUrlKey' must not be empty.");
        }

        return shortRootUrl + shortUrlKey;
    }
}
