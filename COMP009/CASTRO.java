package COMP009;

import java.util.Scanner;

public class CASTRO {
    public static void main(String[] args) {
        Scanner stringManipulation = new Scanner(System.in);

        System.out.println("\n************************************************");
        System.out.println("************** String Manipulation *************");
        System.out.println("************************************************\n");

        boolean inputAnother = true;
        while (inputAnother) {
            // Prompt the user for the first string
            System.out.print("Input String 1: ");
            String input1 = stringManipulation.nextLine();
            
            // Prompt the user for the second string
            System.out.print("Input String 2: ");
            String input2 = stringManipulation.nextLine();

            char thirdCharacter = ' ';
            // Check if the first string has at least 3 characters
            if (input1.length() >= 3) {
                thirdCharacter = Character.toLowerCase(input1.charAt(2)); // Convert to lowercase for case-insensitive check
            }

            if (input2.toLowerCase().contains(String.valueOf(thirdCharacter))) {
                // If the third character of the first string is found in the second string
                String output = input1.toUpperCase().replaceAll("[AEIOUaeiou]", "*");
                System.out.println("Output: " + output);
            } else {
                // If the third character is not found in the second string
                String output = input2.toLowerCase().replaceAll("[AEIOUaeiou]", "@");
                System.out.println("Output: " + output);
            }

            // Ask the user if they want to enter another set of strings
            System.out.print("\n--Do you want to enter another set of strings? (yes/no): ");
            String response = stringManipulation.nextLine().toLowerCase();
            inputAnother = response.equals("yes");
            System.out.print("\n");
        }
        
        // Close the scanner to release resources
        stringManipulation.close();
    }
}
