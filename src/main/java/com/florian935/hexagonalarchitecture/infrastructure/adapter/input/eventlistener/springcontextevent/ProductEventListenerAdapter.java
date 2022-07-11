package com.florian935.hexagonalarchitecture.infrastructure.adapter.input.eventlistener.springcontextevent;

import com.florian935.hexagonalarchitecture.domain.product.event.ProductCreatedEvent;
import com.florian935.hexagonalarchitecture.infrastructure.configuration.annotation.EventAdapter;
import org.springframework.context.event.EventListener;

@EventAdapter
public class ProductEventListenerAdapter {

    @EventListener
    public void handle(ProductCreatedEvent event){
        System.out.println("Event created: " + event);
    }

}
