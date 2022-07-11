package com.florian935.hexagonalarchitecture;

import com.florian935.hexagonalarchitecture.infrastructure.adapter.output.persistence.h2.entity.ProductEntity;
import com.florian935.hexagonalarchitecture.infrastructure.adapter.output.persistence.h2.repository.ProductEntityRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HexagonalArchitectureApplication {

	public static void main(String[] args) {
		SpringApplication.run(HexagonalArchitectureApplication.class, args);
	}

	@Bean
	ApplicationRunner init(ProductEntityRepository productEntityRepository) {
		return args -> {
			productEntityRepository.save(new ProductEntity(null, "Product 1", 10L));
			productEntityRepository.save(new ProductEntity(null, "Product 2", 20L));
			productEntityRepository.save(new ProductEntity(null, "Product 3",30L));
			productEntityRepository.save(new ProductEntity(null, "Product 4", null));
			productEntityRepository.save(new ProductEntity(null, "Product 5", 50L));
		};
	}
}
