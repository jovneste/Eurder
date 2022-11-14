package com.example.eurder.domain;

import java.time.LocalDate;

public class ItemGroup {

    private String itemName;
    private Item item;
    private int amountToOrder;
    private LocalDate shippingDate;

    public ItemGroup() {
    }

    public ItemGroup(String itemName, int amountToOrder) {
        this.itemName = itemName;
        this.amountToOrder = amountToOrder;
    }

    // for putting hardcoding items in the databsae
    public ItemGroup(String itemName, Item item, int amountToOrder) {
        this.itemName = itemName;
        this.item = item;
        this.amountToOrder = amountToOrder;
    }

    public Item getItem() {
        return item;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItem(Item item) {
        this.item = item;
        this.shippingDate = calculateShippingDate(item);
    }

    public int getAmountToOrder() {
        return amountToOrder;
    }

    public LocalDate getShippingDate() {
        return shippingDate;
    }
    private LocalDate calculateShippingDate(Item item){
        if(item.getAmountInStock()<amountToOrder){
            return LocalDate.now().plusDays(7);
        }else {
            return LocalDate.now().plusDays(1);
        }

    }



}
