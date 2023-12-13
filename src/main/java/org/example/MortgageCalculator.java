package org.example;

public class MortgageCalculator {
    final private static byte PERCENT = 100;
    final private static byte MONTHS_IN_YEAR = 12;

    private int principal;
    private float annualInterest;
    private byte years;

    public MortgageCalculator(int principal, float annualInterest, byte years) {
        this.principal = principal;
        this.annualInterest = annualInterest;
        this.years = years;
    }

    public double calculateMortgage() {
        float monthlyInterest = getMonthlyInterest();
        short numberOfPayments = getNumberOfPayments();

        double mortgage = principal
                * (monthlyInterest * Math.pow(1 + monthlyInterest, numberOfPayments))
                / (Math.pow(1 + monthlyInterest, numberOfPayments) - 1);
        return mortgage;
    }

    public double[] getRemainingBalances() {
        short numberOfPayments = getNumberOfPayments();

        var balances = new double[numberOfPayments];
        for (short i = 1; i <= numberOfPayments; i++) {
            double balance = calculateBalance(i);
            balances[i - 1] = balance;
        }
        return balances;
    }

    private double calculateBalance(short numberOfMadePayments) {
        float monthlyInterest = getMonthlyInterest();
        short numberOfPayments = getNumberOfPayments();

        double remainingBalance = principal
                * (Math.pow(1 + monthlyInterest, numberOfPayments) - Math.pow(1 + monthlyInterest, numberOfMadePayments))
                / (Math.pow(1 + monthlyInterest, numberOfPayments) - 1);
        return remainingBalance;
    }

    private float getMonthlyInterest() {
        return annualInterest / PERCENT / MONTHS_IN_YEAR;
    }

    private short getNumberOfPayments() {
        return (short) (years * MONTHS_IN_YEAR);
    }
}
