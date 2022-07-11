package com.florian935.hexagonalarchitecture.infrastructure.adapter.input.rest;

import com.florian935.hexagonalarchitecture.application.port.input.CreateProductUseCase;
import com.florian935.hexagonalarchitecture.application.port.input.GetProductUseCase;
import com.florian935.hexagonalarchitecture.domain.product.model.Price;
import com.florian935.hexagonalarchitecture.domain.product.model.Product;
import com.florian935.hexagonalarchitecture.domain.product.model.ProductId;
import com.florian935.hexagonalarchitecture.infrastructure.adapter.input.rest.data.request.CreateProductRequest;
import com.florian935.hexagonalarchitecture.infrastructure.adapter.input.rest.data.response.GetProductResponse;
import com.florian935.hexagonalarchitecture.infrastructure.adapter.output.persistence.h2.mapper.ProductPersistenceMapper;
import com.florian935.hexagonalarchitecture.infrastructure.configuration.annotation.WebAdapter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@WebAdapter
@RequestMapping("api/v1/products")
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class ProductRestAdapter {

    GetProductUseCase getProductUseCase;
    CreateProductUseCase createProductUseCase;
    ProductPersistenceMapper productPersistenceMapper;

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    List<GetProductResponse> findAllProducts() {
        final List<Product> allProducts = getProductUseCase.findAllProducts();

        return allProducts.stream()
                .map(productPersistenceMapper::toGetProductResponse)
                .toList();
    }

    @GetMapping(path = "{productId}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    @ResponseBody
    GetProductResponse findProductById(@PathVariable Long productId) {
        final Product product = getProductUseCase.getProductById(ProductId.of(productId));

        return productPersistenceMapper.toGetProductResponse(product);
    }

    @GetMapping(path = "prices/{price}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    @ResponseBody
    List<GetProductResponse> findProductsByPriceGreaterThanOrEqualTo(@PathVariable Long price) {
        final List<Product> products = getProductUseCase.getProductsByPriceGreaterThanOrEqualTo(Price.of(price));

        return products.stream()
                .map(productPersistenceMapper::toGetProductResponse)
                .toList();
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(CREATED)
    @ResponseBody
    GetProductResponse saveProduct(@RequestBody CreateProductRequest createProductRequest) {
        final Product productToCreate = productPersistenceMapper.toProduct(createProductRequest);
        final Product productCreated = createProductUseCase.createProduct(productToCreate);

        return productPersistenceMapper.toGetProductResponse(productCreated);
    }
}
