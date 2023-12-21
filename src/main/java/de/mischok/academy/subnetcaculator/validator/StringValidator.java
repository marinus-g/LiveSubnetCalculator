package de.mischok.academy.subnetcaculator.validator;

/**
 * This interface defines a method for validating a string.
 * It is implemented by classes that validate specific types of strings.
 */
public interface StringValidator {

    /**
     * Validates a string.
     * @param input The string to validate.
     * @return true if the string is valid, false otherwise.
     */
    boolean isValid(final String input);

}