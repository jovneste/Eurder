package com.example.eurder.domain.dtos;

import com.example.eurder.domain.ItemGroup;
import com.example.eurder.domain.User;

import java.util.List;

public class OrderDto {


    private User customer;
    private List<ItemGroup> itemGroupList;

    public OrderDto(List<ItemGroup> itemGroupList,User customer) {
        this.itemGroupList = itemGroupList;
        this.customer =customer;
    }

    public List<ItemGroup> getItemGroupList() {
        return itemGroupList;
    }



    public User getCustomer() {
        return customer;
    }
}
