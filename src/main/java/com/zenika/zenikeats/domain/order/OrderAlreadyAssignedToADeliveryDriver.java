package com.zenika.zenikeats.domain.order;

public class OrderAlreadyAssignedToADeliveryDriver extends Throwable {
    public OrderAlreadyAssignedToADeliveryDriver(String orderId) {
        super("The order \"" + orderId + "\" has already been assigned to a delivery driver");
    }
}
