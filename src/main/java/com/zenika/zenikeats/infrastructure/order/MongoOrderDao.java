package com.zenika.zenikeats.infrastructure.order;

import com.zenika.zenikeats.domain.order.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoOrderDao extends MongoRepository<Order, String> {
}
