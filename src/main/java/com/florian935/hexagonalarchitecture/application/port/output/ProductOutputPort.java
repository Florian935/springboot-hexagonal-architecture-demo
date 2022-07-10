package com.florian935.hexagonalarchitecture.application.port.output;

import com.florian935.hexagonalarchitecture.domain.product.model.Product;
import com.florian935.hexagonalarchitecture.domain.product.model.ProductId;

import java.util.List;

public interface ProductOutputPort {

    Product saveProduct(Product product);

    Product findProductById(ProductId productId);

    List<Product> findAllProducts();
}
