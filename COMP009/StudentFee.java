package COMP009;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentFee extends JFrame implements ActionListener {

    private JTextField studentNameField, unitsEnrolledField, totalAmountField;
    private JComboBox<String> yearLevelComboBox;
    private JCheckBox[] otherFeesCheckboxes;
    private ButtonGroup scholarshipGroup;
    private JRadioButton nonScholarRadio, fullScholarRadio, partialScholarRadio;

    public StudentFee() {
        setTitle("Student Fee");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initUI();

        // Pack the frame to fit the preferred sizes of components
        pack();
    }

    private void initUI() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Set background color for the main panel
        mainPanel.setBackground(Color.decode("#F2F2F2"));

        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(new JLabel("Student Name:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        studentNameField = new JTextField(15);
        mainPanel.add(studentNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(new JLabel("Units Enrolled:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        unitsEnrolledField = new JTextField(15);
        mainPanel.add(unitsEnrolledField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        mainPanel.add(new JLabel("Year Level:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        String[] yearLevels = {"", "1st Year", "2nd Year", "3rd Year", "4th Year", "5th Year"};
        yearLevelComboBox = new JComboBox<>(yearLevels);
        yearLevelComboBox.setSelectedIndex(0); // Default to blank or space
        mainPanel.add(yearLevelComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        mainPanel.add(new JLabel("Other Fees:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        otherFeesCheckboxes = new JCheckBox[6];
        otherFeesCheckboxes[0] = new JCheckBox("Laboratory Fee P200");
        otherFeesCheckboxes[1] = new JCheckBox("Student Council P50");
        otherFeesCheckboxes[2] = new JCheckBox("Registration Card P50");
        otherFeesCheckboxes[3] = new JCheckBox("Student ID P50");
        otherFeesCheckboxes[4] = new JCheckBox("Catalyst P50");
        otherFeesCheckboxes[5] = new JCheckBox("Other Miscellaneous P100");

        JPanel otherFeesPanel = new JPanel();
        otherFeesPanel.setLayout(new GridLayout(3, 2, 5, 5)); // 3 rows x 2 columns with gaps
        for (JCheckBox checkbox : otherFeesCheckboxes) {
            otherFeesPanel.add(checkbox);
        }
        mainPanel.add(otherFeesPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        mainPanel.add(new JLabel("Scholarship Grants:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 4;
        scholarshipGroup = new ButtonGroup();
        nonScholarRadio = new JRadioButton("Non-Scholar", true); // Default to Non-Scholar
        fullScholarRadio = new JRadioButton("Full Scholar");
        partialScholarRadio = new JRadioButton("Partial Scholar");
        scholarshipGroup.add(nonScholarRadio);
        scholarshipGroup.add(fullScholarRadio);
        scholarshipGroup.add(partialScholarRadio);

        JPanel scholarshipPanel = new JPanel();
        scholarshipPanel.setLayout(new GridLayout(1, 3, 5, 5)); // 1 row x 3 columns with gaps
        scholarshipPanel.add(nonScholarRadio);
        scholarshipPanel.add(fullScholarRadio);
        scholarshipPanel.add(partialScholarRadio);
        mainPanel.add(scholarshipPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        mainPanel.add(new JLabel("Total Amount:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 5;
        totalAmountField = new JTextField(15);
        totalAmountField.setEditable(false); // Set non-editable
        mainPanel.add(totalAmountField, gbc);

        // Create a new panel for Compute and Clear buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton computeButton = new JButton("Compute");
        JButton clearButton = new JButton("Clear");
        buttonPanel.add(computeButton);
        buttonPanel.add(clearButton);

        // Set button colors
        computeButton.setBackground(Color.decode("#4CAF50"));
        computeButton.setForeground(Color.WHITE);
        clearButton.setBackground(Color.decode("#FF5722"));
        clearButton.setForeground(Color.WHITE);

        computeButton.addActionListener(this);
        clearButton.addActionListener(this);

        // Add button panel to the main panel
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        mainPanel.add(buttonPanel, gbc);

        add(mainPanel);
    }

    private void computeTotalAmount() {
        try {
            int unitsEnrolled = Integer.parseInt(unitsEnrolledField.getText());

            String selectedYear = (String) yearLevelComboBox.getSelectedItem();
            if (selectedYear.equals("")) {
                JOptionPane.showMessageDialog(this, "Please choose a correct Year Level.");
                return;
            }

            int yearLevelFee = 0;
            if (selectedYear.equals("1st Year")) yearLevelFee = 100;
            else if (selectedYear.equals("2nd Year")) yearLevelFee = 200;
            else if (selectedYear.equals("3rd Year")) yearLevelFee = 300;
            else if (selectedYear.equals("4th Year")) yearLevelFee = 400;
            else if (selectedYear.equals("5th Year")) yearLevelFee = 500;

            int otherFeesTotal = 0;
            for (JCheckBox checkbox : otherFeesCheckboxes) {
                if (checkbox.isSelected()) {
                    String feeText = checkbox.getText();
                    int feeAmount = Integer.parseInt(feeText.substring(feeText.lastIndexOf('P') + 1));
                    otherFeesTotal += feeAmount;
                }
            }

            String scholarshipType = nonScholarRadio.isSelected() ? "Non-Scholar" :
                    (fullScholarRadio.isSelected() ? "Full Scholar" : "Partial Scholar");

            int totalAmount = 0;
            if (scholarshipType.equals("Non-Scholar")) {
                totalAmount = (unitsEnrolled * 10) + otherFeesTotal + yearLevelFee;
            } else if (scholarshipType.equals("Partial Scholar")) {
                totalAmount = ((unitsEnrolled * 10) + otherFeesTotal + yearLevelFee) / 2;
            }

            totalAmountField.setText(String.valueOf(totalAmount));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please input a valid positive integer for Units Enrolled.");
        }
    }

    private void clearForm() {
        studentNameField.setText("");
        unitsEnrolledField.setText("");
        yearLevelComboBox.setSelectedIndex(0);

        for (JCheckBox checkbox : otherFeesCheckboxes) {
            checkbox.setSelected(false);
        }

        nonScholarRadio.setSelected(true);
        totalAmountField.setText(""); // Set empty
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StudentFee().setVisible(true));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Compute")) {
            computeTotalAmount();
        } else if (e.getActionCommand().equals("Clear")) {
            clearForm();
        }
    }
}