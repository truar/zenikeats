package com.zenika.zenikeats.configuration;

import com.zenika.zenikeats.application.OrderApplicationService;
import com.zenika.zenikeats.domain.IdGenerator;
import com.zenika.zenikeats.domain.order.OrderRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

@Configuration
public class ZenikeatsConfiguration {

    @Bean
    public Clock clock() {
        return Clock.systemUTC();
    }

    @Bean
    public OrderApplicationService orderApplicationService(OrderRepository orderRepository,
                                                           Clock clock,
                                                           IdGenerator idGenerator) {
        return new OrderApplicationService(orderRepository, clock, idGenerator);
    }

}
