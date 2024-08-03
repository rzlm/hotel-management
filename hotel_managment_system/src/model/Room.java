package model;

public class Room {
    private int roomNumber;
    private boolean status;
    private String[] roomStyle;

    // Getters and setters
    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String[] getRoomStyle() {
        return roomStyle;
    }

    public void setRoomStyle(String[] roomStyle) {
        this.roomStyle = roomStyle;
    }

    // Method to check room availability
    public boolean checkAvailability() {
        // Logic to check room availability (simplified)
        return !status; // Available if not occupied
    }

    // Method to request room maintenance
    public void requestMaintenance() {
        // Logic to request room maintenance (simplified)
        System.out.println("Maintenance requested for room: " + roomNumber);
    }

    // Method to update room status
    public void updateStatus() {
        // Logic to update room status (simplified)
        System.out.println("Room " + roomNumber + " status updated to " + (status ? "occupied" : "vacant"));
    }
}