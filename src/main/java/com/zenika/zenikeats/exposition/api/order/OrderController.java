package com.zenika.zenikeats.exposition.api.order;

import com.zenika.zenikeats.application.OrderApplicationService;
import com.zenika.zenikeats.domain.order.Item;
import com.zenika.zenikeats.domain.order.Order;
import com.zenika.zenikeats.domain.order.OrderNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderApplicationService orderService;

    public OrderController(OrderApplicationService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<Void> postOrder(@RequestBody CreateOrderDTO orderToCreate) {
        List<Item> itemsToCreate = toItems(orderToCreate.getItems());
        String orderId = orderService.createOrder(orderToCreate.getClientId(), itemsToCreate);
        URI location = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{orderId}")
                .buildAndExpand(orderId)
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{orderId}")
    public OrderDTO getOrder(@PathVariable String orderId) {
        try {
            return toOrderDTO(orderService.getOrder(orderId));
        } catch (OrderNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    private OrderDTO toOrderDTO(Order order) {
        return new OrderDTO(order.getId(), order.getStatus(), order.getClientId());
    }

    private List<Item> toItems(List<CreateItemDTO> items) {
        return items.stream().map(this::toItem).collect(Collectors.toList());
    }

    private Item toItem(CreateItemDTO createItemDTO) {
        return new Item(createItemDTO.getId(), createItemDTO.getName(), BigDecimal.valueOf(createItemDTO.getPrice()), createItemDTO.getQuantity());
    }
}
