package com.zenika.zenikeats.application;

import com.zenika.zenikeats.domain.IdGenerator;
import com.zenika.zenikeats.domain.order.Order;
import com.zenika.zenikeats.domain.order.OrderNotFoundException;
import com.zenika.zenikeats.domain.order.OrderRepository;
import com.zenika.zenikeats.domain.order.OrderStatus;
import com.zenika.zenikeats.infrastructure.order.InMemoryOrderRepository;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static java.time.ZoneOffset.UTC;
import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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

        Optional<Order> order = orderRepository.findById(orderIdGenerated);
        assertThat(order.get()).isNotNull();
        assertThat(order.get().getCreationDate()).isEqualTo(creationDate);
    }

    @Test
    void accepts_an_order() throws OrderNotFoundException {
        LocalDateTime creationDate = LocalDate.of(2021, 1, 1).atStartOfDay();
        String clientId = "clientId";
        String orderId = "orderId";
        OrderRepository orderRepository = new InMemoryOrderRepository();
        Order order = new Order(orderId, emptyList(), clientId, creationDate);
        orderRepository.save(order);
        OrderApplicationService orderApplicationService = new OrderApplicationService(orderRepository, clock, null);

        orderApplicationService.acceptOrder(orderId);

        Order actualOrder = orderRepository.findById(orderId).get();
        assertThat(actualOrder.getStatus()).isEqualTo(OrderStatus.ACCEPTED);
    }

    @Test
    void get_an_order() throws OrderNotFoundException {
        LocalDateTime creationDate = LocalDate.of(2021, 1, 1).atStartOfDay();
        String clientId = "clientId";
        String orderId = "orderId";
        OrderRepository orderRepository = new InMemoryOrderRepository();
        Order order = new Order(orderId, emptyList(), clientId, creationDate);
        orderRepository.save(order);
        OrderApplicationService orderApplicationService = new OrderApplicationService(orderRepository, clock, null);

        Order actualOrder = orderApplicationService.getOrder(orderId);

        assertThat(actualOrder.getId()).isEqualTo(orderId);
    }

    @Test
    void get_an_invalid_order() {

        String invalidId = "-1";
        OrderRepository orderRepository = new InMemoryOrderRepository();
        OrderApplicationService orderApplicationService = new OrderApplicationService(orderRepository, clock, null);

        assertThatThrownBy(() -> orderApplicationService.getOrder(invalidId))
                .hasMessage("The order \"-1\" does not exists");
    }
}
