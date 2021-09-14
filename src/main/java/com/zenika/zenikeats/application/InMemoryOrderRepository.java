package com.zenika.zenikeats.application;

import com.zenika.zenikeats.domain.order.Order;
import com.zenika.zenikeats.domain.order.OrderRepository;
import com.zenika.zenikeats.domain.order.OrderStatus;
import org.apache.logging.log4j.CloseableThreadContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryOrderRepository implements OrderRepository {
    private Map<String, Order> orders = new HashMap<>();

    @Override
    public void save(Order order) {
        orders.put(order.getId(), order);
    }

    @Override
    public Order findById(String id) {
        return orders.get(id);
    }

    @Override
    public List<Order> findByStatus(OrderStatus status) {
        return null;
    }
}
