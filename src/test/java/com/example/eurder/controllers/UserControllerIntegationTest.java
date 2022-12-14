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

import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerIntegationTest {

    @LocalServerPort
    private int port;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CustomerService customerService;

    @Test
    void givenNewCustomer_CustomerIsAddedToUserRepository() {

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
    void givenNewCustomerWithouteamil_ExceptionThrown() {

        String requestedBody = "{\"firstName\":\"r\",\"lastName\":\"a\",\"emailAddress\":\"\",\"address\":\"nana\",\"phoneNumber\":\"1\",\"password\":\"pass\"}";


        RestAssured
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
                .statusCode(HttpStatus.FORBIDDEN.value())
                .body("message", equalTo("emailAddress is required"));

    }

    @Test
    void givenNewCustomerWithoutFirstName_ExceptionThrown() {

        String requestedBody = "{\"firstName\":\"\",\"lastName\":\"a\",\"emailAddress\":\"g@h.com\",\"address\":\"nana\",\"phoneNumber\":\"1\",\"password\":\"pass\"}";


        RestAssured
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
                .statusCode(HttpStatus.FORBIDDEN.value())
                .body("message", equalTo("firstname is required"));

    }

    @Test
    void givenNewCustomerWithoutLastName_ExceptionThrown() {

        String requestedBody = "{\"firstName\":\"d\",\"lastName\":\"\",\"emailAddress\":\"g@h.com\",\"address\":\"nana\",\"phoneNumber\":\"1\",\"password\":\"pass\"}";


        RestAssured
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
                .statusCode(HttpStatus.FORBIDDEN.value())
                .body("message", equalTo("lastname is required"));

    }
    @Test
    void givenNewCustomerWithoutPhoneNumber_ExceptionThrown() {

        String requestedBody = "{\"firstName\":\"d\",\"lastName\":\"f\",\"emailAddress\":\"g@h.com\",\"address\":\"nana\",\"phoneNumber\":\"\",\"password\":\"pass\"}";


        RestAssured
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
                .statusCode(HttpStatus.FORBIDDEN.value())
                .body("message", equalTo("Phone number is required"));

    }



    @Test
    void adminViewsAllCustomersHappyPath() {


        RestAssured
                .given()
                .auth()
                .preemptive()
                .basic("admin@eurder.com", "root")
                .contentType(ContentType.JSON)
                .baseUri("http://localhost")
                .port(port)
                .when()
                .accept(ContentType.JSON)
                .get("/customers/")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value());

    }

    @Test
    void customerTriesToViewsAllCustomers_ThrowsUnauthorisedException() {

        RestAssured
                .given()
                .auth()
                .preemptive()
                .basic("customer@eurder.com", "root")
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

    @Test
    void AdminTriesToViewsSingleCustomer_HappyPath() {


        String userId = userRepository.getUserByID(userRepository.getUserDatabase().get("customer@eurder.com").getUserId()).getUserId();
        RestAssured
                .given()
                .auth()
                .preemptive()
                .basic("admin@eurder.com", "root")
                .contentType(ContentType.JSON)
                .baseUri("http://localhost")
                .port(port)
                .when()
                .accept(ContentType.JSON)
                .get("/customers/" + userId)
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .extract();


    }
}
