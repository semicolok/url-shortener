package com.jake.url.shortener.service;

import com.jake.url.shortener.repository.key.ShortUrlKey;
import com.jake.url.shortener.repository.key.ShortUrlKeyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ShortUrlBase62KeyServiceTest {

    ShortUrlKeyService shortUrlKeyService;

    @Mock
    ShortUrlKeyRepository shortUrlKeyRepository;

    @BeforeEach
    void init() {
        this.shortUrlKeyService = new ShortUrlBase62KeyService(shortUrlKeyRepository);
    }

    Stream<Arguments> urlKeyAndGeneratedKeyProvider() {
        return Stream.of(
                arguments(1L, "b"),
                arguments(109120398409L, "b5gYez7"),
                arguments(10009120398409L, "c0ny9keX"),
                arguments(9210912023398404789L, "k8AeNEc10kb"),
                arguments(Long.MAX_VALUE, "k9viXaIfiWh")
        );
    }

    @ParameterizedTest(name = "generate key With {0} == {1}")
    @MethodSource("urlKeyAndGeneratedKeyProvider")
    void generateKey(Long urlKey, String expectedGeneratedKey) {
        final ShortUrlKey shortUrlKey = mock(ShortUrlKey.class);

        when(shortUrlKeyRepository.save(any())).thenReturn(shortUrlKey);
        when(shortUrlKey.getUrlKey()).thenReturn(urlKey);

        final String generatedKey = shortUrlKeyService.generateKey();

        assertEquals(expectedGeneratedKey, generatedKey);
    }
}