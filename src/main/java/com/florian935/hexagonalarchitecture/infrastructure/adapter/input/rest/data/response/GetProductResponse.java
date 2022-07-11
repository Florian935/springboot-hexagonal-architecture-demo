package com.florian935.hexagonalarchitecture.infrastructure.adapter.input.rest.data.response;

import lombok.Builder;

@Builder
public record GetProductResponse(Long productId, String label, Long price) {
}
