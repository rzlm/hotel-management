package System;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import model.Bill;

public class BillGUI extends JPanel {
    private JTextField amountField;
    private JCheckBox statusCheckBox;
    private JTextField customerNameField;
    private JButton createBillButton;
    private JButton updateBillButton;

    // Map to store bill statuses
    private Map<String, Bill> billMap; // Customer name to Bill map

    public BillGUI() {
        billMap = new HashMap<>(); // Initialize the bill map

        setLayout(new GridBagLayout());
        setBackground(new Color(240, 240, 240));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Adding components
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel titleLabel = new JLabel("Manage Bill");
        titleLabel.setFont(new Font("Roboto", Font.BOLD, 18));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(titleLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel amountLabel = new JLabel("Amount:");
        amountLabel.setFont(new Font("Roboto", Font.PLAIN, 18));
        add(amountLabel, gbc);

        gbc.gridx = 1;
        amountField = new JTextField(20);
        add(amountField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel statusLabel = new JLabel("Status:");
        statusLabel.setFont(new Font("Roboto", Font.PLAIN, 18));
        add(statusLabel, gbc);

        gbc.gridx = 1;
        statusCheckBox = new JCheckBox("Paid");
        statusCheckBox.setFont(new Font("Roboto", Font.PLAIN, 18));
        add(statusCheckBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        JLabel customerNameLabel = new JLabel("Guest Name:");
        customerNameLabel.setFont(new Font("Roboto", Font.PLAIN, 18));
        add(customerNameLabel, gbc);

        gbc.gridx = 1;
        customerNameField = new JTextField(20);
        add(customerNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        createBillButton = new JButton("Create Bill");
        createBillButton.setFont(new Font("Roboto", Font.PLAIN, 18));
        createBillButton.setBackground(new Color(33, 150, 243)); // Blue color
        createBillButton.setForeground(Color.WHITE);
        add(createBillButton, gbc);

        gbc.gridx = 1;
        updateBillButton = new JButton("Update Bill");
        updateBillButton.setFont(new Font("Roboto", Font.PLAIN, 18));
        updateBillButton.setBackground(new Color(33, 150, 243)); // Blue color
        updateBillButton.setForeground(Color.WHITE);
        add(updateBillButton, gbc);

        // Button actions
        createBillButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double amount = Double.parseDouble(amountField.getText());
                    boolean status = statusCheckBox.isSelected();
                    String customerName = customerNameField.getText();

                    // Create or update the bill in the map
                    Bill bill = new Bill();
                    bill.setAmount(amount);
                    bill.setStatus(status);
                    bill.setCustomerName(new String[] { customerName });

                    bill.createBill();
                    billMap.put(customerName, bill); // Store bill in the map

                    JOptionPane.showMessageDialog(null, "Bill Created for " + customerName);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid amount format.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        updateBillButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double amount = Double.parseDouble(amountField.getText());
                    boolean status = statusCheckBox.isSelected();
                    String customerName = customerNameField.getText();

                    if (billMap.containsKey(customerName)) {
                        // Update the existing bill
                        Bill bill = billMap.get(customerName);
                        bill.setAmount(amount);
                        bill.setStatus(status);
                        bill.setCustomerName(new String[] { customerName });

                        bill.updateBill();

                        JOptionPane.showMessageDialog(null, "Bill Updated for " + customerName);
                    } else {
                        JOptionPane.showMessageDialog(null, "No bill found for " + customerName, "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid amount format.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Bill Management");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.add(new BillGUI());
        frame.setVisible(true);
    }
}
