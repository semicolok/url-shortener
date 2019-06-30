package com.jake.url.shortener.util;

import java.math.BigInteger;

public class Base62Util {

    private Base62Util() {
    }

    private static final char[] BASE62 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
    private static final BigInteger RADIX = BigInteger.valueOf(62);

    public static String getBase62From10(final long seed) {
        final String number = Long.toString(seed);
        final int numberLength = number.length();
        final char[] buf = new char[numberLength];

        int charPos = numberLength - 1;

        BigInteger bigIntegerNumber = new BigInteger(number);

        while (bigIntegerNumber.compareTo(RADIX) >= 0) {
            buf[charPos--] = BASE62[bigIntegerNumber.mod(RADIX).intValue()];
            bigIntegerNumber = bigIntegerNumber.divide(RADIX);
        }

        buf[charPos] = BASE62[bigIntegerNumber.intValue()];

        return new String(buf, charPos, (numberLength - charPos));
    }
}
