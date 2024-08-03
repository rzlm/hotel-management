package model;

public class Guest {
    private String name;
    private String preference;
    private String email;
    private long phone;

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPreference() {
        return preference;
    }

    public void setPreference(String preference) {
        this.preference = preference;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    // Logic to order room service
    public void orderRoomService(String serviceRequest) {
        // Logic to handle the room service order
        System.out.println("Room service requested: " + serviceRequest);
    }

    public static void main(String[] args) {
        // Example usage
        Guest guest = new Guest();
        guest.setName("John Doe");
        guest.setEmail("john.doe@example.com");
        guest.setPhone(1234567890L);
        guest.setPreference("Non-smoking room");

        System.out.println("Guest Information:");
        System.out.println("Name: " + guest.getName());
        System.out.println("Email: " + guest.getEmail());
        System.out.println("Phone: " + guest.getPhone());
        System.out.println("Preference: " + guest.getPreference());

        guest.orderRoomService("Breakfast in bed at 8 AM");
    }
}