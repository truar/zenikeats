package com.zenika.zenikeats.domain.order;

import java.math.BigDecimal;

public class Item {
    private String id;
    private String name;
    private BigDecimal price;
    private int quantity;

    public Item(String id, String name, BigDecimal price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }
}
