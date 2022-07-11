package com.florian935.hexagonalarchitecture.infrastructure.adapter.output.persistence.h2.mapper;

import com.florian935.hexagonalarchitecture.domain.product.model.Price;
import com.florian935.hexagonalarchitecture.domain.product.model.Product;
import com.florian935.hexagonalarchitecture.domain.product.model.ProductId;
import com.florian935.hexagonalarchitecture.infrastructure.adapter.input.rest.data.request.CreateProductRequest;
import com.florian935.hexagonalarchitecture.infrastructure.adapter.input.rest.data.response.GetProductResponse;
import com.florian935.hexagonalarchitecture.infrastructure.adapter.output.persistence.h2.entity.ProductEntity;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;

@Configuration
public class ProductPersistenceMapper {

    public Product toProduct(ProductEntity productEntity) {
        final ProductId productId = ProductId.of(productEntity.getProductId());
        final Price price = Price.of(productEntity.getPrice());

        return Product.builder()
                .productId(productId)
                .label(productEntity.getLabel())
                .price(price)
                .build();
    }

    public ProductEntity toProductEntity(Product product) {
        Long productId = null;
        if (Objects.nonNull(product.productId())) {
            productId = product.productId().getProductId();
        }

        return ProductEntity.builder()
                .productId(productId)
                .label(product.label())
                .price(product.price().getPrice())
                .build();
    }

    public GetProductResponse toGetProductResponse(Product product) {
        final Long productId = product.productId().getProductId();

        return GetProductResponse.builder()
                .productId(productId)
                .label(product.label())
                .price(product.price().getPrice())
                .build();
    }

    public Product toProduct(CreateProductRequest createProductRequest) {
        final Price price = Price.of(createProductRequest.price());

        return Product.builder()
                .label(createProductRequest.label())
                .price(price)
                .build();
    }
}
