package com.example.eurder.domain.dtos;

public class CustomerDto {
    private String firstName;
    private String lastName;
    private String emailAdress;
    private String address;
    private String phoneNumber;

    public CustomerDto(String firstName, String lastName, String emailAdress, String address, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAdress = emailAdress;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmailAdress() {
        return emailAdress;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
