package de.mischok.academy.subnetcaculator.validator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class validates a subnet mask.
 * It implements the StringValidator interface.
 */
public class SubnetValidator implements StringValidator {

    // A list of valid numbers for a subnet mask
    private final List<Integer> validNumbers = new ArrayList<>();

    /**
     * Constructs a SubnetValidator object and calculates the valid numbers for a subnet mask.
     */
    public SubnetValidator() {
        this.calculateNumbers();
    }

    /**
     * Calculates the valid numbers for a subnet mask.
     */
    private void calculateNumbers() {
        int number = 0;
        this.validNumbers.add(number);
        for (int i = 7; i >= 0; i--) {
            number += (int) Math.pow(2, i);
            this.validNumbers.add(number);
        }
    }

    /**
     * Validates a subnet mask.
     *
     * @param input The subnet mask to validate.
     * @return true if the subnet mask is valid, false otherwise.
     */
    @Override
    public boolean isValid(String input) {
        final Integer[] intArray = Arrays.stream(input.split("\\."))
                .map(Integer::parseInt)
                .toArray(Integer[]::new);
        int lastNumber = 0;
        for (int i = 0; i < intArray.length; i++) {
            final int number = intArray[i];
            if (i == 0 && number == 0) {
                return false;
            }
            if (!this.validNumbers.contains(number)) {
                return false;
            }

            if (i != 0 && (lastNumber < number || (lastNumber != 255 && number != 0))) {
                return false;
            }

            lastNumber = number;
        }
        return true;
    }
}