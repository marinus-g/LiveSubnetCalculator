package de.mischok.academy.subnetcaculator.ipv4;

import java.util.StringJoiner;

/**
 * This class represents an IPv4 address.
 * It provides methods to manipulate and retrieve the address.
 */
public class IPv4 {

    // An array of IPv4Octet objects representing the four octets of an IPv4 address
    private final IPv4Octet[] octets = new IPv4Octet[4];

    /**
     * Constructs an IPv4 object from a string representation of an IPv4 address.
     * @param address A string representation of an IPv4 address.
     */
    public IPv4(String address) {
        final String[] splitted = address.split("\\.");
        for (int i = 0; i < splitted.length; i++) {
            octets[i] = new IPv4Octet(Integer.parseInt(splitted[i]));
        }
    }

    /**
     * Sets the host bits of the IPv4 address.
     * @param length The number of host bits to set.
     * @param bit The value to set the host bits to.
     */
    public void setHostBits(int length, int bit) {
        int index = (32 - length) / 8;
        int changedBits = 0;
        for (int i = this.octets.length - 1; i > index - 1 ; i--) {
            final IPv4Octet octet = this.octets[i];
            int toChangeInOctet = Math.min(length - changedBits, 8);
            octet.setBits(8 - toChangeInOctet, bit);
            changedBits += toChangeInOctet;
            if (changedBits == length) {
                break;
            }
        }
    }

    /**
     * Returns the string representation of the IPv4 address.
     * @return A string representation of the IPv4 address.
     */
    public String getAddress() {
        final StringJoiner stringJoiner = new StringJoiner(".");
        for (final IPv4Octet octet : octets) {
            stringJoiner.add(String.valueOf(octet.getDecimal()));
        }
        return stringJoiner.toString();
    }

    /**
     * Returns the octets of the IPv4 address.
     * @return An array of IPv4Octet objects representing the octets of the IPv4 address.
     */
    public IPv4Octet[] getOctets() {
        return octets;
    }
}