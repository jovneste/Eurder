package com.example.eurder.domain.dtos;

import com.example.eurder.security.Role;

public class NewCustomerDto {
    private String firstName;
    private String lastName;
    private String emailAdress;
    private String address;
    private String phoneNumber;
    private String password;
    private Role role;


    public NewCustomerDto(String firstName, String lastName, String emailAdress, String address, String phoneNumber,String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAdress = emailAdress;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public String getEmailAddress() {
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
}
