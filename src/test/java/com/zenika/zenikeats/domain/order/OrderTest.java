package com.zenika.zenikeats.domain.order;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class OrderTest {

    @Test
    void an_order_has_an_id() {
        String id = "orderId";
        Order order = new Order(id, emptyList(), id, LocalDateTime.now());
        assertThat(order.getId()).isEqualTo(id);
    }

    @Test
    void an_order_has_a_list_of_items() {
        String itemId = "itemId";
        String itemName = "itemName";
        BigDecimal itemPrice = BigDecimal.ONE;
        int itemQuantity = 1;
        List<Item> orderedItems = List.of(new Item(itemId, itemName, itemPrice, itemQuantity));
        Order order = new Order("id", orderedItems, "", LocalDateTime.now());
        assertThat(order.getItems()).hasSize(1);
        assertThat(order.getItems().get(0).getId()).isEqualTo(itemId);
        assertThat(order.getItems().get(0).getName()).isEqualTo(itemName);
        assertThat(order.getItems().get(0).getPrice()).isEqualTo(itemPrice);
        assertThat(order.getItems().get(0).getQuantity()).isEqualTo(itemQuantity);
    }

    @Test
    void an_order_belongs_to_a_client() {
        String clientId = "abc";
        Order order = new Order("id", emptyList(), clientId, LocalDateTime.now());
        assertThat(order.getClientId()).isEqualTo("abc");
    }

    @Test
    void a_new_order_has_a_status_created() {
        Order order = new Order("id", emptyList(), "", LocalDateTime.now());
        assertThat(order.getStatus()).isEqualTo(OrderStatus.CREATED);
    }

    @Test
    void a_status_order_history_is_updated_when_order_is_created() {
        LocalDateTime creationDate = LocalDateTime.now();
        Order order = new Order("id", emptyList(), "", creationDate);
        assertThat(order.getStatusHistories()).hasSize(1);

        StatusHistory statusHistory = order.getStatusHistories().get(0);
        assertThat(statusHistory.getOrderStatus()).isEqualTo(OrderStatus.CREATED);
        assertThat(statusHistory.getDateTime()).isEqualTo(creationDate);
    }

    @Test
    void status_and_status_history_updated_when_order_is_accepted() {
        LocalDateTime creationDate = LocalDateTime.now();
        Order order = new Order("id", emptyList(), "", creationDate);
        LocalDateTime acceptationDate = LocalDateTime.now();
        order.accept(acceptationDate);

        assertThat(order.getStatus()).isEqualTo(OrderStatus.ACCEPTED);
        assertThat(order.getStatusHistories()).hasSize(2);

        StatusHistory statusHistory = order.getStatusHistories().get(1);
        assertThat(statusHistory.getOrderStatus()).isEqualTo(OrderStatus.ACCEPTED);
        assertThat(statusHistory.getDateTime()).isEqualTo(acceptationDate);
    }

    @Test
    void status_and_status_history_updated_when_deliver_driver_accepted_to_deliver() throws OrderNotAcceptedException, OrderAlreadyAssignedToADeliveryDriver {
        Order order = new Order("id", emptyList(), "", LocalDateTime.now());
        order.accept(LocalDateTime.now());

        String deliveryDriverId = "deliveryDriverId";
        order.acceptDelivery(deliveryDriverId);

        assertThat(order.getDeliveryDriverId()).isEqualTo(deliveryDriverId);
    }

    @Test
    void delivery_driver_can_not_accept_an_order_if_the_restaurant_did_not_accept_the_order() {
        Order order = new Order("id", emptyList(), "", LocalDateTime.now());

        String deliveryDriverId = "deliveryDriverId";
        assertThatThrownBy(() -> order.acceptDelivery(deliveryDriverId))
                .hasMessage("The order \"id\" has not been accepted yet!");
    }

    @Test
    void can_not_accept_for_delivery_twice_the_same_order() throws OrderNotAcceptedException, OrderAlreadyAssignedToADeliveryDriver {
        Order order = new Order("id", emptyList(), "", LocalDateTime.now());
        order.accept(LocalDateTime.now());
        String deliveryDriverId = "deliveryDriverId";
        order.acceptDelivery(deliveryDriverId);

        assertThatThrownBy(() -> order.acceptDelivery("deliveryDriverId2"))
                .hasMessage("The order \"id\" has already been assigned to a delivery driver");
    }
}
