package com.example.eurder.domain.dtos;

import com.example.eurder.domain.ItemGroup;
import com.example.eurder.domain.User;

import java.util.List;

public class NewOrderDto {


    private String customerID;
    private List<ItemGroup> itemGroupList;

    public NewOrderDto(List<ItemGroup> itemGroupList, String customerID) {
        this.itemGroupList = itemGroupList;
        this.customerID =customerID;
    }

    public List<ItemGroup> getItemGroupList() {
        return itemGroupList;
    }



    public String getCustomer() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }
}
