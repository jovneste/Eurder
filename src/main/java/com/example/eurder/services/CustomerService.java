package com.example.eurder.services;

import com.example.eurder.domain.Customer;
import com.example.eurder.domain.dtos.CustomerDto;
import com.example.eurder.domain.mappers.CustomerMapper;
import com.example.eurder.exceptions.customerexceptions.*;
import com.example.eurder.repositories.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CustomerService {
    private final Logger logger = LoggerFactory.getLogger(CustomerService.class);
    CustomerRepository customerRepository;
    CustomerMapper customerMapper;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
        this.customerMapper = new CustomerMapper();
    }

    public CustomerDto addCustomer(CustomerDto customerDto) {

        checkRequiredFields(customerDto);
        Customer newCustomer = customerMapper.customerDtoToCustomer(customerDto);
        customerRepository.save(newCustomer);
        return customerMapper.customerToCustomerDto(newCustomer);

    }

    public void checkRequiredFields(CustomerDto customerDto){

        if(customerDto.getEmailAddress() == null || Objects.equals(customerDto.getEmailAddress(), "")){
            logger.error("An email-address is required to create a customer");
            throw new EmailRequiredException();
        }
        if(customerDto.getFirstName() == null || customerDto.getFirstName().equals("")){
            logger.error("A firstname is required to create a customer");
            throw new FirstNameRequiredException();
        }
        if(customerDto.getLastName() == null || customerDto.getLastName().equals("")){
            logger.error("A lastname is required to create a customer");
            throw new LastNameRequiredException();
        }
        if(customerDto.getAddress() == null || customerDto.getAddress().equals("")){
            logger.error("An Adress is required to create a customer");
            throw new AddressRequiredException();
        }
        if(customerDto.getPhoneNumber() == null || customerDto.getPhoneNumber().equals("")){
            logger.error("A phone number is required to create a customer");
            throw new PhoneNumberRequiredException();
        }


    }
}
