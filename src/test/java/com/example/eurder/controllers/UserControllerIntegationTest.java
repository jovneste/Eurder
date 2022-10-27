package com.example.eurder.controllers;

import com.example.eurder.domain.dtos.CustomerDto;
import com.example.eurder.repositories.UserRepository;
import com.example.eurder.services.CustomerService;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerIntegationTest {

    @LocalServerPort
    private int port;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CustomerService customerService;

    @Test
    void givenNewCustomer_CustomerIsAddedToBookRepository() {

        String requestedBody = "{\"firstName\":\"Tom\",\"lastName\":\"a\",\"emailAddress\":\"g@h.com\",\"address\":\"nana\",\"phoneNumber\":\"1\"}";



        CustomerDto result = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .baseUri("http://localhost")
                .port(port)
                .body(requestedBody)
                .when()
                .accept(ContentType.JSON)
                .post("/customers/")
                .then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value())
                .extract()
                .as(CustomerDto.class);

    }
}
