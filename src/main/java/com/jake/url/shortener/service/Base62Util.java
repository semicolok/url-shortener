package com.jake.url.shortener.service;

import java.math.BigInteger;

public class Base62Util {

    private Base62Util() {
    }

    private static final char[] corpus = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();

    public static String getBase62From10(final long seed) {
        String number = seed + "";

        char[] buf = new char[number.length()];

        int charPos = number.length() - 1;

        BigInteger bigIntegerNumber = new BigInteger(number);

        BigInteger radix = BigInteger.valueOf(62);

        while (bigIntegerNumber.compareTo(radix) >= 0) {
            buf[charPos--] = corpus[bigIntegerNumber.mod(radix).intValue()];
            bigIntegerNumber = bigIntegerNumber.divide(radix);
        }

        buf[charPos] = corpus[bigIntegerNumber.intValue()];

        return new String(buf, charPos, (number.length() - charPos));
    }
}
