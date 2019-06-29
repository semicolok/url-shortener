package com.jake.url.shortener.controller;

import com.jake.url.shortener.controller.dto.GenerateShortUrlRequestDto;
import com.jake.url.shortener.service.ShortUrlKeyService;
import com.jake.url.shortener.service.UrlShortenerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UrlShortenerController {

    private final ShortUrlKeyService shortUrlKeyService;
    private final UrlShortenerService urlShortenerService;

    public UrlShortenerController(ShortUrlKeyService shortUrlBase62KeyService, UrlShortenerService urlShortenerService) {
        this.shortUrlKeyService = shortUrlBase62KeyService;
        this.urlShortenerService = urlShortenerService;
    }

    @PostMapping("/url-shortener")
    public ResponseEntity<String> generateShortUrl(@Valid @RequestBody GenerateShortUrlRequestDto generateShortUrlRequestDto) {
        final String shortUrlKey = shortUrlKeyService.generateKey();
        final String shortUrl = urlShortenerService.generateShortUrl(shortUrlKey, generateShortUrlRequestDto.getUrl());

        return new ResponseEntity<>(shortUrl, HttpStatus.OK);
    }
}
