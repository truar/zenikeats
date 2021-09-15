package com.zenika.zenikeats.domain.order;

import com.zenika.zenikeats.domain.DomainEvent;

import java.time.LocalDateTime;

public class OrderAccepted extends DomainEvent {
    private final String id;
    private final LocalDateTime acceptationDate;

    public OrderAccepted(String id, LocalDateTime acceptationDate) {
        this.id = id;
        this.acceptationDate = acceptationDate;
    }
}
