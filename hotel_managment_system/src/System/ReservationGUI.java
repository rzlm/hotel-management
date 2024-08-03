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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import model.Reservation;

public class ReservationGUI extends JPanel {
    private JTextField checkinDateField;
    private JTextField checkoutDateField;
    private JTextField roomNumberField;
    private JButton createReservationButton;
    private JButton cancelReservationButton;

    // ArrayList to store reservations
    private ArrayList<Reservation> reservationList;

    public ReservationGUI() {
        reservationList = new ArrayList<>(); // Initialize the reservation list

        setBackground(new Color(240, 240, 240));
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel titleLabel = new JLabel("Create a new Reservation");
        titleLabel.setFont(new Font("Roboto", Font.BOLD, 18));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(titleLabel, gbc);

        // Adding components
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        JLabel checkinDateLabel = new JLabel("Check-in Date:");
        checkinDateLabel.setFont(new Font("Roboto", Font.PLAIN, 18));
        add(checkinDateLabel, gbc);

        gbc.gridx = 1;
        checkinDateField = new JTextField(20);
        add(checkinDateField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel checkoutDateLabel = new JLabel("Check-out Date:");
        checkoutDateLabel.setFont(new Font("Roboto", Font.PLAIN, 18));
        add(checkoutDateLabel, gbc);

        gbc.gridx = 1;
        checkoutDateField = new JTextField(20);
        add(checkoutDateField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        JLabel roomNumberLabel = new JLabel("Room Number:");
        roomNumberLabel.setFont(new Font("Roboto", Font.PLAIN, 18));
        add(roomNumberLabel, gbc);

        gbc.gridx = 1;
        roomNumberField = new JTextField(20);
        add(roomNumberField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        createReservationButton = new JButton("Create Reservation");
        createReservationButton.setFont(new Font("Roboto", Font.PLAIN, 18));
        createReservationButton.setBackground(new Color(33, 150, 243));
        createReservationButton.setForeground(Color.WHITE);
        add(createReservationButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        cancelReservationButton = new JButton("Cancel Reservation");
        cancelReservationButton.setFont(new Font("Roboto", Font.PLAIN, 18));
        cancelReservationButton.setBackground(new Color(33, 150, 243));
        cancelReservationButton.setForeground(Color.WHITE);
        add(cancelReservationButton, gbc);

        // Button actions
        createReservationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Reservation reservation = new Reservation();
                    reservation.setCheckinDate(checkinDateField.getText());
                    reservation.setCheckoutDate(checkoutDateField.getText());
                    reservation.setRoomNumber(Integer.parseInt(roomNumberField.getText()));

                    reservationList.add(reservation); // Add reservation to the list

                    // Display reservations
                    StringBuilder reservationInfo = new StringBuilder();
                    for (Reservation r : reservationList) {
                        reservationInfo.append("Room Number: ").append(r.getRoomNumber())
                                       .append(", Check-in Date: ").append(r.getCheckinDate())
                                       .append(", Check-out Date: ").append(r.getCheckoutDate())
                                       .append("\n");
                    }
                    JOptionPane.showMessageDialog(null, "Reservation Created!\n" + reservationInfo.toString());

                    // Print reservation information to console
                    System.out.println("Reservations List:");
                    for (Reservation r : reservationList) {
                        System.out.println("Room Number: " + r.getRoomNumber() +
                                           ", Check-in Date: " + r.getCheckinDate() +
                                           ", Check-out Date: " + r.getCheckoutDate());
                    }

                    // Clear fields
                    checkinDateField.setText("");
                    checkoutDateField.setText("");
                    roomNumberField.setText("");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid room number format.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        cancelReservationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int roomNumber = Integer.parseInt(roomNumberField.getText());
                    Reservation reservationToRemove = null;

                    // Find the reservation to remove
                    for (Reservation reservation : reservationList) {
                        if (reservation.getRoomNumber() == roomNumber) {
                            reservationToRemove = reservation;
                            break;
                        }
                    }

                    // Remove the reservation if found
                    if (reservationToRemove != null) {
                        reservationList.remove(reservationToRemove);
                        JOptionPane.showMessageDialog(null, "Reservation Canceled!");

                        // Print updated reservation information to console
                        System.out.println("Updated Reservations List:");
                        for (Reservation r : reservationList) {
                            System.out.println("Room Number: " + r.getRoomNumber() +
                                               ", Check-in Date: " + r.getCheckinDate() +
                                               ", Check-out Date: " + r.getCheckoutDate());
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Reservation not found for Room Number: " + roomNumber, "Error", JOptionPane.ERROR_MESSAGE);
                    }

                    // Clear fields
                    roomNumberField.setText("");
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

        JFrame frame = new JFrame("Reservation Management");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.add(new ReservationGUI());
        frame.setVisible(true);
    }
}
