package de.mischok.academy.subnetcaculator.validator;

import java.util.Arrays;

public class Ipv4Validator implements StringValidator{
    @Override
    public boolean isValid(String input) {


        final boolean matches = input.matches("^(?:[0-9]{1,3}\\.){3}[0-9]{1,3}$");
        if (!matches) {
            return false;
        }
        final Integer[] intArray = Arrays.stream(input.split("\\."))
                .map(Integer::parseInt)
                .toArray(Integer[]::new);
        for (Integer number : intArray) {
            if (number > 255) {
                return false;
            } // 1.1.1.1.
        }
        return true;
    }

}