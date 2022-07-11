package com.florian935.hexagonalarchitecture.infrastructure.adapter.output.persistence.h2;

import com.florian935.hexagonalarchitecture.application.port.output.ProductOutputPort;
import com.florian935.hexagonalarchitecture.domain.product.model.Price;
import com.florian935.hexagonalarchitecture.domain.product.model.Product;
import com.florian935.hexagonalarchitecture.domain.product.model.ProductId;
import com.florian935.hexagonalarchitecture.infrastructure.adapter.output.persistence.h2.entity.ProductEntity;
import com.florian935.hexagonalarchitecture.infrastructure.adapter.output.persistence.h2.mapper.ProductPersistenceMapper;
import com.florian935.hexagonalarchitecture.infrastructure.adapter.output.persistence.h2.repository.ProductEntityRepository;
import com.florian935.hexagonalarchitecture.infrastructure.adapter.output.specification.BaseSpecification;
import com.florian935.hexagonalarchitecture.infrastructure.configuration.annotation.PersistenceAdapter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

import static lombok.AccessLevel.PRIVATE;

@PersistenceAdapter
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class H2ProductPersistenceAdapter extends BaseSpecification<ProductEntity> implements ProductOutputPort {
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
    public List<Product> findProductsByPriceGreaterThanOrEqualTo(Price price) {
        final Specification<ProductEntity> specification = Specification
                .where(greaterThanOrEqualTo("price", price.getPrice()))
                .and(notNull("price"))
                .and(notContain("label", "PrOdUcT 1", true));

        return productEntityRepository.findAll(specification).stream()
                .map(productPersistenceMapper::toProduct)
                .toList();
    }

    @Override
    public List<Product> findAllProducts() {
        return productEntityRepository.findAll().stream()
                .map(productPersistenceMapper::toProduct)
                .toList();
    }
}
