package com.zenika.zenikeats.application;

import com.zenika.zenikeats.domain.IdGenerator;
import com.zenika.zenikeats.domain.order.Item;
import com.zenika.zenikeats.domain.order.Order;
import com.zenika.zenikeats.domain.order.OrderRepository;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;

public class OrderApplicationService {

    private final OrderRepository orderRepository;
    private final Clock clock;
    private IdGenerator idGenerator;

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

    public void acceptOrder(String orderId) {
        Order order = orderRepository.findById(orderId);
        order.accept(LocalDateTime.now(clock));
        orderRepository.save(order);
    }
}
