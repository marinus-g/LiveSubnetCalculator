package de.mischok.academy.subnetcaculator.ipv4;

import java.util.StringJoiner;

public class IPv4 {

    private final IPv4Octet[] octets = new IPv4Octet[4];

    public IPv4(String address) {
        String[] splitted = address.split("\\.");
        for (int i = 0; i < splitted.length; i++) {
            octets[i] = new IPv4Octet(Integer.parseInt(splitted[i]));
        }
    }

    public void setHostBits(int length, int bit) {
        // 12
        int index = (32 - length) / 8;
        int changedBits = 0;
        for (int i = this.octets.length - 1; i > index - 1 ; i--) {
            final IPv4Octet octet = this.octets[i];
            // 8
            // 4
            int toChangeInOctet = Math.min(length - changedBits, 8);
            octet.setBits(8 - toChangeInOctet, bit);
            changedBits += toChangeInOctet;
            if (changedBits == length) {
                break;
            }
        }
    }

    public String getAddress() {
        final StringJoiner stringJoiner = new StringJoiner(".");
        for (final IPv4Octet octet : octets) {
            stringJoiner.add(String.valueOf(octet.getDecimal()));
        }
        return stringJoiner.toString();
    }
    public IPv4Octet[] getOctets() {
        return octets;
    }
}