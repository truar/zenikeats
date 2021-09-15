package com.zenika.zenikeats.infrastructure.order;

import com.zenika.zenikeats.domain.order.Order;
import com.zenika.zenikeats.domain.order.OrderRepository;
import com.zenika.zenikeats.domain.order.OrderStatus;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MongoOrderRepository implements OrderRepository {

    private MongoOrderDao mongoOrderDao;

    public MongoOrderRepository(MongoOrderDao mongoOrderDao) {
        this.mongoOrderDao = mongoOrderDao;
    }

    @Override
    public void save(Order order) {
        mongoOrderDao.save(order);
    }

    @Override
    public Optional<Order> findById(String id) {
        return mongoOrderDao.findById(id);
    }

    @Override
    public List<Order> findByStatus(OrderStatus status) {
        return null;
    }
}
