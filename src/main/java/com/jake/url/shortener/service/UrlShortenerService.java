package com.jake.url.shortener.service;

import com.jake.url.shortener.repository.url.ShortUrl;
import com.jake.url.shortener.repository.url.ShortUrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UrlShortenerService {

    private final ShortUrlRepository shortUrlRepository;

    public UrlShortenerService(ShortUrlRepository shortUrlRepository) {
        this.shortUrlRepository = shortUrlRepository;
    }

    public String generateShortUrl(String shortUrlKey, String originalUrl) {

        final String url = "http://shrturl.com/" + shortUrlKey;

        final ShortUrl shortUrl = ShortUrl.of(url, originalUrl);
        shortUrlRepository.save(shortUrl);

        return shortUrl.getShortUrl();
    }
}
