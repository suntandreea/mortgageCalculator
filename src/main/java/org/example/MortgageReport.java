package org.example;

import java.text.NumberFormat;

public class MortgageReport {

    private final NumberFormat currency;
    private MortgageCalculator calculator;

    public MortgageReport(MortgageCalculator calculator) {
        this.currency = NumberFormat.getCurrencyInstance();
        this.calculator = calculator;
    }
    public void printMortgage() {
        System.out.println();
        System.out.println("MORTGAGE");
        System.out.println("__________");
        System.out.println("Monthly Payments: ");
        System.out.println(currency.format(calculator.calculateMortgage()));
    }

    public void printPaymentSchedule() {
        System.out.println();
        System.out.println("PAYMENT SCHEDULE");
        System.out.println("__________");
        for (double balance : calculator.getRemainingBalances())
            System.out.println(currency.format(balance));
    }
}
