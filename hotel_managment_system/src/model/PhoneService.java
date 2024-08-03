package model;

public class PhoneService {
    private String phoneNumber;
    private int callDuration; // in minutes

    public PhoneService(String phoneNumber, int callDuration) {
        this.phoneNumber = phoneNumber;
        this.callDuration = callDuration;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getCallDuration() {
        return callDuration;
    }

    public void setCallDuration(int callDuration) {
        this.callDuration = callDuration;
    }

    @Override
    public String toString() {
        return "PhoneService{" +
                "phoneNumber='" + phoneNumber + '\'' +
                ", callDuration=" + callDuration +
                '}';
    }
}
