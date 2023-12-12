package COMP009;

// Programming Task 7
// CASTRO, MACASINAG, NARISMA, ROMALES

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class OrderFrame extends Frame {

    private JTextField userNameTextField, totalBillTextField;
    private JPasswordField passwordTextField;
    private JCheckBox meal1CheckBox, meal2CheckBox, meal3CheckBox;
    private JComboBox<String> drinksDropdown;
    private JRadioButton extraRiceRadio, noExtraRiceRadio;
    private ButtonGroup extraRiceGroup;
    private JButton okButton, computeButton, clearButton, closeButton;

    public OrderFrame() {
        setTitle("Jolly McKing Order Form");
        setSize(400, 300);
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        // Row 1: Username(label) (text field)
        add(new Label("Username:"));
        userNameTextField = new JTextField(15);
        add(userNameTextField);

        // Row 2: Password(label) (text field) OK(button)
        add(new Label("Password:"));
        passwordTextField = new JPasswordField(15);
        passwordTextField.setEchoChar('*'); // Set echo char to '*'
        add(passwordTextField);
        okButton = new JButton("OK");
        add(okButton);

        // Row 3: Choose Your Order:(label)
        add(new Label("Choose Your Order:"));
        add(new Label(""));
        add(new Label(""));

        // Row 4: (checkbox) Value Meal #1: P25.00(label)
        meal1CheckBox = new JCheckBox("Value Meal #1: P25.00");
        add(meal1CheckBox);

        // Row 5: (checkbox) Value Meal #2: P30.00(label)
        meal2CheckBox = new JCheckBox("Value Meal #2: P30.00");
        add(meal2CheckBox);

        // Row 6: (checkbox) Value Meal #3: P35.00(label) Coke, Sprite, Royal(dropdown list)
        meal3CheckBox = new JCheckBox("Value Meal #3: P35.00");
        add(meal3CheckBox);
        String[] drinks = {"Coke", "Sprite", "Royal"};
        drinksDropdown = new JComboBox<>(drinks);
        add(drinksDropdown);

        // Row 7: (radio button) Extra Rice (radio button) No Extra Rice Compute(button)
        extraRiceRadio = new JRadioButton("Extra Rice");
        noExtraRiceRadio = new JRadioButton("No Extra Rice");
        extraRiceGroup = new ButtonGroup();
        extraRiceGroup.add(extraRiceRadio);
        extraRiceGroup.add(noExtraRiceRadio);
        add(extraRiceRadio);
        add(noExtraRiceRadio);
        computeButton = new JButton("Compute");
        add(computeButton);

        // Row 8: Total Bill is:(label) (text field)
        add(new Label("Total Bill is:"));
        totalBillTextField = new JTextField(15);
        totalBillTextField.setEditable(false);
        add(totalBillTextField);

        // Row 9: Clear(button) Close(button)
        clearButton = new JButton("Clear");
        closeButton = new JButton("Close");

        // Set the layout for Row 9 to FlowLayout with right alignment
        FlowLayout row9Layout = new FlowLayout(FlowLayout.RIGHT);
        Panel row9Panel = new Panel(row9Layout);
        row9Panel.add(clearButton);
        row9Panel.add(closeButton);
        add(row9Panel);

        // Disable ordering part initially
        disableOrdering();

        // Event Listeners
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Check the password and enable ordering part
                try {
                    String password = "comp009";
                    char[] enteredPassword = passwordTextField.getPassword();
                    if (Arrays.equals(enteredPassword, password.toCharArray())) {
                        enableOrdering();
                    } else {
                        JOptionPane.showMessageDialog(OrderFrame.this, "Invalid Password");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(OrderFrame.this, "Invalid Input!");
                }
            }
        });

        computeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Compute total bill based on selected items
                try {
                    double totalBill = 0.0;

                    if (meal1CheckBox.isSelected()) {
                        totalBill += 25.0;
                    }
                    if (meal2CheckBox.isSelected()) {
                        totalBill += 30.0;
                    }
                    if (meal3CheckBox.isSelected()) {
                        totalBill += 35.0;
                    }
                    if (extraRiceRadio.isSelected()) {
                        totalBill += 25.0;
                    }

                    totalBillTextField.setText(String.format("%.2f", totalBill));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(OrderFrame.this, "Invalid Input!");
                }
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Display confirmation dialog and clear ordering part
                int result = JOptionPane.showConfirmDialog(OrderFrame.this, "Are you sure you want to clear?");
                if (result == JOptionPane.YES_OPTION) {
                    clearOrdering();
                }
            }
        });

        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Display confirmation dialog and close the form
                int result = JOptionPane.showConfirmDialog(OrderFrame.this, "Are you sure you want to exit?");
                if (result == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
    }

    private void enableOrdering() {
        meal1CheckBox.setEnabled(true);
        meal2CheckBox.setEnabled(true);
        meal3CheckBox.setEnabled(true);
        extraRiceRadio.setEnabled(true);
        noExtraRiceRadio.setEnabled(true);
        drinksDropdown.setEnabled(true);
        computeButton.setEnabled(true);
        clearButton.setEnabled(true);
    }

    private void clearOrdering() {
        meal1CheckBox.setSelected(false);
        meal2CheckBox.setSelected(false);
        meal3CheckBox.setSelected(false);
        extraRiceGroup.clearSelection();
        drinksDropdown.setSelectedIndex(0);
        totalBillTextField.setText("");
    }

    private void disableOrdering() {
        meal1CheckBox.setEnabled(false);
        meal2CheckBox.setEnabled(false);
        meal3CheckBox.setEnabled(false);
        extraRiceRadio.setEnabled(false);
        noExtraRiceRadio.setEnabled(false);
        drinksDropdown.setEnabled(false);
        computeButton.setEnabled(false);
        clearButton.setEnabled(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new OrderFrame().setVisible(true);
            }
        });
    }
}