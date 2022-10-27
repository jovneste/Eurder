package com.example.eurder.controllers;

import com.example.eurder.domain.dtos.CustomerDto;
import com.example.eurder.exceptions.customerexceptions.*;
import com.example.eurder.repositories.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CustomerControllerTest {

    @LocalServerPort
    private int port;
    @Autowired
    CustomerController customerController;
    @Autowired
    CustomerRepository customerRepository;

    @Test
    void addNewCustomeIsSucessful(){
        //given
        CustomerDto customerDto = new CustomerDto("Peter","Parker"
                ,"definitelynotspiderman@hotmailcom","someStreet","456");
        //when
        customerController.addCustomer(customerDto);
        //then
        Assertions.assertTrue(customerRepository.getCustomerDatabase().containsKey("definitelynotspiderman@hotmailcom"));
    }
    @Test
    void addingCustomerWithoutEmail_ThrowsEamilRequiredException(){
        CustomerDto customerDto = new CustomerDto("Peter","Parker"
                ,"","someStreet","456");
        //when

        //then
        Assertions.assertThrows(EmailRequiredException.class,()-> customerController.addCustomer(customerDto));
    }
    @Test
    void addingCustomerWithoutFirstName_ThrowsRequiredException(){
        CustomerDto customerDto = new CustomerDto("","Parker"
                ,"someemail@hotmail.com","someStreet","456");
        //when

        //then
        Assertions.assertThrows(FirstNameRequiredException.class,()-> customerController.addCustomer(customerDto));
    }
    @Test
    void addingCustomerWithoutLastName_ThrowsRequiredException(){
        CustomerDto customerDto = new CustomerDto("f",""
                ,"someemail@hotmail.com","someStreet","456");
        //when

        //then
        Assertions.assertThrows(LastNameRequiredException.class,()-> customerController.addCustomer(customerDto));
    }
    @Test
    void addingCustomerWithoutAdress_ThrowsRequiredException(){
        CustomerDto customerDto = new CustomerDto("f","f"
                ,"someemail@hotmail.com","","456");
        //when

        //then
        Assertions.assertThrows(AddressRequiredException.class,()-> customerController.addCustomer(customerDto));
    }
    @Test
    void addingCustomerWithoutPhoneNumber_ThrowsRequiredException(){
        CustomerDto customerDto = new CustomerDto("f","f"
                ,"someemail@hotmail.com","street123","");
        //when

        //then
        Assertions.assertThrows(PhoneNumberRequiredException.class,()-> customerController.addCustomer(customerDto));
    }
}