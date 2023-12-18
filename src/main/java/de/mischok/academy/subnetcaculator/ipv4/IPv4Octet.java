package de.mischok.academy.subnetcaculator.ipv4;

import java.util.Arrays;

public class IPv4Octet {

    private int decimal;
    private final int[] binary = new int[8];

    public IPv4Octet(int decimal) {
        this.decimal = decimal;
        this.convertToBinary();
        this.convertToDecimal();
    }

    private void convertToBinary() {

        int number = this.decimal;
        int index = 7;
        // 193 / 2
        // 96 rest 1
        while (number > 0) {
            this.binary[index] = number % 2;
            number = number / 2;
            index--;
        }
        System.out.println(Arrays.toString(this.binary));
    }

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

    public void setBits(int from, int bit) {
        Arrays.fill(this.binary, from, this.binary.length - 1, bit);
        this.convertToDecimal();
    }

    public int getDecimal() {
        return decimal;
    }

    public int[] getBinary() {
        return binary;
    }
}
