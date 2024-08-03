package System;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;

import model.PhoneService;
import model.PhoneServiceManager;

public class PhoneServiceGUI extends JPanel {
    private JTextField phoneNumberField;
    private JTextField callDurationField;
    private JButton addButton;
    private JButton editButton;
    private JButton deleteButton;
    private JButton searchButton;
    private JTextArea displayArea;

    private PhoneServiceManager phoneServiceManager;

    public PhoneServiceGUI() {
        phoneServiceManager = new PhoneServiceManager();

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        setBackground(new Color(240, 240, 240));
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Adding phone number field
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        JLabel phoneNumberLabel = new JLabel("Phone Number:");
        phoneNumberLabel.setFont(new Font("Roboto", Font.PLAIN, 18));
        add(phoneNumberLabel, gbc);

        gbc.gridx = 1;
        phoneNumberField = new JTextField(20);
        add(phoneNumberField, gbc);

        // Adding call duration field
        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel callDurationLabel = new JLabel("Call Duration (min):");
        callDurationLabel.setFont(new Font("Roboto", Font.PLAIN, 18));
        add(callDurationLabel, gbc);

        gbc.gridx = 1;
        callDurationField = new JTextField(20);
        add(callDurationField, gbc);

        // Adding buttons
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        addButton = new JButton("Add");
        addButton.setFont(new Font("Roboto", Font.PLAIN, 18));
        addButton.setBackground(new Color(33, 150, 243));
        addButton.setForeground(Color.WHITE);
        add(addButton, gbc);

        gbc.gridx = 1;
        editButton = new JButton("Edit");
        editButton.setFont(new Font("Roboto", Font.PLAIN, 18));
        editButton.setBackground(new Color(33, 150, 243));
        editButton.setForeground(Color.WHITE);
        add(editButton, gbc);

        gbc.gridx = 2;
        deleteButton = new JButton("Delete");
        deleteButton.setFont(new Font("Roboto", Font.PLAIN, 18));
        deleteButton.setBackground(new Color(33, 150, 243));
        deleteButton.setForeground(Color.WHITE);
        add(deleteButton, gbc);

        gbc.gridx = 3;
        searchButton = new JButton("Search");
        searchButton.setFont(new Font("Roboto", Font.PLAIN, 18));
        searchButton.setBackground(new Color(33, 150, 243));
        searchButton.setForeground(Color.WHITE);
        add(searchButton, gbc);

        // Display area
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 4;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        displayArea = new JTextArea();
        displayArea.setEditable(true); // Allow editing
        JScrollPane scrollPane = new JScrollPane(displayArea);
        add(scrollPane, gbc);

        // Button actions
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String phoneNumber = phoneNumberField.getText();
                int callDuration = Integer.parseInt(callDurationField.getText());
                phoneServiceManager.addPhoneService(new PhoneService(phoneNumber, callDuration));
                displayAllPhoneServices();
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String phoneNumber = phoneNumberField.getText();
                int newCallDuration = Integer.parseInt(callDurationField.getText());
                phoneServiceManager.editPhoneService(phoneNumber, newCallDuration);
                displayAllPhoneServices();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String phoneNumber = phoneNumberField.getText();
                phoneServiceManager.deletePhoneService(phoneNumber);
                displayAllPhoneServices();
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String phoneNumber = phoneNumberField.getText();
                PhoneService service = phoneServiceManager.searchPhoneService(phoneNumber);
                if (service != null) {
                    displayArea.setText(service.toString());
                } else {
                    displayArea.setText("Phone service not found.");
                }
            }
        });
    }

    private void displayAllPhoneServices() {
        StringBuilder builder = new StringBuilder();
        for (PhoneService service : phoneServiceManager.getAllPhoneServices()) {
            builder.append(service.toString()).append("\n");
        }
        displayArea.setText(builder.toString());
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        JFrame frame = new JFrame("Phone Service Management");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.add(new PhoneServiceGUI());
        frame.setVisible(true);
    }
}
