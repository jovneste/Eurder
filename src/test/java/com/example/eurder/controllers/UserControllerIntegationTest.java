package com.example.eurder.controllers;

import com.example.eurder.domain.dtos.CustomerDto;
import com.example.eurder.repositories.UserRepository;
import com.example.eurder.services.CustomerService;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

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

        String requestedBody = "{\"firstName\":\"Tom\",\"lastName\":\"a\",\"emailAddress\":\"g@h.com\",\"address\":\"nana\",\"phoneNumber\":\"1\",\"password\":\"pass\"}";



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
    @Test
    void adminViewsAllCustomersHappyPath(){

        List<CustomerDto> expectedList = new ArrayList<>();
        expectedList.add(new CustomerDto("customer","notadminson"
                ,"customer@eurder.com","street","044"));

        CustomerDto[] result = RestAssured
                .given()
                .auth()
                .preemptive()
                .basic("admin@eurder.com","root")
                .contentType(ContentType.JSON)
                .baseUri("http://localhost")
                .port(port)
                .when()
                .accept(ContentType.JSON)
                .get("/customers/")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .as(CustomerDto[].class);
        Assertions.assertEquals(expectedList.get(0).getEmailAddress(),List.of(result).get(0).getEmailAddress());
    }
    @Test
    void customerTriesToViewsAllCustomers_ThrowsUnauthorisedException (){



       RestAssured
                .given()
                .auth()
                .preemptive()
                .basic("customern@eurder.com","root")
                .contentType(ContentType.JSON)
                .baseUri("http://localhost")
                .port(port)
                .when()
                .accept(ContentType.JSON)
                .get("/customers/")
                .then()
                .assertThat()
                .statusCode(HttpStatus.UNAUTHORIZED.value())
                .extract();


    }
}
