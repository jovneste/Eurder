package com.example.eurder.repositories;

import com.example.eurder.domain.Item;
import com.example.eurder.domain.ItemGroup;
import com.example.eurder.domain.Order;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderRepository {
    private List<Order> orderDatabase;

    public OrderRepository() {
        this.orderDatabase = new ArrayList<>();
        List<ItemGroup> itemGroupList = new ArrayList<>();
        itemGroupList.add(new ItemGroup("phoenix down",new Item("phoenix down","item", BigDecimal.valueOf(5),4),10));
        orderDatabase.add(new Order(itemGroupList,"customer@eurder.com"));

    }
    public void saveOrder(Order order){
        orderDatabase.add(order);
    }

    public List<Order> getOrderDatabase() {
        return orderDatabase;
    }
}
