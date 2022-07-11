package com.florian935.hexagonalarchitecture.domain.product.event;

import com.florian935.hexagonalarchitecture.domain.product.model.Product;

import java.time.LocalDateTime;

public record ProductCreatedEvent(Product product, LocalDateTime date) {
}
