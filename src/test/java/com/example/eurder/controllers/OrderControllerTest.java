package com.example.eurder.controllers;


import com.example.eurder.domain.dtos.NewOrderDto;
import com.example.eurder.domain.dtos.ReturnOrderDto;
import com.example.eurder.exceptions.securityexceptions.WrongPasswordException;
import com.example.eurder.repositories.OrderRepository;
import com.example.eurder.repositories.UserRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import java.util.Base64;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OrderControllerTest {
    @LocalServerPort
    private int port;
    @Autowired
    OrderController orderController;
    @Autowired
    UserRepository userRepository;
    @Autowired
    OrderRepository orderRepository;

    @Test
    void customerMakesAnOrder_HappyPath(){

        String requestedBody = "{\"itemGroupList\":[{\"itemName\":\"phoenix down\",\"amountToOrder\":10}]}";
        RestAssured
                .given()
                .auth()
                .preemptive()
                .basic("customer@eurder.com","root")
                .contentType(ContentType.JSON)
                .baseUri("http://localhost")
                .port(port)
                .body(requestedBody)
                .when()
                .accept(ContentType.JSON)
                .post("/orders/")
                .then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value())
                .extract()
                .as(ReturnOrderDto.class);
    }
    @Test
    void unregisterdUserMakesAnOrder_HappyPath(){

        String requestedBody = "{\"customer\":{\"firstName\":\"a\",\"lastName\":\"b\",\"emailAdress\":\"A@B.com\",\"address\":\"str\",\"phoneNumber\":\"555\",\"password\":\"pass\"},\"itemGroupList\":[{\"item\":{\"name\":\"phoenixdown\",\"description\":\"item\",\"price\":5,\"amountInStock\":4},\"amountToOrder\":10}]}";
         RestAssured
                .given()
                .auth()
                .preemptive()
                .basic("hasnotregistered@eurder.com","root")
                .contentType(ContentType.JSON)
                .baseUri("http://localhost")
                .port(port)
                .body(requestedBody)
                .when()
                .accept(ContentType.JSON)
                .post("/orders/")
                .then()
                .assertThat()
                .statusCode(HttpStatus.UNAUTHORIZED.value())
                .extract();

    }
    @Test
    void WrongPasswordMakesAnOrder_ThrowsWrongPasswordException(){

        String requestedBody = "{\"customer\":{\"firstName\":\"a\",\"lastName\":\"b\",\"emailAdress\":\"A@B.com\",\"address\":\"str\",\"phoneNumber\":\"555\",\"password\":\"pass\"},\"itemGroupList\":[{\"item\":{\"name\":\"phoenixdown\",\"description\":\"item\",\"price\":5,\"amountInStock\":4},\"amountToOrder\":10}]}";
        RestAssured
                .given()
                .auth()
                .preemptive()
                .basic("customer@eurder.com","wrongpassword")
                .contentType(ContentType.JSON)
                .baseUri("http://localhost")
                .port(port)
                .body(requestedBody)
                .when()
                .accept(ContentType.JSON)
                .post("/orders/")
                .then()
                .assertThat()
                .statusCode(HttpStatus.UNAUTHORIZED.value())
                .extract();



    }
    @Test
    void customerTriesToOrderANegativeAmount(){

        String requestedBody = "{\"itemGroupList\":[{\"itemName\":\"phoenix down\",\"amountToOrder\":-1}]}";
        RestAssured
                .given()
                .auth()
                .preemptive()
                .basic("customer@eurder.com","root")
                .contentType(ContentType.JSON)
                .baseUri("http://localhost")
                .port(port)
                .body(requestedBody)
                .when()
                .accept(ContentType.JSON)
                .post("/orders/")
                .then()
                .assertThat()
                .statusCode(HttpStatus.FORBIDDEN.value());

    }
    @Test
    void customerTriesToOrderAZeroAmount(){

        String requestedBody = "{\"itemGroupList\":[{\"itemName\":\"phoenix down\",\"amountToOrder\":0}]}";
        RestAssured
                .given()
                .auth()
                .preemptive()
                .basic("customer@eurder.com","root")
                .contentType(ContentType.JSON)
                .baseUri("http://localhost")
                .port(port)
                .body(requestedBody)
                .when()
                .accept(ContentType.JSON)
                .post("/orders/")
                .then()
                .assertThat()
                .statusCode(HttpStatus.FORBIDDEN.value());


    }
    @Test
    void adminViewsAllOrders(){

        RestAssured
                .given()
                .auth()
                .preemptive()
                .basic("admin@eurder.com","root")
                .contentType(ContentType.JSON)
                .baseUri("http://localhost")
                .port(port)
                .when()
                .accept(ContentType.JSON)
                .get("/orders/")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value());


    }

}