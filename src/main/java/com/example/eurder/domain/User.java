package com.example.eurder.domain;

import com.example.eurder.security.Feature;
import com.example.eurder.security.Role;

public class User {
    private String firstName;
    private String lastName;
    private String emailAdress;
    private String address;
    private String phoneNumber;
    private String password;
    private Role role;


    public User(String firstName, String lastName, String emailAdress, String address, String phoneNumber,String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAdress = emailAdress;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.role = Role.CUSTOMER;
    }

    public String getEmailAdress() {
        return emailAdress;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean doesPasswordMatch(String password) {
        return this.password.equals(password);
    }
    public boolean canHaveAccessTo(Feature feature) {
        return role.containsFeature(feature);
    }
}
