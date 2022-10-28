package com.example.eurder.controllers;


import com.example.eurder.domain.dtos.OrderDto;
import com.example.eurder.repositories.OrderRepository;
import com.example.eurder.repositories.UserRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;



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

        String requestedBody = "{\"customer\":{\"firstName\":\"a\",\"lastName\":\"b\",\"emailAdress\":\"A@B.com\",\"address\":\"str\",\"phoneNumber\":\"555\",\"password\":\"pass\"},\"itemGroupList\":[{\"item\":{\"name\":\"phoenixdown\",\"description\":\"item\",\"price\":5,\"amountInStock\":4},\"amountToOrder\":10}]}";
        OrderDto result = RestAssured
                .given()
                .auth()
                .preemptive()
                .basic("Customer@eurder.com","root")
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
                .as(OrderDto.class);
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

}