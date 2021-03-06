package com.zenika.zenikeats.infrastructure.order;

import com.zenika.zenikeats.domain.order.Order;
import com.zenika.zenikeats.domain.order.OrderRepository;
import com.zenika.zenikeats.domain.order.OrderStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

//@Repository
public class InMemoryOrderRepository implements OrderRepository {
    private Map<String, Order> orders = new HashMap<>();

    @Override
    public void save(Order order) {
        orders.put(order.getId(), order);
    }

    @Override
    public Optional<Order> findById(String id) {
        return Optional.ofNullable(orders.get(id));
    }

    @Override
    public List<Order> findByStatus(OrderStatus status) {
        return null;
    }
}
