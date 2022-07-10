package com.florian935.hexagonalarchitecture.domain.product.model;

import lombok.Value;

@Value(staticConstructor = "of")
public class ProductId {
    Long productId;
}
