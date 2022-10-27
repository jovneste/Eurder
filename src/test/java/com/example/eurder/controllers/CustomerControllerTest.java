package com.example.eurder.controllers;

import com.example.eurder.domain.dtos.CustomerDto;
import com.example.eurder.exceptions.EmailRequiredException;
import com.example.eurder.repositories.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.*;

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
    void addingCustomerWitjoutEmail_ThrowsEamilRequiredException(){
        CustomerDto customerDto = new CustomerDto("Peter","Parker"
                ,"","someStreet","456");
        //when

        //then
        Assertions.assertThrows(EmailRequiredException.class,()-> customerController.addCustomer(customerDto));
    }
}