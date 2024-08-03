package model;

import java.util.ArrayList;
import java.util.List;

public class Reservation {
    private String checkoutDate;
    private String checkinDate;
    private int roomNumber = 0;

    // List to store reservations (simulating a database)
    private static List<Reservation> reservations = new ArrayList<>();

    // Getters and setters
    public String getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(String checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public String getCheckinDate() {
        return checkinDate;
    }

    public void setCheckinDate(String checkinDate) {
        this.checkinDate = checkinDate;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    // Method to create a reservation
    public void createReservation() {
        // Adding the reservation to the list
        reservations.add(this);
        System.out.println("Reservation created: Room " + roomNumber + " from " + checkinDate + " to " + checkoutDate);
    }

    // Method to cancel a reservation
    public void cancelReservation() {
        // Removing the reservation from the list
        reservations.removeIf(reservation -> reservation.getRoomNumber() == this.roomNumber);
        System.out.println("Reservation canceled: Room " + roomNumber);
    }

    // Method to check in
    public void checkIn() {
        // Logic to check in (simplified)
        System.out.println("Checked in: Room " + roomNumber + " on " + checkinDate);
    }

    // Method to check out
    public void checkOut() {
        // Logic to check out (simplified)
        System.out.println("Checked out: Room " + roomNumber + " on " + checkoutDate);
    }

    public static void main(String[] args) {
        // Example usage
        Reservation reservation = new Reservation();
        reservation.setCheckinDate("2024-07-26");
        reservation.setCheckoutDate("2024-07-30");
        reservation.setRoomNumber(101);

        reservation.createReservation();
        reservation.checkIn();
        reservation.checkOut();
        reservation.cancelReservation();
    }
}