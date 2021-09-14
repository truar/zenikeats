package com.zenika.zenikeats.domain.order;

import java.math.BigDecimal;

public class Item {
    private String id;
    private String name;
    private BigDecimal price;
    private int quantity;

    public Item(String itemId, String itemName, BigDecimal itemPrice, int itemQuantity) {
        id = itemId;
        name = itemName;
        price = itemPrice;
        quantity = itemQuantity;
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
