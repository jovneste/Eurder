package com.example.eurder.services;

import com.example.eurder.domain.Customer;
import com.example.eurder.domain.dtos.CustomerDto;
import com.example.eurder.domain.mappers.CustomerMapper;
import com.example.eurder.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    CustomerRepository customerRepository;
    CustomerMapper customerMapper;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
        this.customerMapper = new CustomerMapper();
    }

    public CustomerDto addCustomer(CustomerDto customerDto) {

        Customer newCustomer = customerMapper.customerDtoToCustomer(customerDto);
        customerRepository.save(newCustomer);
        return customerMapper.customerToCustomerDto(newCustomer);

    }
}
