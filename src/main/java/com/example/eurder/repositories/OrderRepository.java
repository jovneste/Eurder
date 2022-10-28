package com.example.eurder.repositories;

import com.example.eurder.domain.Order;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderRepository {
    private List<Order> orderDatabase;

    public OrderRepository() {
        this.orderDatabase = new ArrayList<>();
    }
    public void saveOrder(Order order){
        orderDatabase.add(order);
    }
}
