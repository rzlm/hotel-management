package model;

import java.util.ArrayList;
import java.util.List;

public class PhoneServiceManager {
    private List<PhoneService> phoneServices;

    public PhoneServiceManager() {
        this.phoneServices = new ArrayList<>();
    }

    public void addPhoneService(PhoneService phoneService) {
        phoneServices.add(phoneService);
    }

    public void editPhoneService(String phoneNumber, int newCallDuration) {
        for (PhoneService service : phoneServices) {
            if (service.getPhoneNumber().equals(phoneNumber)) {
                service.setCallDuration(newCallDuration);
                return;
            }
        }
    }

    public void deletePhoneService(String phoneNumber) {
        phoneServices.removeIf(service -> service.getPhoneNumber().equals(phoneNumber));
    }

    public PhoneService searchPhoneService(String phoneNumber) {
        for (PhoneService service : phoneServices) {
            if (service.getPhoneNumber().equals(phoneNumber)) {
                return service;
            }
        }
        return null;
    }

    public List<PhoneService> getAllPhoneServices() {
        return new ArrayList<>(phoneServices);
    }
}
