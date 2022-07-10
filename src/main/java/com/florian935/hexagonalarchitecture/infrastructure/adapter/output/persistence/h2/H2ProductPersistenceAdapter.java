package com.florian935.hexagonalarchitecture.infrastructure.adapter.output.persistence.h2;

import com.florian935.hexagonalarchitecture.application.port.output.ProductOutputPort;
import com.florian935.hexagonalarchitecture.domain.product.model.Product;
import com.florian935.hexagonalarchitecture.domain.product.model.ProductId;
import com.florian935.hexagonalarchitecture.infrastructure.adapter.output.persistence.h2.entity.ProductEntity;
import com.florian935.hexagonalarchitecture.infrastructure.adapter.output.persistence.h2.mapper.ProductPersistenceMapper;
import com.florian935.hexagonalarchitecture.infrastructure.adapter.output.persistence.h2.repository.ProductEntityRepository;
import com.florian935.hexagonalarchitecture.infrastructure.configuration.annotation.PersistenceAdapter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static lombok.AccessLevel.PRIVATE;

@PersistenceAdapter
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class H2ProductPersistenceAdapter implements ProductOutputPort {
    ProductEntityRepository productEntityRepository;
    ProductPersistenceMapper productPersistenceMapper;

    @Override
    public Product saveProduct(Product product) {
        final ProductEntity productEntity = productPersistenceMapper.toProductEntity(product);
        final ProductEntity productEntitySaved = productEntityRepository.save(productEntity);

        return productPersistenceMapper.toProduct(productEntitySaved);
    }

    @Override
    public Product findProductById(ProductId productId) {
        final Optional<ProductEntity> productEntityFound = productEntityRepository.findById(productId.getProductId());

        if (productEntityFound.isEmpty()) {
            // TODO: throw exception
            return null;
        }

        return productPersistenceMapper.toProduct(productEntityFound.get());
    }

    @Override
    public List<Product> findAllProducts() {
        return productEntityRepository.findAll().stream()
                .map(productPersistenceMapper::toProduct)
                .toList();
    }
}
