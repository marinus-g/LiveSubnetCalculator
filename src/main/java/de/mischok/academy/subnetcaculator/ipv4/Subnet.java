package de.mischok.academy.subnetcaculator.ipv4;

import java.util.Arrays;

/**
 * This class represents a subnet of an IPv4 network.
 * It provides methods to calculate and retrieve the network ID, broadcast address, and the number of usable hosts.
 */
public class Subnet {

    // The given IP address for the subnet
    private final IPv4 givenIp;
    // The subnet mask for the subnet
    private final IPv4 subnetMask;

    // The network ID of the subnet
    private IPv4 netId;
    // The broadcast address of the subnet
    private IPv4 broadcastAddress;
    // The number of usable hosts in the subnet
    private long usableHosts;

    /**
     * Constructs a Subnet object from a given IP address and a subnet mask.
     * @param givenIp The given IP address for the subnet.
     * @param subnetMask The subnet mask for the subnet.
     */
    public Subnet(IPv4 givenIp, IPv4 subnetMask) {
        this.givenIp = givenIp;
        this.subnetMask = subnetMask;
        this.calculateSubnet();
    }

    /**
     * Calculates the network ID, broadcast address, and the number of usable hosts for the subnet.
     */
    private void calculateSubnet() {
        int hostBits = this.getHostBits();
        this.usableHosts = (long) Math.pow(2, hostBits) - 2;
        this.netId = new IPv4(this.givenIp.getAddress());
        this.broadcastAddress = new IPv4(this.givenIp.getAddress());
        this.netId.setHostBits(hostBits, 0);
        this.broadcastAddress.setHostBits(hostBits, 1);
    }

    /**
     * Returns the number of host bits in the subnet mask.
     * @return The number of host bits in the subnet mask.
     */
    private int getHostBits() {
        return (int) Arrays.stream(this.subnetMask.getOctets())
                .map(IPv4Octet::getBinary)
                .flatMapToInt(Arrays::stream)
                .filter(bit -> bit == 0)
                .count();
    }

    /**
     * Returns the given IP address for the subnet.
     * @return The given IP address for the subnet.
     */
    public IPv4 getGivenIp() {
        return givenIp;
    }

    /**
     * Returns the subnet mask for the subnet.
     * @return The subnet mask for the subnet.
     */
    public IPv4 getSubnetMask() {
        return subnetMask;
    }

    /**
     * Returns the network ID of the subnet.
     * @return The network ID of the subnet.
     */
    public IPv4 getNetId() {
        return netId;
    }

    /**
     * Returns the broadcast address of the subnet.
     * @return The broadcast address of the subnet.
     */
    public IPv4 getBroadcastAddress() {
        return broadcastAddress;
    }

    /**
     * Returns the number of usable hosts in the subnet.
     * @return The number of usable hosts in the subnet.
     */
    public long getUsableHosts() {
        return usableHosts;
    }
}