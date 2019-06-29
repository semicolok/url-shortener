package com.jake.url.shortener.controller;

import com.jake.url.shortener.controller.dto.GenerateShortUrlRequestDto;
import com.jake.url.shortener.controller.dto.GenerateShortUrlResponseDto;
import com.jake.url.shortener.service.ShortUrlKeyService;
import com.jake.url.shortener.service.UrlShortenerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Controller
public class UrlShortenerController {

    private final ShortUrlKeyService shortUrlKeyService;
    private final UrlShortenerService urlShortenerService;

    public UrlShortenerController(ShortUrlKeyService shortUrlBase62KeyService, UrlShortenerService urlShortenerService) {
        this.shortUrlKeyService = shortUrlBase62KeyService;
        this.urlShortenerService = urlShortenerService;
    }

    @GetMapping("/{shortUrlKey}")
    public String redirectToOriginalUrl(@PathVariable String shortUrlKey) {
        final String originalUrl = urlShortenerService.getOriginalUrlByShortUrlKey(shortUrlKey);

        return "redirect:" + originalUrl;
    }

    @PostMapping("/url-shorteners")
    public ResponseEntity<GenerateShortUrlResponseDto> generateShortUrl(@Valid @RequestBody GenerateShortUrlRequestDto requestDto) {
        final String shortUrlKey = shortUrlKeyService.generateKey();
        final GenerateShortUrlResponseDto responseDto = urlShortenerService.generateShortUrl(shortUrlKey, requestDto.getUrl());

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
