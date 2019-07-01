package com.jake.url.shortener.util;

import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Base62UtilTest {

    Stream<Arguments> decimalLongAndBase62StringProvider() {
        return Stream.of(
                arguments(-2_140L, "IG"),
                arguments(1L, "b"),
                arguments(5L, "f"),
                arguments(40L, "O"),
                arguments(2_140L, "IG"),
                arguments(457_140L, "b45o"),
                arguments(109_120_398_409L, "b5gYez7"),
                arguments(10_009_120_398_409L, "c0ny9keX"),
                arguments(9_210_912_023_398_404_789L, "k8AeNEc10kb"),
                arguments(9_223_372_036_854_775_806L, "k9viXaIfiWg"),
                arguments(Long.MAX_VALUE, "k9viXaIfiWh")
        );
    }

    @ParameterizedTest(name = "Base62Util.encode({0}) == {1}")
    @MethodSource("decimalLongAndBase62StringProvider")
    void encode(long decimal, String base62) {
        if (decimal < 0) {
            assertThrows(IllegalArgumentException.class, () -> Base62Util.encode(decimal));
        } else {
            assertEquals(base62, Base62Util.encode(decimal));
        }
    }
}