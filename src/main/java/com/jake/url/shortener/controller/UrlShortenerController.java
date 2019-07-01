package com.jake.url.shortener.controller;

import com.jake.url.shortener.component.UrlShortener;
import com.jake.url.shortener.controller.dto.GenerateShortUrlRequestDto;
import com.jake.url.shortener.controller.dto.GenerateShortUrlResponseDto;
import com.jake.url.shortener.exception.UrlShortenerException;
import com.jake.url.shortener.service.ShortUrlKeyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Controller
public class UrlShortenerController {

    private final ShortUrlKeyService shortUrlKeyService;
    private final UrlShortener urlShortener;

    public UrlShortenerController(ShortUrlKeyService shortUrlBase62KeyService, UrlShortener urlShortener) {
        this.shortUrlKeyService = shortUrlBase62KeyService;
        this.urlShortener = urlShortener;
    }

    @GetMapping("/{shortUrlKey}")
    public String redirectToOriginalUrl(@PathVariable String shortUrlKey, ModelMap modelMap) {
        try {
            final String originalUrl = urlShortener.getOriginalUrlByShortUrlKey(shortUrlKey);
            return "redirect:" + originalUrl;
        } catch (UrlShortenerException e) {
            modelMap.put("errorMessage", e.getMessage());
            return "error/404";
        }
    }

    @PostMapping("/url-shorteners")
    public ResponseEntity<GenerateShortUrlResponseDto> generateShortUrl(@Valid @RequestBody GenerateShortUrlRequestDto requestDto) {
        final String shortUrlKey = shortUrlKeyService.generateKey();
        final GenerateShortUrlResponseDto responseDto = urlShortener.generateShortUrl(shortUrlKey, requestDto.getUrl());

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
