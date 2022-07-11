package com.florian935.hexagonalarchitecture.infrastructure.configuration;

import com.florian935.hexagonalarchitecture.domain.product.service.ProductService;
import com.florian935.hexagonalarchitecture.infrastructure.adapter.output.eventpublisher.springcontextevent.ProductEventPublisherAdapter;
import com.florian935.hexagonalarchitecture.infrastructure.adapter.output.persistence.h2.H2ProductPersistenceAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AdapterConfiguration {

    @Bean
    public ProductService productService(H2ProductPersistenceAdapter h2ProductPersistenceAdapter,
                                         ProductEventPublisherAdapter productEventPublisherAdapter) {
        return new ProductService(h2ProductPersistenceAdapter, productEventPublisherAdapter);
    }
}
