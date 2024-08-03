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
import javax.swing.UIManager;

import model.Room;
import model.Reservation;

public class RoomGUI extends JPanel {
    private JTextField roomNumberField;
    private JTextField roomStyleField;
    private JCheckBox statusCheckBox;
    private JButton checkAvailabilityButton;
    private JButton requestMaintenanceButton;
    private JButton updateStatusButton;

    // Map to store room statuses
    private Map<Integer, Boolean> roomStatusMap; // Room number to occupied status map
    private Map<Integer, Boolean> roomReservationMap; // Room number to reserved status map

    public RoomGUI() {
        roomStatusMap = new HashMap<>(); // Initialize the room status map
        roomReservationMap = new HashMap<>(); // Initialize the room reservation map

        setBackground(new Color(240, 240, 240));
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Adding components
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        JLabel titleLabel = new JLabel("Manage Room");
        titleLabel.setFont(new Font("Roboto", Font.BOLD, 18));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.anchor = GridBagConstraints.CENTER;
        add(titleLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        JLabel roomNumberLabel = new JLabel("Room Number:");
        roomNumberLabel.setFont(new Font("Roboto", Font.PLAIN, 18));
        add(roomNumberLabel, gbc);

        gbc.gridx = 1;
        roomNumberField = new JTextField(20);
        add(roomNumberField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel roomStyleLabel = new JLabel("Room Style:");
        roomStyleLabel.setFont(new Font("Roboto", Font.PLAIN, 18));
        add(roomStyleLabel, gbc);

        gbc.gridx = 1;
        roomStyleField = new JTextField(20);
        roomStyleField.setFont(new Font("Roboto", Font.PLAIN, 18));
        add(roomStyleField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        JLabel statusLabel = new JLabel("Status:");
        statusLabel.setFont(new Font("Roboto", Font.PLAIN, 18));
        add(statusLabel, gbc);

        gbc.gridx = 1;
        statusCheckBox = new JCheckBox("Occupied");
        statusCheckBox.setFont(new Font("Roboto", Font.PLAIN, 18));
        add(statusCheckBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        checkAvailabilityButton = new JButton("Check Availability");
        checkAvailabilityButton.setFont(new Font("Roboto", Font.PLAIN, 18));
        checkAvailabilityButton.setBackground(new Color(33, 150, 243));
        checkAvailabilityButton.setForeground(Color.WHITE);
        add(checkAvailabilityButton, gbc);

        gbc.gridy = 5;
        requestMaintenanceButton = new JButton("Request Maintenance");
        requestMaintenanceButton.setFont(new Font("Roboto", Font.PLAIN, 18));
        requestMaintenanceButton.setBackground(new Color(33, 150, 243));
        requestMaintenanceButton.setForeground(Color.WHITE);
        add(requestMaintenanceButton, gbc);

        gbc.gridy = 6;
        updateStatusButton = new JButton("Update Status");
        updateStatusButton.setFont(new Font("Roboto", Font.PLAIN, 18));
        updateStatusButton.setBackground(new Color(33, 150, 243)); // Blue color
        updateStatusButton.setForeground(Color.WHITE);
        add(updateStatusButton, gbc);

        // Button actions
        checkAvailabilityButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int roomNumber = Integer.parseInt(roomNumberField.getText());
                    boolean isReserved = roomReservationMap.getOrDefault(roomNumber, false);
                    boolean isOccupied = roomStatusMap.getOrDefault(roomNumber, false);

                    // Show the status
                    JOptionPane.showMessageDialog(null,
                            "Room " + roomNumber + " is " + (isReserved ? "reserved" : (isOccupied ? "occupied" : "available")));

                    // Print reservation status to the console
                    System.out.println("Room " + roomNumber + " is " + (isReserved ? "reserved" : (isOccupied ? "occupied" : "available")));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid room number format.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        requestMaintenanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int roomNumber = Integer.parseInt(roomNumberField.getText());
                    boolean isReserved = roomReservationMap.getOrDefault(roomNumber, false);

                    if (isReserved) {
                        JOptionPane.showMessageDialog(null, "Maintenance cannot be requested for a reserved room.");
                        System.out.println("Maintenance request failed for room " + roomNumber + " as it is reserved.");
                    } else {
                        // Room is available, proceed with maintenance request
                        JOptionPane.showMessageDialog(null, "Maintenance requested for room " + roomNumber);
                        System.out.println("Maintenance requested for room " + roomNumber);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid room number format.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        updateStatusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int roomNumber = Integer.parseInt(roomNumberField.getText());
                    String roomStyle = roomStyleField.getText();
                    boolean status = statusCheckBox.isSelected();

                    Room room = new Room();
                    room.setRoomNumber(roomNumber);
                    room.setRoomStyle(new String[]{roomStyle});
                    room.setStatus(status);
                    room.updateStatus();

                    // Update status in the map
                    roomStatusMap.put(roomNumber, status);

                    JOptionPane.showMessageDialog(null,
                            "Room " + roomNumber + " status updated to " + (status ? "occupied" : "vacant"));

                    // Print status update to the console
                    System.out.println("Room " + roomNumber + " status updated to " + (status ? "occupied" : "vacant"));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid room number format.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        JFrame frame = new JFrame("Room Management");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.add(new RoomGUI());
        frame.setVisible(true);
    }
}
