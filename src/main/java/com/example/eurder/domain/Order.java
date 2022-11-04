package com.example.eurder.domain;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class Order {
    private String orderId;

    private String customer;
    private List<ItemGroup> itemGroupList;
    private BigDecimal totalPrice;

    public Order(List<ItemGroup> itemGroupList,String customerId) {
        this.orderId = UUID.randomUUID().toString();
        this.itemGroupList = itemGroupList;
        this.customer = customerId;
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

    public String getCustomer() {
        return customer;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }
}
