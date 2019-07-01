package com.jake.url.shortener.component;

import com.jake.url.shortener.controller.dto.GenerateShortUrlResponseDto;
import com.jake.url.shortener.exception.UrlNotFoundException;
import com.jake.url.shortener.repository.url.ShortUrl;
import com.jake.url.shortener.repository.url.ShortUrlMapper;
import com.jake.url.shortener.repository.url.ShortUrlRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class UrlShortener {

    private static final String SLASH = "/";

    private final String shortRootUrl;
    private final ShortUrlRepository shortUrlRepository;
    private final ShortUrlMapper shortUrlMapper;

    public UrlShortener(@Value("${url.short.root}") String shortRootUrl,
                        ShortUrlRepository shortUrlRepository,
                        ShortUrlMapper shortUrlMapper) {

        this.shortRootUrl = shortRootUrl;
        this.shortUrlRepository = shortUrlRepository;
        this.shortUrlMapper = shortUrlMapper;
    }

    public GenerateShortUrlResponseDto generateShortUrl(final String shortUrlKey, final String originalUrl) {
        final ShortUrl shortUrl = shortUrlRepository.save(ShortUrl.of(buildShortUrlWithKey(shortUrlKey), originalUrl));

        return shortUrlMapper.toGenerateShortUrlResponseDto(shortUrl);
    }

    public String getOriginalUrlByShortUrlKey(final String shortUrlKey) {
        Assert.hasText(shortUrlKey, "'shortUrlKey' must not be empty.");

        final String fullShortUrl = buildShortUrlWithKey(shortUrlKey);
        final ShortUrl shortUrl = shortUrlRepository.findById(fullShortUrl)
                .orElseThrow(() -> new UrlNotFoundException("Not found URL with " + fullShortUrl));

        return shortUrl.getOriginalUrl();
    }

    private String buildShortUrlWithKey(final String shortUrlKey) {
        Assert.hasText(shortUrlKey, "'shortUrlKey' must not be empty.");

        return shortRootUrl + SLASH + shortUrlKey;
    }
}
