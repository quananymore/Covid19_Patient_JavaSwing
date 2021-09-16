package com.ifi.javacore.management.covid.utilities;

public class IdCounter {
    private static long counter = 0;

    public static long getAndIncrement() {
        return ++counter;
    }

    public static void updateCounter(long num) {
        if (counter < num) {
            counter = num;
        }
    }
}
