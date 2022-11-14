package com.example.eurder.domain;

import com.example.eurder.security.Feature;
import com.example.eurder.security.Role;

import java.util.UUID;

public class User {
    private final String userId;
    private final String firstName;
    private final String lastName;
    private final String emailAddress;
    private final String address;
    private final String phoneNumber;
    private final String password;
    private Role role;


    public User(String firstName, String lastName, String emailAddress, String address, String phoneNumber, String password) {
        this.userId = UUID.randomUUID().toString();
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

    public String getUserId() {
        return userId;
    }
}
