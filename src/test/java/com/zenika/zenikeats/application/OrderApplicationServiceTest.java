package com.zenika.zenikeats.application;

import com.zenika.zenikeats.domain.IdGenerator;
import com.zenika.zenikeats.domain.order.Order;
import com.zenika.zenikeats.domain.order.OrderRepository;
import com.zenika.zenikeats.domain.order.OrderStatus;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Or;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static java.time.ZoneOffset.UTC;
import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;

class OrderApplicationServiceTest {

    private Clock clock = Clock.fixed(LocalDate.of(2021, 1, 1).atStartOfDay().toInstant(UTC), UTC);

    @Test
    void creates_an_order() {
        LocalDateTime creationDate = LocalDate.of(2021, 1, 1).atStartOfDay();
        String clientId = "clientId";
        String orderId = "orderId";
        OrderRepository orderRepository = new InMemoryOrderRepository();
        IdGenerator idGenerator = () -> orderId;
        OrderApplicationService orderApplicationService = new OrderApplicationService(orderRepository, clock, idGenerator);

        String orderIdGenerated = orderApplicationService.createOrder(clientId, emptyList());

        Order order = orderRepository.findById(orderIdGenerated);
        assertThat(order).isNotNull();
        assertThat(order.getCreationDate()).isEqualTo(creationDate);
    }

    @Test
    void accepts_an_order() {
        LocalDateTime creationDate = LocalDate.of(2021, 1, 1).atStartOfDay();
        String clientId = "clientId";
        String orderId = "orderId";
        OrderRepository orderRepository = new InMemoryOrderRepository();
        Order order = new Order(orderId, emptyList(), clientId, creationDate);
        orderRepository.save(order);
        OrderApplicationService orderApplicationService = new OrderApplicationService(orderRepository, clock, null);

        orderApplicationService.acceptOrder(orderId);

        Order actualOrder = orderRepository.findById(orderId);
        assertThat(actualOrder.getStatus()).isEqualTo(OrderStatus.ACCEPTED);
    }
}
