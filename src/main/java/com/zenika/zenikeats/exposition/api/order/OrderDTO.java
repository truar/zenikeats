package com.zenika.zenikeats.exposition.api.order;

import com.zenika.zenikeats.domain.order.OrderStatus;

public class OrderDTO {
    private String id;
    private OrderStatus status;
    private String clientId;

    public OrderDTO() {
    }

    public OrderDTO(String id, OrderStatus status, String clientId) {
        this.id = id;
        this.status = status;
        this.clientId = clientId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
}
