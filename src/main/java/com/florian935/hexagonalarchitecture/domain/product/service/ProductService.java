package com.florian935.hexagonalarchitecture.domain.product.service;

import com.florian935.hexagonalarchitecture.application.port.input.CreateProductUseCase;
import com.florian935.hexagonalarchitecture.application.port.input.GetProductUseCase;
import com.florian935.hexagonalarchitecture.application.port.output.ProductOutputPort;
import com.florian935.hexagonalarchitecture.domain.product.model.Price;
import com.florian935.hexagonalarchitecture.domain.product.model.Product;
import com.florian935.hexagonalarchitecture.domain.product.model.ProductId;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class ProductService implements CreateProductUseCase, GetProductUseCase {

    ProductOutputPort productOutputPort;

    @Override
    public Product createProduct(Product product) {
        return productOutputPort.saveProduct(product);
    }

    @Override
    public Product getProductById(ProductId productId) {
        return productOutputPort.findProductById(productId);
    }

    @Override
    public List<Product> getProductsByPriceGreaterThanOrEqualTo(Price price) {
        return productOutputPort.findProductsByPriceGreaterThanOrEqualTo(price);
    }

    @Override
    public List<Product> findAllProducts() {
        return productOutputPort.findAllProducts();
    }
}
