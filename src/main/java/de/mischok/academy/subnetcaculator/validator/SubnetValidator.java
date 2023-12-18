package de.mischok.academy.subnetcaculator.validator;

import de.mischok.academy.subnetcaculator.SubnetCalculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubnetValidator implements StringValidator {

    private final List<Integer> validNumbers = new ArrayList<>();

    // 128
    // 192
    // 224
    public SubnetValidator() {
        this.calculateNumbers();
    }

    private void calculateNumbers() {


        int number = 0;
        this.validNumbers.add(number);
        for (int i = 7; i >= 0; i--) {
            number += (int) Math.pow(2, i);
            this.validNumbers.add(number);
            System.out.println(number);
        }

    }


    @Override
    public boolean isValid(String input) {
        final Integer[] intArray = Arrays.stream(input.split("\\."))
                .map(Integer::parseInt)
                .toArray(Integer[]::new);
        int lastNumber = 0;
        for (int i = 0; i < intArray.length; i++) {
            final int number = intArray[i];
            // 255.255.255.0
            if (i == 0 && number == 0) {
                return false;
            }
            if (!this.validNumbers.contains(number)) {
                return false;
            }
            //if (i != 0 && (lastNumber < decimal || (lastNumber != 255 && decimal != 0))) {
            if (i != 0 && number >= lastNumber && number != 255 && number != 0) {
                // 12
                return false;
            }

            lastNumber = number;
        }
        return true;
    }
}
