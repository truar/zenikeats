package com.zenika.zenikeats.domain.order;

public class OrderNotFoundException extends Throwable {
    public OrderNotFoundException(String orderId) {
        super("The order \"" + orderId + "\" does not exists");
    }
}
