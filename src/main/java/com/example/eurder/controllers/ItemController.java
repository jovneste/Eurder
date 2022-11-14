package com.example.eurder.controllers;

import com.example.eurder.domain.dtos.ItemDto;
import com.example.eurder.security.Feature;
import com.example.eurder.security.SecurityService;
import com.example.eurder.services.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/items")
public class ItemController {
    private final Logger log = LoggerFactory.getLogger(getClass());
    ItemService itemService;
    SecurityService securityService;

    public ItemController(ItemService itemService ,SecurityService securityService) {
        this.itemService = itemService;
        this.securityService = securityService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ItemDto addItem(@RequestHeader String authorization,@RequestBody ItemDto itemDto){
        log.info("adding item");
        securityService.validateAuthorization(authorization, Feature.ADD_ITEM);
        return itemService.addItem(itemDto);
    }
}
