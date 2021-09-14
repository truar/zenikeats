package com.zenika.zenikeats.domain.order;

import java.time.LocalDateTime;

public class StatusHistory {

    private final OrderStatus orderStatus;
    private final LocalDateTime dateTime;

    public StatusHistory(OrderStatus orderStatus, LocalDateTime dateTime) {
        this.orderStatus = orderStatus;
        this.dateTime = dateTime;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }
}
