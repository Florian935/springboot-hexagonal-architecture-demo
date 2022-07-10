package com.florian935.hexagonalarchitecture.domain.product.model;

import lombok.Builder;

import java.util.function.Function;

@Builder
public record Product(ProductId productId, String label) {
}
