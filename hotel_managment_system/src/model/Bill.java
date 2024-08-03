package model;

public class Bill {
    private double amount = 0;
    private boolean status = false; // false for unpaid, true for paid
    private String[] customerName = new String[0];

    // Getters and setters
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String[] getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String[] customerName) {
        this.customerName = customerName;
    }

    // Method to create a bill
    public void createBill() {
        // Logic to create a bill (simplified)
        System.out.println("Bill created for " + String.join(", ", customerName) + " with amount: " + amount);
    }

    // Method to update a bill
    public void updateBill() {
        // Logic to update a bill (simplified)
        System.out.println("Bill updated for " + String.join(", ", customerName) + " with amount: " + amount + " and status: " + (status ? "Paid" : "Unpaid"));
    }
}