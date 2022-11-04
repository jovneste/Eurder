package com.example.eurder.controllers;

import com.example.eurder.domain.dtos.CustomerDto;
import com.example.eurder.domain.dtos.ItemDto;
import com.example.eurder.domain.mappers.ItemMapper;
import com.example.eurder.repositories.ItemRepository;
import com.example.eurder.services.ItemService;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ItemControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    ItemRepository itemRepository;
    @Autowired
    ItemController itemController;
    @Autowired
    ItemService itemService;

    @Test
    void addItem_happypath(){

        String requestedBody = "{\"name\":\"itemName\",\"description\":\"a\",\"price\":\"4.12\",\"amount\":\"5\"}";

        ItemDto result = RestAssured
                .given()
                .auth()
                .preemptive()
                .basic("admin@eurder.com","root")
                .contentType(ContentType.JSON)
                .baseUri("http://localhost")
                .port(port)
                .body(requestedBody)
                .when()
                .accept(ContentType.JSON)
                .post("/items/")
                .then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value())
                .extract()
                .as(ItemDto.class);
    }






}