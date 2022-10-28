package com.example.eurder.domain;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class Order {
    private String orderId;

    private User customer;
    private List<ItemGroup> itemGroupList;
    private BigDecimal totalPrice;

    public Order(List<ItemGroup> itemGroupList,User customer) {
        this.orderId = UUID.randomUUID().toString();
        this.itemGroupList = itemGroupList;
        this.customer = customer;
        this.totalPrice = calculateTotalPrice();


    }
    private BigDecimal calculateTotalPrice(){
        double totalPrice = 0;
        for (ItemGroup itemgroup:this.itemGroupList) {
            totalPrice += itemgroup.getAmountToOrder() * itemgroup.getItem().getPrice().doubleValue();
        }
        return BigDecimal.valueOf(totalPrice);
    }

    public List<ItemGroup> getItemGroupList() {
        return itemGroupList;
    }

    public String getOrderId() {
        return orderId;
    }

    public User getCustomer() {
        return customer;
    }
}
