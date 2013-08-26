package com.cengage.apprentice.app.utils;

import java.util.Random;

public class IdGenerator {
    public static int randInt(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }
}
