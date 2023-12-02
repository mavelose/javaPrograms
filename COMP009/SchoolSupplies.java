package COMP009;

import java.util.Scanner;

public class SchoolSupplies {
    public static void main(String[] args) {
        double paper, pencil, scissors, eraser, totalPrice, cash, paperInitial, pencilInitial, scissorsInitial, eraserInitial, change, priceVAT, vat;
        int paperQuanti, pencilQuanti, scissorsQuanti, eraserQuanti, numOfItems;
        Scanner price = new Scanner(System.in);

        System.out.print("\nMATERIALS \n");
        System.out.print("------------- \n");
        System.out.print("Enter the cost of the following: \n");
        System.out.print("Paper : ");
        paper = price.nextDouble();
        System.out.print("Pencil : ");
        pencil = price.nextDouble();
        System.out.print("Scissors : ");
        scissors = price.nextDouble();
        System.out.print("Eraser : ");
        eraser = price.nextDouble();

        System.out.print("\nPURCHASE \n");
        System.out.print("------------- \n");
        System.out.print("How many items of the following did you buy?: \n");
        System.out.print("Paper : ");
        paperQuanti = price.nextInt();
        System.out.print("Pencil : ");
        pencilQuanti = price.nextInt();
        System.out.print("Scissors : ");
        scissorsQuanti = price.nextInt();
        System.out.print("Eraser : ");
        eraserQuanti = price.nextInt();

        totalPrice = (paper * paperQuanti) + (pencil * pencilQuanti) + (scissors * scissorsQuanti) + (eraser * eraserQuanti);
        System.out.println("\n\nTHE TOTAL PRICE IS : " + String.valueOf(totalPrice));
        
        System.out.print("Enter Cash : ");
        cash = price.nextDouble();

        System.out.print("\n\n\n\nOFFICIAL RECEIPT \n");
        System.out.print("---------------------- \n");
        paperInitial = paper * paperQuanti;
        System.out.println("Paper : " +  String.valueOf(paperInitial));
        pencilInitial = pencil * pencilQuanti;
        System.out.println("Pencil : " +  String.valueOf(pencilInitial));
        scissorsInitial = scissors * scissorsQuanti;
        System.out.println("Scissors : " +  String.valueOf(scissorsInitial));
        eraserInitial = eraser * eraserQuanti;
        System.out.println("Eraser : " +  String.valueOf(eraserInitial));
        System.out.print("---------------------- \n");
        System.out.println("Total : P " + String.valueOf(totalPrice));
        System.out.println("Cash : P " + String.valueOf(cash));
        change = cash - totalPrice;
        System.out.println("Change : P " + String.valueOf(change));

        numOfItems = paperQuanti + pencilQuanti + scissorsQuanti + eraserQuanti;
        System.out.println("\nNo. of Items: " + numOfItems);

        priceVAT = totalPrice / (1 + 0.12);
        System.out.println("Price before VAT : P " + String.format("%.2f", priceVAT));

        vat = priceVAT - totalPrice; // Calculate the VAT
        vat = Math.abs(vat); // Ensure vat is positive
        System.out.println("VAT : P " + String.format("%.2f", vat));
        System.out.print("\n\n");

price.close();
    }
}
