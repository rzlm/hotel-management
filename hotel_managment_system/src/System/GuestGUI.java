package System;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import model.Guest;

public class GuestGUI extends JPanel {
    private JTextField nameField;
    private JTextField emailField;
    private JTextField phoneField;
    private JTextField preferenceField;
    private JTextField roomServiceField;
    private JButton saveButton;
    private JButton orderRoomServiceButton;
    private JButton removeButton;

    // ArrayList to store guest information
    private ArrayList<Guest> guestList;

    public GuestGUI() {
        guestList = new ArrayList<>(); // Initialize the guest list

        setLayout(new GridBagLayout());
        setBackground(new Color(240, 240, 240));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Adding title
        JLabel titleLabel = new JLabel("Add a new Guest");
        titleLabel.setFont(new Font("Roboto", Font.BOLD, 18));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(titleLabel, gbc);

        // Adding name field
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(new Font("Roboto", Font.PLAIN, 18));
        add(nameLabel, gbc);

        gbc.gridx = 1;
        nameField = new JTextField(20);
        add(nameField, gbc);

        // Adding email field
        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Roboto", Font.PLAIN, 18));
        add(emailLabel, gbc);

        gbc.gridx = 1;
        emailField = new JTextField(20);
        add(emailField, gbc);

        // Adding phone field
        gbc.gridx = 0;
        gbc.gridy = 3;
        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setFont(new Font("Roboto", Font.PLAIN, 18));
        add(phoneLabel, gbc);

        gbc.gridx = 1;
        phoneField = new JTextField(20);
        add(phoneField, gbc);

        // Adding preference field
        gbc.gridx = 0;
        gbc.gridy = 4;
        JLabel preferenceLabel = new JLabel("Preference:");
        preferenceLabel.setFont(new Font("Roboto", Font.PLAIN, 18));
        add(preferenceLabel, gbc);

        gbc.gridx = 1;
        preferenceField = new JTextField(20);
        add(preferenceField, gbc);

        // Adding room service field
        gbc.gridx = 0;
        gbc.gridy = 6;
        JLabel roomServiceLabel = new JLabel("Room Service:");
        roomServiceLabel.setFont(new Font("Roboto", Font.PLAIN, 18));
        add(roomServiceLabel, gbc);

        gbc.gridx = 1;
        roomServiceField = new JTextField(20);
        add(roomServiceField, gbc);

        // Adding save button
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        saveButton = new JButton("Save");
        saveButton.setFont(new Font("Roboto", Font.PLAIN, 18));
        saveButton.setBackground(new Color(33, 150, 243));
        saveButton.setForeground(Color.WHITE);
        add(saveButton, gbc);

        // Adding remove button
        gbc.gridy = 8;
        removeButton = new JButton("Remove Guest");
        removeButton.setFont(new Font("Roboto", Font.PLAIN, 18));
        removeButton.setBackground(new Color(255, 87, 34));
        removeButton.setForeground(Color.WHITE);
        add(removeButton, gbc);

        // Adding order room service button
        gbc.gridx = 1;
        gbc.gridy = 7;
        orderRoomServiceButton = new JButton("Order Room Service");
        orderRoomServiceButton.setFont(new Font("Roboto", Font.PLAIN, 18));
        orderRoomServiceButton.setBackground(new Color(33, 150, 243));
        orderRoomServiceButton.setForeground(Color.WHITE);
        add(orderRoomServiceButton, gbc);

        // Button actions
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String name = nameField.getText();
                    String email = emailField.getText();
                    long phone = Long.parseLong(phoneField.getText());
                    String preference = preferenceField.getText();

                    Guest guest = new Guest();
                    guest.setName(name);
                    guest.setEmail(email);
                    guest.setPhone(phone);
                    guest.setPreference(preference);

                    // Add guest to the list
                    guestList.add(guest);

                    // Display saved guests
                    StringBuilder guestInfo = new StringBuilder();
                    for (Guest g : guestList) {
                        guestInfo.append("Name: ").append(g.getName())
                                 .append(", Email: ").append(g.getEmail())
                                 .append(", Phone: ").append(g.getPhone())
                                 .append(", Preference: ").append(g.getPreference())
                                 .append("\n");
                    }
                    JOptionPane.showMessageDialog(null, "Guest details saved:\n" + guestInfo.toString());
                    
                    // Print guest information to console
                    System.out.println("Guest List:");
                    for (Guest g : guestList) {
                        System.out.println("Name: " + g.getName() +
                                           ", Email: " + g.getEmail() +
                                           ", Phone: " + g.getPhone() +
                                           ", Preference: " + g.getPreference());
                    }

                    // Clear fields
                    nameField.setText("");
                    emailField.setText("");
                    phoneField.setText("");
                    preferenceField.setText("");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid phone number format.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                Guest guestToRemove = null;

                for (Guest guest : guestList) {
                    if (guest.getName().equalsIgnoreCase(name)) {
                        guestToRemove = guest;
                        break;
                    }
                }

                if (guestToRemove != null) {
                    guestList.remove(guestToRemove);
                    JOptionPane.showMessageDialog(null, "Guest removed: " + name);

                    // Print updated guest information to console
                    System.out.println("Updated Guest List:");
                    for (Guest g : guestList) {
                        System.out.println("Name: " + g.getName() +
                                           ", Email: " + g.getEmail() +
                                           ", Phone: " + g.getPhone() +
                                           ", Preference: " + g.getPreference());
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Guest not found: " + name, "Error", JOptionPane.ERROR_MESSAGE);
                }
                
                // Clear fields
                nameField.setText("");
            }
        });

        orderRoomServiceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String serviceRequest = roomServiceField.getText();
                JOptionPane.showMessageDialog(null, "Room service has been ordered: " + serviceRequest);
                roomServiceField.setText(""); // Clear the room service request field
            }
        });
    }
}
