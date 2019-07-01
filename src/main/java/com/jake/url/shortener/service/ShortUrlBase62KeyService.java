package com.jake.url.shortener.service;

import com.jake.url.shortener.repository.key.ShortUrlKey;
import com.jake.url.shortener.repository.key.ShortUrlKeyRepository;
import com.jake.url.shortener.util.Base62Util;
import org.springframework.stereotype.Service;

@Service
public class ShortUrlBase62KeyService implements ShortUrlKeyService {

    private final ShortUrlKeyRepository shortUrlKeyRepository;

    public ShortUrlBase62KeyService(ShortUrlKeyRepository shortUrlKeyRepository) {
        this.shortUrlKeyRepository = shortUrlKeyRepository;
    }

    @Override
    public String generateKey() {
        final ShortUrlKey shortUrlKey = shortUrlKeyRepository.save(new ShortUrlKey());

        return Base62Util.encode(shortUrlKey.getUrlKey());
    }
}
