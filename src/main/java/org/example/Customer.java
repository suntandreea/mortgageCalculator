package org.example;

import java.text.NumberFormat;
import java.util.Scanner;

public class Customer {
    private int principal;
    private float annualInterest;
    private float monthlyInterest;
    private byte years;
    private short numberOfPayments;

    private static int minPrincipal;
    private static int maxPrincipal;
    private static float minInterest;
    private static float maxInterest;
    private static byte minYears;
    private static byte maxYears;


    final private static byte PERCENT = 100;
    final private static byte MONTHS_IN_YEAR = 12;

    public static void setPrincipalInterval(int min, int max) {
        minPrincipal = min;
        maxPrincipal = max;
    }

    public static void setRateInterval(int min, int max) {
        minInterest = (float) min;
        maxInterest = (float) max;
    }

    public static void setYearsInterval(int min, int max) {
        minYears = (byte) min;
        maxYears = (byte) max;
    }

    public void readAndSetUserData() {
        principal = (int) runValidation("principal");
        annualInterest = (float) runValidation("interest");
        monthlyInterest = annualInterest / PERCENT / MONTHS_IN_YEAR;
        years = (byte) runValidation("years");
        numberOfPayments = (short) (years * MONTHS_IN_YEAR);
    }

    private static double runValidation(String field) {
        Scanner scanner = new Scanner(System.in);
        String[] labels = {
                "Principal ($1K - $1M): ",
                "Annual Interest Rate (1 - 30): ",
                "Period in years (1 - 30): "
        };
        double value;
        String label = "";
        double min = 0;
        double max = 0;

        while (true) {
            switch (field) {
                case "principal":
                    label = labels[0];
                    min = minPrincipal;
                    max = maxPrincipal;
                    break;
                case "interest":
                    label = labels[1];
                    min = minInterest;
                    max = maxInterest;
                    break;
                case "years":
                    label = labels[2];
                    min = minYears;
                    max = maxYears;
                    break;
            }

            System.out.print(label);
            value = scanner.nextDouble();

            if (value >= min && value <= max)
                break;
            System.out.println("Enter a value between " + (int) min + " and " + (int) max + ".");
        }
        return value;
    }

    private double calculateMortgage() {

        double mortgage = principal
                * (monthlyInterest * Math.pow(1 + monthlyInterest, numberOfPayments))
                / (Math.pow(1 + monthlyInterest, numberOfPayments) - 1);

        return mortgage;
    }

    public void printMortgage() {
        double mortgage = calculateMortgage();
        String mortgageFormatted = NumberFormat.getCurrencyInstance().format(mortgage);
        System.out.println();
        System.out.println("MORTGAGE");
        System.out.println("__________");
        System.out.println("Monthly Payments: " + mortgageFormatted);
    }

    private double calculateRemainingBalance(short numberOfMadePayments) {

        double remainingBalance = principal
                * (Math.pow(1 + monthlyInterest, numberOfPayments) - Math.pow(1 + monthlyInterest, numberOfMadePayments))
                / (Math.pow(1 + monthlyInterest, numberOfPayments) - 1);

        return remainingBalance;
    }

    public void printBalance() {
        System.out.println();
        System.out.println("PAYMENT SCHEDULE");
        System.out.println("__________");
        for (short numberOfCurrentPayment = 1; numberOfCurrentPayment <= numberOfPayments; numberOfCurrentPayment++) {
            double remainingBalance = calculateRemainingBalance(numberOfCurrentPayment);
            String formattedRemainingBalance = NumberFormat.getCurrencyInstance().format(remainingBalance);
            System.out.println(formattedRemainingBalance);
        }
    }
}
