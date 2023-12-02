package COMP009;

import java.util.Scanner;

// Programming Task 5 
// Programmers: CASTRO, MACASINAG, NARISMA, ROMALES

class PIZZA {
  String Toppings, Discount;
  int Diameter, HowMany;
  double Price;

  // Set methods
  public void setToppings(String Toppings) {
    this.Toppings = Toppings;
  }

  public void setDiameter(int Diameter) {
    this.Diameter = Diameter;
  }

  public void setHowMany(int HowMany) {
    this.HowMany = HowMany;
  }

  public void setDiscount(String Discount) {
    this.Discount = Discount;
  }

  // Get methods
  public String getToppings() {
    return this.Toppings;
  }

  public int getDiameter() {
    return this.Diameter;
  }

  // Calculation based on the user's input
  public double getPrice() {
    double pricePerCm = 5.0; // PHP 5.00 per centimeter
    // Switch statement for the price of toppings
    double toppingsPrice = 0.0;
    switch (this.Toppings.toLowerCase()) {
      case "pepperoni":
        toppingsPrice = 100.0;
        break;
      case "ham and cheese":
        toppingsPrice = 150.0;
        break;
      case "hawaiian":
        toppingsPrice = 200.0;
        break;
      default:
        toppingsPrice = 0.0;
        break;
    }

    double diameterInCm = this.Diameter * 2.54; // Covert cm to in
    double diameterRounded = Math.round(diameterInCm); // Round-off to the nearest whole number
    double diameterPrice = (diameterRounded * pricePerCm); // Pizza's diameter price
    double totalPrice = (toppingsPrice + diameterPrice) * this.HowMany; // Total price calculation

    // Apply discount if applicable
    if (this.Discount.toLowerCase().equals("senior citizen")) {
      totalPrice *= 0.8; // 20 percent discount for senior citizen
    } else if (this.Discount.toLowerCase().equals("vipcard")) {
      totalPrice *= 0.88; // 12 percent discount for VIPs
    }

    this.Price = totalPrice;
    return this.Price;
  }
}

// Print order
class BILL_ORDER {
  public void OUT(PIZZA horray) {
    String toppings = horray.getToppings();
    int diameter = horray.getDiameter();

    System.out.println("\n    You ordered for " + toppings + 
    " Pizza with the size of \n\t     " + Math.round(diameter * 2.54) +
    " centimeter for PHP " + horray.getPrice() + ".");
  }
}

public class TAKE_ORDER {
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    PIZZA PIE = new PIZZA();
    BILL_ORDER billOrder = new BILL_ORDER();

    // Layout of the program
    System.out.println("\n--------------------------------------------------------");
    System.out.println("\t       PIZZA PIE ORDERING PROGRAM");
    System.out.println("--------------------------------------------------------");
    System.out.println("\t\t\t  MENU");
    System.out.println("\t\tToppings \t  Price");
    System.out.println("\t\tPepperoni \t  100");
    System.out.println("\t\tHam and Cheese    150");
    System.out.println("\t\tHawaiian \t  200");
    System.out.println("--------------------------------------------------------");

    // Pizza order details
    System.out.print("Enter Toppings:           ");
    PIE.setToppings(scan.nextLine());

    System.out.print("Enter Diameter in inches: ");
    PIE.setDiameter(scan.nextInt());

    System.out.print("Enter How Many?:          ");
    PIE.setHowMany(scan.nextInt());

    scan.nextLine();

    System.out.print("Enter Type of Discount:   ");
    PIE.setDiscount(scan.nextLine());

    billOrder.OUT(PIE);

    double amountDue = PIE.getPrice();
    System.out.print("\nAmount Due:               PHP " + amountDue);

    // User's payment
    double amountReceived;
    do {
      System.out.print("\nEnter Amount Received:    PHP ");
      amountReceived = scan.nextDouble();
      if (amountReceived < amountDue) {
        System.out.println("\t\t\t  Insufficient amount!");
      }
    } while (amountReceived <= amountDue);

    // Change
    double change = amountReceived - amountDue;
    System.out.println("Change:                   PHP " + change);
    System.out.println("\n****Thank you for using Pizza Pie Ordering Program.****\n");

    // Close the scanner
    scan.close();
  }
}