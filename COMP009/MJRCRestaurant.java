package COMP009;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Order {
  private double soupPrice = 25.00;
  private double coffeePrice = 20.00;
  private double siopaoPrice = 15.00;

  private int numSoup = 0;
  private int numCoffee = 0;
  private int numSiopao = 0;

  public double calculateTotal() {
    return (numSoup * soupPrice) + (numCoffee * coffeePrice) + (numSiopao * siopaoPrice);
  }

  public double calculateChange(double amountGiven, double totalBill) {
    return amountGiven - totalBill;
  }

  public void clearOrder() {
    numSoup = 0;
    numCoffee = 0;
    numSiopao = 0;
  }

  public void setNumSoup(int numSoup) {
    this.numSoup = numSoup;
  }

  public void setNumCoffee(int numCoffee) {
    this.numCoffee = numCoffee;
  }

  public void setNumSiopao(int numSiopao) {
    this.numSiopao = numSiopao;
  }

  public double getSoupPrice() {
    return soupPrice;
  }

  public double getCoffeePrice() {
    return coffeePrice;
  }

  public double getSiopaoPrice() {
    return siopaoPrice;
  }
}

public class MJRCRestaurant {
  private Order order;

  private JFrame frame;
  private JTextField amountField, totalField, changeField;
  private JCheckBox soupCheckbox, coffeeCheckbox, siopaoCheckbox;
  private JTextField soupQuantity, coffeeQuantity, siopaoQuantity;
  private JButton computeBillBtn, computeChangeBtn, clearBtn, closeBtn;

  public MJRCRestaurant() {
    order = new Order();
    initializeGUI();
  }

  private void initializeGUI() {

    frame = new JFrame("MJRC Restaurant");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(400, 250);
    frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

    JPanel orderPanel = new JPanel();
    orderPanel.setLayout(new GridLayout(3, 2, 5, 5));

    soupCheckbox = new JCheckBox("Soup: P25.00");
    orderPanel.add(soupCheckbox);
    soupQuantity = new JTextField(5);
    orderPanel.add(soupQuantity);

    coffeeCheckbox = new JCheckBox("Coffee: P20.00");
    orderPanel.add(coffeeCheckbox);
    coffeeQuantity = new JTextField(5);
    orderPanel.add(coffeeQuantity);

    siopaoCheckbox = new JCheckBox("Siopao: P15.00");
    orderPanel.add(siopaoCheckbox);
    siopaoQuantity = new JTextField(5);
    orderPanel.add(siopaoQuantity);

    frame.add(orderPanel);

    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new FlowLayout());

    computeBillBtn = new JButton("Compute Bill");
    buttonPanel.add(computeBillBtn);

    clearBtn = new JButton("Clear All");
    buttonPanel.add(clearBtn);

    frame.add(buttonPanel);

    JPanel totalPanel = new JPanel();
    totalPanel.setLayout(new FlowLayout());

    totalPanel.add(new JLabel("Total Bill is:"));
    totalField = new JTextField(10);
    totalField.setEditable(false);
    totalPanel.add(totalField);

    frame.add(totalPanel);

    JPanel changePanel = new JPanel();
    changePanel.setLayout(new FlowLayout());

    amountField = new JTextField(10);
    changePanel.add(new JLabel("Amount Given:"));
    changePanel.add(amountField);

    changePanel.add(new JLabel("Change:"));
    changeField = new JTextField(10);
    changeField.setEditable(false);
    changePanel.add(changeField);

    frame.add(changePanel);

    JPanel computationPanel = new JPanel();
    computationPanel.setLayout(new FlowLayout());

    computeChangeBtn = new JButton("Compute Change");
    computationPanel.add(computeChangeBtn);

    closeBtn = new JButton("Close");
    computationPanel.add(closeBtn);

    frame.add(computationPanel);

    // Add Action Listeners to Buttons
    computeBillBtn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        computeBill();
      }
    });

    clearBtn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        clearFields();
      }
    });

    computeChangeBtn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        computeChange();
      }
    });

    closeBtn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        int confirmClose = JOptionPane.showConfirmDialog(frame,
            "Are you sure you want to close the application?", "Confirm Close", JOptionPane.YES_NO_OPTION);

        if (confirmClose == JOptionPane.YES_OPTION) {
          frame.dispose();
        }
      }
    });

    frame.setVisible(true);
  }

  private void computeBill() {
    double totalBill = 0.0;

    if (soupCheckbox.isSelected()) {
      try {
        int numSoup = Integer.parseInt(soupQuantity.getText());
        totalBill += numSoup * order.getSoupPrice();
      } catch (NumberFormatException ignored) {
      }
    }

    if (coffeeCheckbox.isSelected()) {
      try {
        int numCoffee = Integer.parseInt(coffeeQuantity.getText());
        totalBill += numCoffee * order.getCoffeePrice();
      } catch (NumberFormatException ignored) {
      }
    }

    if (siopaoCheckbox.isSelected()) {
      try {
        int numSiopao = Integer.parseInt(siopaoQuantity.getText());
        totalBill += numSiopao * order.getSiopaoPrice();
      } catch (NumberFormatException ignored) {
      }
    }

    totalField.setText(String.valueOf(totalBill));
  }

  private void computeChange() {
    try {
      double amountGiven = Double.parseDouble(amountField.getText());
      double totalBill = Double.parseDouble(totalField.getText());

      if (amountGiven < totalBill) {
        JOptionPane.showMessageDialog(frame, "Kulang Bayad Nyo!");
      } else {
        double change = order.calculateChange(amountGiven, totalBill);
        changeField.setText(String.valueOf(change));
      }
    } catch (NumberFormatException ex) {
      JOptionPane.showMessageDialog(frame, "Invalid Input!");
    }
  }

  private void clearFields() {
    int confirmClear = JOptionPane.showConfirmDialog(frame, "Are you sure you want to clear all fields?",
        "Confirm Clear", JOptionPane.YES_NO_OPTION);

    if (confirmClear == JOptionPane.YES_OPTION) {
      soupCheckbox.setSelected(false);
      coffeeCheckbox.setSelected(false);
      siopaoCheckbox.setSelected(false);
      soupQuantity.setText("");
      coffeeQuantity.setText("");
      siopaoQuantity.setText("");
      amountField.setText("");
      totalField.setText("");
      changeField.setText("");
      order.clearOrder();
    }
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        new MJRCRestaurant();
      }
    });
  }
}