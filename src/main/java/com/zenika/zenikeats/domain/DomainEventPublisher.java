package com.zenika.zenikeats.domain;

public interface DomainEventPublisher {


    void publish(DomainEvent event);
}
