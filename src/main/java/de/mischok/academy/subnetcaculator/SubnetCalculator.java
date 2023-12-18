package de.mischok.academy.subnetcaculator;


import de.mischok.academy.subnetcaculator.ipv4.IPv4;
import de.mischok.academy.subnetcaculator.ipv4.Subnet;
import de.mischok.academy.subnetcaculator.validator.Ipv4Validator;
import de.mischok.academy.subnetcaculator.validator.StringValidator;
import de.mischok.academy.subnetcaculator.validator.SubnetValidator;

import java.util.Scanner;

public class SubnetCalculator {

    private final Scanner scanner = new Scanner(System.in);
    private final StringValidator ipValidator = new Ipv4Validator();
    private final StringValidator subnetValidator = new SubnetValidator();
    private Subnet subnet;
    public SubnetCalculator() {
        this.requestInput();
        this.printResult();
    }

    private void printResult() {

        System.out.println("Eingegebene IP: " + subnet.getGivenIp().getAddress());
        System.out.println("Eingegebene Subnet Maske: " + subnet.getSubnetMask().getAddress());
        System.out.println("Broadcast Adresse: " + subnet.getBroadcastAddress().getAddress());
        System.out.println("Netz ID: " + subnet.getNetId().getAddress());
        System.out.println("Host Anzahl: " + subnet.getUsableHosts());

    }

    private void requestInput() {
        System.out.print("Bitte gib eine IP Adresse ein: ");
        final String ipAddress = this.handleIpInput();
        System.out.println("Bitte gib eine Subnet Maske ein: ");
        final String subnetMask = this.handleSubnetInput();
        this.subnet = new Subnet(new IPv4(ipAddress), new IPv4(subnetMask));
    }

    private String handleIpInput() {
        String input = "";
        do {
            if (!input.isEmpty()) {
                System.out.print("Falsche Eingabe. Gebe eine valide Adresse ein: ");
            }
            input = scanner.nextLine();
        } while (!ipValidator.isValid(input));
        return input;
    }

    private String handleSubnetInput() {
        String input = "";
        do {
            if (!input.isEmpty()) {
                System.out.print("Falsche Eingabe. Gebe eine valide Subnetmaske ein: ");
            }
            input = scanner.nextLine();
        } while (!ipValidator.isValid(input) || !subnetValidator.isValid(input));
        return input;
    }

}