package com.alexislavie.coding.assignment.becare.helper;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class MathHelper {

    private final Random random = new Random();

    public int randomValue(int minimum, int maximum) {
        if (minimum >= maximum) {
            throw new IllegalArgumentException("Maximum must be greater than minimum.");
        }

        return random.nextInt(maximum - minimum + 1) + minimum;
    }
}
