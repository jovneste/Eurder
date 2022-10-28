package com.example.eurder.services;

import com.example.eurder.domain.User;
import com.example.eurder.domain.dtos.CustomerDto;
import com.example.eurder.domain.dtos.NewCustomerDto;
import com.example.eurder.domain.mappers.CustomerMapper;
import com.example.eurder.exceptions.customerexceptions.*;
import com.example.eurder.repositories.UserRepository;
import com.example.eurder.security.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    private final Logger logger = LoggerFactory.getLogger(CustomerService.class);
    UserRepository userRepository;
    CustomerMapper customerMapper;

    public CustomerService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.customerMapper = new CustomerMapper();
    }

    public CustomerDto addCustomer(NewCustomerDto newCustomerDto) {

        checkRequiredFields(newCustomerDto);
        User newUser = customerMapper.newcustomerDtoToCustomer(newCustomerDto);
        userRepository.save(newUser);
        return customerMapper.customerToCustomerDto(newUser);

    }

    public void checkRequiredFields(NewCustomerDto customerDto){

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

    public List<CustomerDto> getAllCustomers() {
       List<User> userList= userRepository.getUserDatabase().values().stream().filter(user -> user.getRole().equals(Role.CUSTOMER)).collect(Collectors.toList());
       return userList.stream().map(user -> customerMapper.customerToCustomerDto(user)).toList();
    }

    public CustomerDto getSingleCustomer(String id) {
        return customerMapper.customerToCustomerDto(userRepository.getUserByID(id));
    }
}
