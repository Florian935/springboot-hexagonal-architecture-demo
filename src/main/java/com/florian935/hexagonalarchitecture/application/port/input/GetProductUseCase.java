package com.florian935.hexagonalarchitecture.application.port.input;

import com.florian935.hexagonalarchitecture.domain.product.model.Product;
import com.florian935.hexagonalarchitecture.domain.product.model.ProductId;

import java.util.List;

public interface GetProductUseCase {

    Product getProductById(ProductId productId);

    List<Product> findAllProducts();
}
