package de.mischok.academy.subnetcaculator.ipv4;

public class Subnet {

    private final IPv4 givenIp;
    private final IPv4 subnetMask;

    private IPv4 netId;
    private IPv4 broadcastAddress;
    private long usableHosts;

    public Subnet(IPv4 givenIp, IPv4 subnetMask) {
        this.givenIp = givenIp;
        this.subnetMask = subnetMask;
        this.calculateSubnet();
    }

    private void calculateSubnet() {
        int hostBits = this.getHostBits();
        this.usableHosts = (long) Math.pow(2, hostBits) - 2;
        this.netId = new IPv4(this.givenIp.getAddress());
        this.broadcastAddress = new IPv4(this.givenIp.getAddress());
        this.netId.setHostBits(hostBits, 0);
        this.broadcastAddress.setHostBits(hostBits, 1);
    }

    private int getHostBits() {
        int count = 0;
        for (IPv4Octet octet : subnetMask.getOctets()) {
            for (int bit : octet.getBinary()) {
                if (bit == 0) {
                    count++;
                }
            }
        }
        return count;
    }

    public IPv4 getGivenIp() {
        return givenIp;
    }

    public IPv4 getSubnetMask() {
        return subnetMask;
    }

    public IPv4 getNetId() {
        return netId;
    }

    public IPv4 getBroadcastAddress() {
        return broadcastAddress;
    }

    public long getUsableHosts() {
        return usableHosts;
    }
}