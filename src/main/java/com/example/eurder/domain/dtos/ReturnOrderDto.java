package com.example.eurder.domain.dtos;

import com.example.eurder.domain.ItemGroup;
import com.example.eurder.domain.User;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class ReturnOrderDto {
    private String orderId;

    private String customerID;
    private List<ItemGroup> itemGroupList;
    private BigDecimal totalPrice;

    public ReturnOrderDto(String orderId, String customerID, List<ItemGroup> itemGroupList, BigDecimal totalPrice) {
        this.orderId = orderId;
        this.customerID = customerID;
        this.itemGroupList = itemGroupList;
        this.totalPrice = totalPrice;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getCustomer() {
        return customerID;
    }

    public List<ItemGroup> getItemGroupList() {
        return itemGroupList;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }
}
