package de.mischok.academy.subnetcaculator.validator;

import java.util.Arrays;

/**
 * This class validates an IPv4 address.
 * It implements the StringValidator interface.
 */
public class Ipv4Validator implements StringValidator{

    /**
     * Validates an IPv4 address.
     * @param input The IPv4 address to validate.
     * @return true if the IPv4 address is valid, false otherwise.
     */
    @Override
    public boolean isValid(String input) {

        // Check if the input matches the IPv4 address format
        final boolean matches = input.matches("^([0-9]{1,3}\\.){3}[0-9]{1,3}$");
        if (!matches) {
            return false;
        }

        // Convert the input into an array of integers
        final Integer[] intArray = Arrays.stream(input.split("\\."))
                .map(Integer::parseInt)
                .toArray(Integer[]::new);

        // Check each octet of the IPv4 address
        for (int i = 0; i < intArray.length; i++) {
            final int number = intArray[i];
            // Check if the octet is within the valid range (0-255)
            // and if the first octet is not 0
            if (number > 255 || (i == 0 && number == 0) || number < 0) {
                return false;
            }
        }

        // If all checks pass, the IPv4 address is valid
        return true;
    }
}