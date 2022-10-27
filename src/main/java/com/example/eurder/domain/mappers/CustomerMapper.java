package com.example.eurder.domain.mappers;

import com.example.eurder.domain.Customer;
import com.example.eurder.domain.dtos.CustomerDto;

public class CustomerMapper {
    public Customer customerDtoToCustomer(CustomerDto customerDto){
        return new Customer(customerDto.getFirstName(),customerDto.getLastName(), customerDto.getEmailAddress(), customerDto.getAddress(), customerDto.getPhoneNumber());

    }
    public CustomerDto customerToCustomerDto(Customer customer){
        return new CustomerDto(customer.getFirstName(), customer.getLastName(), customer.getEmailAdress(), customer.getAddress(), customer.getPhoneNumber());
    }
}
