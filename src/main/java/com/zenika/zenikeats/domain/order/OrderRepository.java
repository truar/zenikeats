package com.zenika.zenikeats.domain.order;

import java.util.List;

public interface OrderRepository {

    void save(Order order);

    Order findById(String id);

    List<Order> findByStatus(OrderStatus status);

}
