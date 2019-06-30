package com.jake.url.shortener.repository.url;

import com.google.common.collect.Lists;
import com.jake.url.shortener.controller.dto.GenerateShortUrlResponseDto;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mapstruct.factory.Mappers;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ShortUrlMapperTest {

    ShortUrlMapper shortUrlMapper = Mappers.getMapper(ShortUrlMapper.class);

    Collection<ShortUrl> shortUrlProvider() {
        List<ShortUrl> shortUrls = Lists.newArrayList();
        shortUrls.add(ShortUrl.of("http://localhost:8080", "http://longurl.com/loooong"));
        shortUrls.add(ShortUrl.of("http://localhost:8080/1", "http://longurl.com/looooooooong"));
        shortUrls.add(ShortUrl.of("http://localhost:8080/a", "http://longurl.com/l000000oooong"));

        return shortUrls;
    }

    @ParameterizedTest(name = "{index} => toGenerateShortUrlResponseDto({0})")
    @MethodSource("shortUrlProvider")
    void toGenerateShortUrlResponseDto(ShortUrl shortUrl) {
        final GenerateShortUrlResponseDto responseDto = shortUrlMapper.toGenerateShortUrlResponseDto(shortUrl);

        assertEquals(shortUrl.getShortUrl(), responseDto.getShortUrl());
        assertEquals(shortUrl.getOriginalUrl(), responseDto.getOriginalUrl());
    }
}
