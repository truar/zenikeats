package com.zenika.zenikeats.application;

import com.zenika.zenikeats.domain.DomainEventPublisher;
import com.zenika.zenikeats.domain.IdGenerator;
import com.zenika.zenikeats.domain.order.*;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;

public class OrderApplicationService {

    private final OrderRepository orderRepository;
    private final Clock clock;
    private final IdGenerator idGenerator;
    private DomainEventPublisher eventPublisher;

    public OrderApplicationService(OrderRepository orderRepository, Clock clock, IdGenerator idGenerator) {
        this.orderRepository = orderRepository;
        this.clock = clock;
        this.idGenerator = idGenerator;
    }

    public String createOrder(String clientId, List<Item> items) {
        LocalDateTime creationDate = LocalDateTime.now(clock);
        String orderId = idGenerator.getId();
        Order order = new Order(orderId, items, clientId, creationDate);
        orderRepository.save(order);
        return orderId;
    }

    public void acceptOrder(String orderId) throws OrderNotFoundException {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException(orderId));
        OrderAccepted event = order.accept(LocalDateTime.now(clock));
        orderRepository.save(order);
        eventPublisher.publish(event);
    }

    public Order getOrder(String orderId) throws OrderNotFoundException {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException(orderId));
    }
}
