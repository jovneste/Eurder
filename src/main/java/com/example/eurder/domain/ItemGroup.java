package com.example.eurder.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ItemGroup {
    private Item item;
    private int amountToOrder;
    private LocalDate shippingDate;


    public ItemGroup(Item item, int amountToOrder, User customer) {
        this.item = item;
        this.amountToOrder = amountToOrder;
        if(item.getAmountInStock()<amountToOrder){
            this.shippingDate = LocalDate.now().plusDays(7);
        }else {
            this.shippingDate = LocalDate.now().plusDays(1);
        }

    }

    public Item getItem() {
        return item;
    }

    public int getAmountToOrder() {
        return amountToOrder;
    }

    public LocalDate getShippingDate() {
        return shippingDate;
    }


}
