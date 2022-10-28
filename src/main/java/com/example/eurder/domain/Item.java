package com.example.eurder.domain;

import java.math.BigDecimal;

public class Item {
    private String name;
    private String description;
    private BigDecimal price;
    private int amountInStock;

    public Item(String name, String description, BigDecimal price, int amountInStock) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.amountInStock = amountInStock;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getAmountInStock() {
        return amountInStock;
    }
}
