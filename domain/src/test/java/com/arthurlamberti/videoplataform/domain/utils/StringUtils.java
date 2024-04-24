package com.arthurlamberti.videoplataform.domain.utils;

import java.util.Random;

public class StringUtils {
    public static String generateValidString(int size) {
        int a = 97;
        int z = 122;
        Random random = new Random();

        return random.ints(a, z + 1)
                .limit(size)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
