package com.example.eurder.controllers;

import com.example.eurder.domain.dtos.CustomerDto;
import com.example.eurder.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public CustomerDto addCustomer(@RequestBody CustomerDto customerDto){
        return customerService.addCustomer(customerDto);
    }
}
