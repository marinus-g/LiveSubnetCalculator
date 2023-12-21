package de.mischok.academy.subnetcaculator;

import de.mischok.academy.subnetcaculator.ipv4.IPv4;
import de.mischok.academy.subnetcaculator.ipv4.Subnet;
import de.mischok.academy.subnetcaculator.validator.Ipv4Validator;
import de.mischok.academy.subnetcaculator.validator.StringValidator;
import de.mischok.academy.subnetcaculator.validator.SubnetValidator;

import java.util.Scanner;

/**
 * This class represents a subnet calculator.
 * It provides methods to request user input, validate the input, calculate the subnet, and print the result.
 */
public class SubnetCalculator {

    // Scanner object for reading user input
    private final Scanner scanner = new Scanner(System.in);
    // Validator for validating IP addresses
    private final StringValidator ipValidator = new Ipv4Validator();
    // Validator for validating subnet masks
    private final StringValidator subnetValidator = new SubnetValidator();
    // Subnet object for storing the calculated subnet
    private Subnet subnet;

    /**
     * Constructs a SubnetCalculator object and starts the subnet calculation process.
     */
    public SubnetCalculator() {
        this.requestInput();
        this.printResult();
    }

    /**
     * Prints the result of the subnet calculation.
     */
    private void printResult() {
        System.out.println("Eingegebene IPv4: " + subnet.getGivenIp().getAddress());
        System.out.println("Eingegebene Subnetzmaske: " + subnet.getSubnetMask().getAddress());
        System.out.println("Broadcast-Adresse: " + subnet.getBroadcastAddress().getAddress());
        System.out.println("Netz-ID: " + subnet.getNetId().getAddress());
        System.out.println("Host Anzahl: " + subnet.getUsableHosts());
    }

    /**
     * Requests user input for the IP address and subnet mask.
     */
    private void requestInput() {
        System.out.print("Bitte gib eine IPv4-Adresse ein: ");
        final String ipAddress = this.handleIpInput();
        System.out.println("Bitte gib eine Subnetzmaske ein: ");
        final String subnetMask = this.handleSubnetInput();
        this.subnet = new Subnet(new IPv4(ipAddress), new IPv4(subnetMask));
    }

    /**
     * Handles user input for the IP address.
     *
     * @return The user's input for the IP address.
     */
    private String handleIpInput() {
        String input = this.scanner.nextLine();
        if (!this.ipValidator.isValid(input)) {
            System.out.print("Falsche Eingabe. Gebe eine valide IPv4-Adresse ein: ");
            return this.handleIpInput();
        }
        return input;
    }

    /**
     * Handles user input for the subnet mask.
     *
     * @return The user's input for the subnet mask.
     */
    private String handleSubnetInput() {
        String input = this.scanner.nextLine();
        if (!this.ipValidator.isValid(input) || !this.subnetValidator.isValid(input)) {
            System.out.print("Falsche Eingabe. Gebe eine valide Subnetzmaske ein: ");
            return this.handleSubnetInput();
        }
        return input;
    }
}