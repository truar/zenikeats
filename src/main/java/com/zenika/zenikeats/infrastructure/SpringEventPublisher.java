package com.zenika.zenikeats.infrastructure;

import com.zenika.zenikeats.domain.DomainEvent;
import com.zenika.zenikeats.domain.DomainEventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class SpringEventPublisher implements DomainEventPublisher {

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Override
    public void publish(DomainEvent event) {
        eventPublisher.publishEvent(event);
    }
}
