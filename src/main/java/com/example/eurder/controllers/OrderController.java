package com.example.eurder.controllers;


import com.example.eurder.domain.dtos.NewOrderDto;
import com.example.eurder.domain.dtos.ReturnOrderDto;
import com.example.eurder.security.Feature;
import com.example.eurder.security.SecurityService;
import com.example.eurder.services.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final Logger log = LoggerFactory.getLogger(getClass());
    OrderService orderService;
    SecurityService securityService;

    public OrderController(OrderService orderService, SecurityService securityService) {
        this.orderService = orderService;
        this.securityService = securityService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ReturnOrderDto addOrder(@RequestHeader String authorization, @RequestBody NewOrderDto newOrderDto){
        log.info("adding the following order " + newOrderDto);
        securityService.validateAuthorization(authorization, Feature.ORDER);
        String username = securityService.getUsernamePassword(authorization).getUsername();
        newOrderDto.setCustomerID(username);



        return orderService.addOrder(newOrderDto);
    }
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ReturnOrderDto> viewAllOrders(@RequestHeader String authorization){
        log.info("retrieving all orders");
        securityService.validateAuthorization(authorization, Feature.VIEW_CUSTOMERS);
        return orderService.getAllOrders();
    }


}
