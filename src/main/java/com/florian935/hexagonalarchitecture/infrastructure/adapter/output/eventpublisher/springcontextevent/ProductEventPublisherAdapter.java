package com.florian935.hexagonalarchitecture.infrastructure.adapter.output.eventpublisher.springcontextevent;

import com.florian935.hexagonalarchitecture.application.port.output.ProductEventPublisher;
import com.florian935.hexagonalarchitecture.domain.product.event.ProductCreatedEvent;
import com.florian935.hexagonalarchitecture.infrastructure.configuration.annotation.EventAdapter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.ApplicationEventPublisher;

import static lombok.AccessLevel.PRIVATE;

@EventAdapter
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class ProductEventPublisherAdapter implements ProductEventPublisher {

    ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void publishProductCreatedEvent(ProductCreatedEvent event) {
        System.out.println("Event published: " + event);
        applicationEventPublisher.publishEvent(event);
    }

}
