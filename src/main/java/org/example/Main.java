package org.example;

public class Main {
    public static void main(String[] args) {
        var user1 = new Customer();

        Customer.setPrincipalInterval(1_000, 1_000_000);
        Customer.setRateInterval(1, 30);
        Customer.setYearsInterval(1, 30);

        user1.readAndSetUserData();
        user1.printMortgage();
        user1.printBalance();
    }
}