package com.florian935.hexagonalarchitecture.application.port.output;

import com.florian935.hexagonalarchitecture.domain.product.event.ProductCreatedEvent;

public interface ProductEventPublisher {

    void publishProductCreatedEvent(ProductCreatedEvent event);

}
