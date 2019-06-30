package com.jake.url.shortener.util;

import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Base62UtilTest {

    Stream<Arguments> seedLongAndBase62Provider() {
        return Stream.of(
                arguments(1L, "b"),
                arguments(109120398409L, "b5gYez7"),
                arguments(10009120398409L, "c0ny9keX"),
                arguments(9210912023398404789L, "k8AeNEc10kb"),
                arguments(Long.MAX_VALUE, "k9viXaIfiWh")
        );
    }

    @ParameterizedTest(name = "Base62Util.getBase62From10({0}) == {1}")
    @MethodSource("seedLongAndBase62Provider")
    void getBase62From10(long seed, String base62) {
        assertEquals(base62, Base62Util.getBase62From10(seed));
    }
}