package model;

import java.util.ArrayList;
import java.util.List;

public class Administrator {
    private String name;
    private String email;
    private long phone;
    private List<User> users;

    public Administrator() {
        users = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public void addUser(User user) {
        users.add(user);
    }

    public boolean isUserInSystem(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
}
