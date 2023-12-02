package COMP009;

import javax.swing.JOptionPane;
import javax.swing.JComboBox;

class PIZZA {
    String Toppings, Discount;
    int Diameter, HowMany;
    double Price;

    // Method to set the toppings using a dialog box
    public void setToppings() {
        String[] toppingsOptions = {"", "Pepperoni", "Ham and Cheese", "Hawaiian"};

        JComboBox<String> comboBox = new JComboBox<>(toppingsOptions);
        int option = JOptionPane.showConfirmDialog(null, comboBox, "Choose Toppings",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (option == JOptionPane.OK_OPTION) {
            this.Toppings = (String) comboBox.getSelectedItem();
            displayToppingsConfirmation();  // Add this line to display the confirmation dialog
        }
    }

    // Method to display a confirmation dialog for chosen toppings
    public void displayToppingsConfirmation() {
        double toppingsPrice = getToppingsPrice(this.Toppings);

        JOptionPane.showMessageDialog(null,
                "You chose \"" + this.Toppings + "\" which amounts to additional PHP " + toppingsPrice,
                "Message", JOptionPane.INFORMATION_MESSAGE);

        confirmToppings();  // Ask the user if they are sure with their choice
    }

    // Method to confirm the chosen toppings
    public void confirmToppings() {
        int option = JOptionPane.showConfirmDialog(null, "Are you sure you want \"" + this.Toppings + "\"?",
                "Confirm Question", JOptionPane.YES_NO_OPTION);

        if (option == JOptionPane.NO_OPTION) {
            setToppings();  // Choose again from the Toppings selection dialog box
        }
    }

    // Method to set the diameter using a dialog box
    public void setDiameter() {
        String diameterInput = JOptionPane.showInputDialog("Enter Diameter in Inches:");

        if (diameterInput == null) { // Check if the user pressed cancel
            if (confirmDiameterCancellation()) {
                JOptionPane.showMessageDialog(null, "Thank you!");
                System.exit(0); // End the program
            } else {
                setDiameter(); // Enter again the size of the pizza and proceed with the program
            }
        } else {
            this.Diameter = Integer.parseInt(diameterInput);
        }
    }

    // Method to confirm the cancellation of the diameter input
    public boolean confirmDiameterCancellation() {
        int option = JOptionPane.showConfirmDialog(null, "Want to CANCEL the order?", "Confirm Question", JOptionPane.YES_NO_OPTION);
        return option == JOptionPane.YES_OPTION;
    }

    // Method to set the number of pizzas to order
    public void setHowMany() {
        Integer howManyInput = null;
        int option;

        do {
            try {
                howManyInput = Integer.parseInt(JOptionPane.showInputDialog("Enter How Many?"));
            } catch (NumberFormatException e) {
                if (howManyInput == null) {
                    option = JOptionPane.showConfirmDialog(null, "Want to CANCEL the order?", "Confirm Question", JOptionPane.YES_NO_OPTION);
                    if (option == JOptionPane.YES_OPTION) {
                        JOptionPane.showMessageDialog(null, "Thank you!");
                        System.exit(0); // End the program
                    } else {
                        // User selected "No," so allow them to enter the quantity again
                        continue;
                    }
                }
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number.");
            }
        } while (howManyInput == null);

        this.HowMany = howManyInput;
    }

    // Method to confirm the cancellation of the number of pizzas input
    public boolean confirmHowManyCancellation() {
        int option = JOptionPane.showConfirmDialog(null, "Want to CANCEL the order?", "Confirm Question", JOptionPane.YES_NO_OPTION);
        return option == JOptionPane.YES_OPTION;
    }

    // Method to set the discount using a dialog box
    public void setDiscount() {
        String[] discountOptions = {"N/A", "Senior/PWD", "VIP"};
        int choice = JOptionPane.showOptionDialog(null, "Choose Discount", "Pizza Program",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, discountOptions, discountOptions[0]);

        this.Discount = discountOptions[choice];
        if (!this.Discount.equals("N/A")) {
            JOptionPane.showInputDialog("Enter " + this.Discount + " Card Number:");
        }
    }

    // Method to calculate and return the total price of the order
    public double getPrice() {
        double pricePerCm = 5.0;

        double toppingsPrice = getToppingsPrice(this.Toppings);

        double diameterInCm = this.Diameter * 2.54;
        double diameterRounded = Math.round(diameterInCm);
        double diameterPrice = (diameterRounded * pricePerCm);
        double totalPrice = (toppingsPrice + diameterPrice) * this.HowMany;

        // Apply discount based on the selected discount option
        if (this.Discount.toLowerCase().equals("senior citizen")) {
            totalPrice *= 0.8;
        } else if (this.Discount.toLowerCase().equals("vip")) {
            totalPrice *= 0.88;
        }

        this.Price = totalPrice;
        return this.Price;
    }

    // Private method to get the price of the selected toppings
    private double getToppingsPrice(String toppings) {
        switch (toppings.toLowerCase()) {
            case "pepperoni":
                return 100.0;
            case "ham and cheese":
                return 150.0;
            case "hawaiian":
                return 200.0;
            default:
                return 0.0;
        }
    }
}

class BILL_ORDER {
    // Method to display the order details
    public void OUT(PIZZA horray) {
        String toppings = horray.Toppings;
        int diameter = horray.Diameter;

        JOptionPane.showMessageDialog(null,
                "You ordered for " + toppings + " Pizza with the size of " + Math.round(diameter * 2.54) +
                        " centimeter for PHP " + horray.getPrice() + ".");
    }
}

// Main class to execute the pizza ordering program
public class PizzaPie {
    public static void main(String[] args) {
        PIZZA PIE = new PIZZA();
        BILL_ORDER billOrder = new BILL_ORDER();

        try {
            PIE.setToppings();
            PIE.setDiameter();
            PIE.setHowMany();
            PIE.setDiscount();

            billOrder.OUT(PIE);

            double amountDue = PIE.getPrice();
            JOptionPane.showMessageDialog(null, "Amount Due: PHP " + amountDue);

            double amountReceived;
            do {
                String amountReceivedInput = JOptionPane.showInputDialog("Enter Amount Received (PHP):");
                amountReceived = Double.parseDouble(amountReceivedInput);
                if (amountReceived < amountDue) {
                    JOptionPane.showMessageDialog(null, "Insufficient amount!");
                }
            } while (amountReceived <= amountDue);

            double change = amountReceived - amountDue;
            JOptionPane.showMessageDialog(null, "Change: PHP " + change);
            JOptionPane.showMessageDialog(null, "Thank you!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Invalid input. Order canceled. Thank you!");
        }
    }
}
