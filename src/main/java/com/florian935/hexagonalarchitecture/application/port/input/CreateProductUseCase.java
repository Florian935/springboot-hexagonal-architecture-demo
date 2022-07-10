package com.florian935.hexagonalarchitecture.application.port.input;

import com.florian935.hexagonalarchitecture.domain.product.model.Product;

public interface CreateProductUseCase {

    Product createProduct(Product product);
}
