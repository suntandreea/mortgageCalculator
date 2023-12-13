package org.example;

import java.util.Scanner;

public class Console {
    private static Scanner scanner = new Scanner(System.in);

    public static double readUserInput(String label) {
        System.out.print(label);
        return scanner.nextDouble();
    }

    public static double readUserInput(String label, double min, double max) {
        double value;
        while (true) {
            System.out.print(label);
            value = scanner.nextDouble();

            if (value >= min && value <= max)
                break;
            System.out.println("Enter a value between " + (int) min + " and " + (int) max + ".");
        }
        return value;
    }
}
