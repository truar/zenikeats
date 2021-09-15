package com.zenika.zenikeats.exposition.api.order;

import java.util.List;

public class CreateOrderDTO {
    private String clientId;
    private List<CreateItemDTO> items;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public List<CreateItemDTO> getItems() {
        return items;
    }

    public void setItems(List<CreateItemDTO> items) {
        this.items = items;
    }
}
