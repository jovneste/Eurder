package com.example.eurder.domain;

public class Customer {
    private String firstName;
    private String lastName;
    private String emailAdress;
    private String address;
    private String phoneNumber;

    public Customer(String firstName, String lastName, String emailAdress, String address, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAdress = emailAdress;
        this.address = address;
        this.phoneNumber = phoneNumber;
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
}
