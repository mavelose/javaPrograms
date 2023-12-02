package COMP009;

import java.util.Scanner;

// Programming Task 4 
// Programmers: CASTRO, MACASINAG, NARISMA, ROMALES

public class Conversion {
    public static void main(String[] args) {
        Scanner count = new Scanner(System.in);
        char choice;

        do {
            int[] denominations = {1000, 500, 100, 50, 20, 10, 5, 1}; // Array of denominations
            int amount;

            // Layout of the program
            System.out.println("\n-------------------------------------------------------");
            System.out.println("\t       MONEY CONVERSIONS PROGRAM");
            System.out.println("-------------------------------------------------------");
            System.out.print("\t\tInput Amount: ");
            amount = count.nextInt();

            displayDenominations(amount, denominations); // Call method to display denominations

            System.out.print("\nEnter More [Y/N]? ");
            choice = count.next().charAt(0); // Get user choice to repeat the program
        } while (choice == 'Y' || choice == 'y');
        System.out.println("\n****Thank you for using Money Conversions Program.****\n");
        count.close();
    }

    // Method to display denominations for a given amount
    private static void displayDenominations(int amount, int[] denominations) {
        System.out.println("\t       Denominations:");
        for (int val : denominations) {
            // Display each denomination and the number of times it goes into the amount with right alignment
            System.out.printf("%34d - %1d\n", val, amount / val);
            // Update the amount to represent the remaining value
            amount = amount % val;
        }
    }
}