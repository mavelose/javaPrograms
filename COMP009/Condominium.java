package COMP009;

import java.util.Scanner;

public class Condominium {
    public static void main(String[] args) {
        // Declare variables
        double priceSQM, sqm, totalUnitPrice, downPayment, percentDownPay, balance, yearsPay, monthlyAmortization,
                lessDiscountAmount, interestAmount, contractPrice;
        String unitType;
        Scanner unit = new Scanner(System.in);

        // Input the area in square meters
        System.out.println("************************************************");
        System.out.println("*********** Unit Payment Transaction ***********");
        System.out.println("************************************************\n");

        System.out.print("Enter Area (in square meters): ");
        sqm = unit.nextDouble();

        // Determine the unit type and price per square meter
        if (sqm >= 28.5 && sqm < 52.0) {
            unitType = "Studio Type";
            priceSQM = 65892.00;
        } else if (sqm >= 52.0 && sqm < 75.0) {
            unitType = "2 Bedroom";
            priceSQM = 58807.00;
        } else if (sqm >= 75.0) {
            unitType = "3 Bedroom";
            priceSQM = 53380.00;
        } else {
            System.out.println("Invalid Area");
            unit.close(); // Close the Scanner object
            return;
        }

        // Calculate the total unit price
        totalUnitPrice = sqm * priceSQM;

        // Display unit information
        System.out.println("\n************************************************");
        System.out.println("*************** Unit Information ***************");
        System.out.println("************************************************\n");
        System.out.println("Unit Type: " + unitType);
        System.out.println("Price per Square Meter: P " + String.format("%.2f", priceSQM));
        System.out.println("Total Unit Price: P " + String.format("%.2f", totalUnitPrice));

        // Input the downpayment amount and calculate downpayment percentage
        System.out.print("\nDownpayment Amount: P ");
        downPayment = unit.nextDouble();
        percentDownPay = (downPayment / totalUnitPrice) * 100;
        System.out.println("Downpayment in Percentage: " + String.format("%.2f", percentDownPay) + "%\n");

        // Calculate the balance and apply the discount based on the downpayment percentage
        balance = totalUnitPrice - downPayment;
        System.out.println("\n***********************************************");
        System.out.println("*************** Payment Details ***************");
        System.out.println("***********************************************\n");
        System.out.println("Balance: P " + String.format("%.2f", balance));

        double discountAmount = 0.0; // Provide an initial default value
        if (percentDownPay <= 19) {
            discountAmount = 0;
            System.out.println("Discount: No Discount");
        } else if (percentDownPay <= 29) {
            discountAmount = 0.03;
            System.out.println("Discount: 3%");
        } else if (percentDownPay <= 39) {
            discountAmount = 0.04;
            System.out.println("Discount: 4%");
        } else if (percentDownPay >= 40) {
            discountAmount = 0.05;
            System.out.println("Discount: 5%");
        } else {
            System.out.println("Invalid");
        }

        // Calculate the discount amount
        lessDiscountAmount = balance * discountAmount;
        System.out.println("Less: Discount Amount: P " + String.format("%.2f", lessDiscountAmount));

        // Input the number of years to pay and calculate the interest rate
        System.out.print("\nYears to Pay: ");
        yearsPay = unit.nextDouble();
        double interest = 0.0;
        if (yearsPay == 5) {
            interest = 0.04;
            System.out.println("Interest: 4%");
        } else if (yearsPay == 10) {
            interest = 0.06;
            System.out.println("Interest: 6%");
        } else if (yearsPay == 15) {
            interest = 0.08;
            System.out.println("Interest: 8%");
        } else {
            interest = 0.10;
            System.out.println("Interest: 10%");
        }

        // Calculate the interest amount and contract price
        interestAmount = (balance - lessDiscountAmount) * interest;
        contractPrice = (balance - lessDiscountAmount) + interestAmount;

        // Calculate the monthly amortization
        int numberOfMonths = (int) (yearsPay * 12);
        monthlyAmortization = contractPrice / numberOfMonths;

        // Display financial information
        System.out.println("\n***********************************************");
        System.out.println("************ Financial Information ************");
        System.out.println("***********************************************\n");
        System.out.println("Interest Amount: P " + String.format("%.2f", interestAmount));
        System.out.println("Contract Price: P " + String.format("%.2f", contractPrice));
        System.out.println("Monthly Amortization: P " + String.format("%.2f", monthlyAmortization));
        System.out.println("\n***********************************************\n");

        unit.close();
    }
}
