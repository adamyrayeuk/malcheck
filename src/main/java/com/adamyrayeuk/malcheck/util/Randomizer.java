package com.adamyrayeuk.malcheck.util;

import java.util.Random;

public class Randomizer {
    private static final Random random = new Random();

    private Randomizer() {
        throw new IllegalThreadStateException("Utility Class");
    }

    public static String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder randomString = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int idx = random.nextInt(characters.length());
            char randomChar = characters.charAt(idx);
            randomString.append(randomChar);
        }

        return randomString.toString();
    }
}
