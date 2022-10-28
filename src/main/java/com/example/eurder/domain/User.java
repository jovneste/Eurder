package com.example.eurder.domain;

import com.example.eurder.security.Feature;
import com.example.eurder.security.Role;

public class User {
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String address;
    private String phoneNumber;
    private String password;
    private Role role;


    public User(String firstName, String lastName, String emailAddress, String address, String phoneNumber, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.role = Role.CUSTOMER;
    }

    public String getEmailAddress() {
        return emailAddress;
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

    public Role getRole() {
        return role;
    }

    public boolean doesPasswordMatch(String password) {
        return this.password.equals(password);
    }
    public boolean canHaveAccessTo(Feature feature) {
        return role.containsFeature(feature);
    }
}
