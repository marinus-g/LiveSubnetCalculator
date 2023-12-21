package de.mischok.academy.subnetcaculator.ipv4;

import java.util.Arrays;

/**
 * This class represents an octet of an IPv4 address.
 * It provides methods to manipulate and retrieve the octet.
 */
public class IPv4Octet {

    // The decimal representation of the octet
    private int decimal;
    // The binary representation of the octet
    private final int[] binary = new int[8];

    /**
     * Constructs an IPv4Octet object from a decimal representation of an octet.
     *
     * @param decimal A decimal representation of an octet.
     */
    public IPv4Octet(int decimal) {
        this.decimal = decimal;
        this.convertToBinary();
    }

    /**
     * Converts the decimal representation of the octet to binary.
     */
    private void convertToBinary() {
        int number = this.decimal;
        int index = 7;
        while (number > 0) {
            this.binary[index] = number % 2;
            number = number / 2;
            index--;
        }
        System.out.println(Arrays.toString(this.binary));
    }

    /**
     * ConvertsTo the binary representation of the octet to decimal.
     */
    private void convertToDecimal() {
        this.decimal = 0;
        for (int i = 0; i < 8; i++) {
            if (this.binary[i] == 0) {
                continue;
            }
            this.decimal += (int) Math.pow(2, 7 - i);
        }
        System.out.println(decimal);
    }

    /**
     * Sets the bits of the octet from a specified index to a specified value.
     *
     * @param from The index from which to start setting the bits.
     * @param bit  The value to set the bits to.
     */
    public void setBits(int from, int bit) {
        Arrays.fill(this.binary, from, this.binary.length, bit);
        this.convertToDecimal();
    }

    /**
     * Returns the decimal representation of the octet.
     *
     * @return The decimal representation of the octet.
     */
    public int getDecimal() {
        return decimal;
    }

    /**
     * Returns the binary representation of the octet.
     *
     * @return The binary representation of the octet.
     */
    public int[] getBinary() {
        return binary;
    }
}