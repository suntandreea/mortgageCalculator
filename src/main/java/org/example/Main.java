package org.example;

public class Main {
    public static void main(String[] args) {
        int principal = (int) Console.readUserInput("Principal ($1K - $1M): ", 1_000, 1_000_000);
        float annualInterest = (float) Console.readUserInput("Annual Interest Rate (1 - 30): ", 1, 30);
        byte years = (byte) Console.readUserInput("Period in years (1 - 30): ", 1, 30);

        var calculator = new MortgageCalculator(principal, annualInterest, years);
        var report = new MortgageReport(calculator);
        report.printMortgage();
        report.printPaymentSchedule();
    }
}