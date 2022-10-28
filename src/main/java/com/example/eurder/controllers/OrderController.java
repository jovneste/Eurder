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
    public ReturnOrderDto addCustomer(@RequestHeader String authorization, @RequestBody NewOrderDto newOrderDto){
        log.info("adding the following order " + newOrderDto);
        securityService.validateAuthorization(authorization, Feature.ORDER);
        return orderService.addOrder(newOrderDto);
    }

}
