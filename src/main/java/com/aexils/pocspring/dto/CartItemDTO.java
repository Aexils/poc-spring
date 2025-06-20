package com.aexils.pocspring.dto;

import lombok.Builder;


@Builder
public record CartItemDTO(
        String id,
        int quantity,
        String variantId,
        ProductDTO product
) {}

