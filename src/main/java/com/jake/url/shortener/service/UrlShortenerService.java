package com.jake.url.shortener.service;

import com.jake.url.shortener.repository.url.ShortUrl;
import com.jake.url.shortener.repository.url.ShortUrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class UrlShortenerService {

    private final ShortUrlRepository shortUrlRepository;
    private final String shortRootUrl;

    public UrlShortenerService(@Value("${url.short.root}") String shortRootUrl, ShortUrlRepository shortUrlRepository) {
        this.shortUrlRepository = shortUrlRepository;
        this.shortRootUrl = shortRootUrl;
    }

    public String generateShortUrl(String shortUrlKey, String originalUrl) {

        final String url = shortRootUrl + shortUrlKey;

        final ShortUrl shortUrl = ShortUrl.of(url, originalUrl);
        shortUrlRepository.save(shortUrl);

        return shortUrl.getShortUrl();
    }
}
