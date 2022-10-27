package com.example.eurder.controllers;

import com.example.eurder.domain.dtos.CustomerDto;
import com.example.eurder.domain.dtos.NewCustomerDto;
import com.example.eurder.exceptions.customerexceptions.*;
import com.example.eurder.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerTest {

    @LocalServerPort
    private int port;
    @Autowired
    CustomerController customerController;
    @Autowired
    UserRepository userRepository;

    @Test
    void addNewCustomeIsSucessful(){
        //given
        NewCustomerDto customerDto = new NewCustomerDto("Peter","Parker"
                ,"definitelynotspiderman@hotmailcom","someStreet","456","pass");
        //when
        customerController.addCustomer(customerDto);
        //then
        Assertions.assertTrue(userRepository.getUserDatabase().containsKey("definitelynotspiderman@hotmailcom"));
    }
    @Test
    void addingCustomerWithoutEmail_ThrowsEamilRequiredException(){
        NewCustomerDto customerDto = new NewCustomerDto("Peter","Parker"
                ,"","someStreet","456","pass");
        //when

        //then
        Assertions.assertThrows(EmailRequiredException.class,()-> customerController.addCustomer(customerDto));
    }
    @Test
    void addingCustomerWithoutFirstName_ThrowsRequiredException(){
        NewCustomerDto customerDto = new NewCustomerDto("","Parker"
                ,"definitelynotspiderman@hotmailcom","someStreet","456","pass");
        //when

        //then
        Assertions.assertThrows(FirstNameRequiredException.class,()-> customerController.addCustomer(customerDto));
    }
    @Test
    void addingCustomerWithoutLastName_ThrowsRequiredException(){
        NewCustomerDto customerDto = new NewCustomerDto("Peter",""
                ,"definitelynotspiderman@hotmailcom","someStreet","456","pass");
        //when

        //then
        Assertions.assertThrows(LastNameRequiredException.class,()-> customerController.addCustomer(customerDto));
    }
    @Test
    void addingCustomerWithoutAdress_ThrowsRequiredException(){
        NewCustomerDto customerDto = new NewCustomerDto("Peter","Parker"
                ,"definitelynotspiderman@hotmailcom","","456","pass");
        //when

        //then
        Assertions.assertThrows(AddressRequiredException.class,()-> customerController.addCustomer(customerDto));
    }
    @Test
    void addingCustomerWithoutPhoneNumber_ThrowsRequiredException(){
        NewCustomerDto customerDto = new NewCustomerDto("Peter","Parker"
                ,"definitelynotspiderman@hotmailcom","someStreet","","pass");
        //when

        //then
        Assertions.assertThrows(PhoneNumberRequiredException.class,()-> customerController.addCustomer(customerDto));
    }
}