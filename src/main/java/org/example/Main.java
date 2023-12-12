package org.example;

import java.text.NumberFormat;
import java.util.Scanner;

public class Main {

    final static byte PERCENT = 100;
    final static byte MONTHS_IN_YEAR = 12;
    public static void main(String[] args) {
        int principal = (int) readUserInput("Principal ($1K - $1M): ", 1_000, 1_000_000);
        float annualInterest = (float) readUserInput("Annual Interest Rate (1 - 30): ", 1, 30);
        byte years = (byte) readUserInput("Period in years (1 - 30): ", 1, 30);

        float monthlyInterest = annualInterest / PERCENT / MONTHS_IN_YEAR;
        short numberOfPayments = (short) (years * MONTHS_IN_YEAR);

        printMortgage(principal, monthlyInterest, numberOfPayments);
        printBalance(numberOfPayments, principal, monthlyInterest);
    }

    private static void printBalance(short numberOfPayments, int principal, float monthlyInterest) {
        System.out.println();
        System.out.println("PAYMENT SCHEDULE");
        System.out.println("__________");
        for (short month = 1; month <= numberOfPayments; month++) {
            double remainingBalance = calculateRemainingBalance(principal, monthlyInterest, numberOfPayments, month);
            String formattedRemainingBalance = NumberFormat.getCurrencyInstance().format(remainingBalance);
            System.out.println(formattedRemainingBalance);
        }
    }

    private static void printMortgage(int principal, float monthlyInterest, short numberOfPayments) {
        double mortgage = calculateMortgage(principal, monthlyInterest, numberOfPayments);
        String mortgageFormatted = NumberFormat.getCurrencyInstance().format(mortgage);
        System.out.println();
        System.out.println("MORTGAGE");
        System.out.println("__________");
        System.out.println("Monthly Payments: " + mortgageFormatted);
    }

    public static double readUserInput(String label, double min, double max) {
        Scanner scanner = new Scanner(System.in);
        double value;

        while (true) {
            System.out.print(label);
            value = scanner.nextDouble();

            if (value >= min && value <= max)
                break;
            System.out.println("Enter a value between " + min + " and " + max + ".");
        }
        return value;
    }

    public static double calculateMortgage(
            int principal,
            float monthlyInterest,
            short numberOfPayments) {

        double mortgage = principal
                * (monthlyInterest * Math.pow(1 + monthlyInterest, numberOfPayments))
                / (Math.pow(1 + monthlyInterest, numberOfPayments) - 1);

        return mortgage;
    }

    public static double calculateRemainingBalance(
            int principal,
            float monthlyInterest,
            short numberOfPayments,
            short numberOfMadePayments) {

        double remainingBalance = principal
                * (Math.pow(1 + monthlyInterest, numberOfPayments) - Math.pow(1 + monthlyInterest, numberOfMadePayments))
                / (Math.pow(1 + monthlyInterest, numberOfPayments) - 1);

        return remainingBalance;
    }
}