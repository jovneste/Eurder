package com.example.eurder.domain.mappers;

import com.example.eurder.domain.User;
import com.example.eurder.domain.dtos.CustomerDto;
import com.example.eurder.domain.dtos.NewCustomerDto;

public class CustomerMapper {
    public User newcustomerDtoToCustomer(NewCustomerDto newCustomerDto){
        return new User(newCustomerDto.getFirstName(),newCustomerDto.getLastName(), newCustomerDto.getEmailAddress(), newCustomerDto.getAddress(), newCustomerDto.getPhoneNumber(),newCustomerDto.getPassword());

    }
    public CustomerDto customerToCustomerDto(User user){
        return new CustomerDto(user.getFirstName(), user.getLastName(), user.getEmailAdress(), user.getAddress(), user.getPhoneNumber());
    }
}
