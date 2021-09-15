package com.zenika.zenikeats.domain.order;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Order {
    private String id;
    private List<Item> orderedItems;
    private String clientId;
    private OrderStatus status;
    private List<StatusHistory> statusHistories;
    private String deliveryDriverId;
    private LocalDateTime creationDate;

    public Order(String id, List<Item> orderedItems, String clientId, LocalDateTime creationDate) {
        this.id = id;
        this.orderedItems = Collections.unmodifiableList(orderedItems);
        this.clientId = clientId;
        this.statusHistories = new ArrayList<>();
        this.creationDate = creationDate;
        changeStatus(OrderStatus.CREATED, creationDate);
    }

    private Order() {

    }

    public OrderAccepted accept(LocalDateTime acceptationDate) {
        changeStatus(OrderStatus.ACCEPTED, acceptationDate);
        return new OrderAccepted(id, acceptationDate);
    }

    public void acceptDelivery(String deliveryDriverId) throws OrderNotAcceptedException, OrderAlreadyAssignedToADeliveryDriver {
        if (status != OrderStatus.ACCEPTED) {
            throw new OrderNotAcceptedException(id);
        }
        if (this.deliveryDriverId != null) {
            throw new OrderAlreadyAssignedToADeliveryDriver(id);
        }
        this.deliveryDriverId = deliveryDriverId;
    }

    public String getId() {
        return id;
    }

    public List<Item> getItems() {
        return Collections.unmodifiableList(orderedItems);
    }

    public String getClientId() {
        return clientId;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public List<StatusHistory> getStatusHistories() {
        return Collections.unmodifiableList(statusHistories);
    }

    public String getDeliveryDriverId() {
        return deliveryDriverId;
    }

    private void changeStatus(OrderStatus orderStatus, LocalDateTime historyDate) {
        this.status = orderStatus;
        this.statusHistories.add(new StatusHistory(orderStatus, historyDate));
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }
}
