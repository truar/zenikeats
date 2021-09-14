package com.zenika.zenikeats.domain.order;

public class OrderNotAcceptedException extends Throwable {
    public OrderNotAcceptedException(String orderId) {
        super("The order \"" + orderId + "\" has not been accepted yet!");
    }
}
