package com.florian935.hexagonalarchitecture.infrastructure.adapter.input.rest.data.request;

import lombok.Builder;

@Builder
public record CreateProductRequest(String label) {
}
