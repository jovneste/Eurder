package com.example.eurder.controllers;

import com.example.eurder.domain.dtos.CustomerDto;
import com.example.eurder.domain.dtos.NewCustomerDto;
import com.example.eurder.security.Feature;
import com.example.eurder.security.SecurityService;
import com.example.eurder.services.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final Logger log = LoggerFactory.getLogger(getClass());
    CustomerService customerService;
    SecurityService securityService;

    public CustomerController(CustomerService customerService,SecurityService securityService) {
        this.customerService = customerService;
        this.securityService = securityService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public CustomerDto addCustomer(@RequestBody NewCustomerDto newCustomerDto){
        log.info("adding the following customer" +newCustomerDto);
        return customerService.addCustomer(newCustomerDto);
    }
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CustomerDto> viewAllCustomer(@RequestHeader String authorization){
        log.info("retrieving all customers");
        securityService.validateAuthorization(authorization, Feature.VIEW_CUSTOMERS);
        return customerService.getAllCustomers();
    }
}
