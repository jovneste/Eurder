package com.example.eurder.domain.dtos;

import com.example.eurder.domain.ItemGroup;


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

    public ItemGroup getItemGroupFromList(int index){

        return this.itemGroupList.get(index);
    }



    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }
}
