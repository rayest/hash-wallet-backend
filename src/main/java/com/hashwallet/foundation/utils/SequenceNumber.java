package com.hashwallet.foundation.utils;

import java.util.Random;

public class SequenceNumber {
    public static String generate(int bit) {
        Random random = new Random();
        String result = "";
        for (int i = 0; i <bit; i++) {
            result += random.nextInt(10);
        }
        return result;
    }
}
