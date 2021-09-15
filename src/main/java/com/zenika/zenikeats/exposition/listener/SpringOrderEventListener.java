package com.zenika.zenikeats.exposition.listener;

import com.zenika.zenikeats.domain.order.OrderAccepted;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class SpringOrderEventListener {

//    private OtherApplicationService

    @EventListener
    public void listenOrderAccepted(OrderAccepted event) {
        //...
    }

}
