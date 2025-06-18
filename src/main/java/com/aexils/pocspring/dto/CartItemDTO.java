package com.aexils.pocspring.dto;

import java.math.BigDecimal;

public record CartItemDTO(
        String id,
        int quantity,
        String cartId,
        String variantId,
        String variantName,
        BigDecimal price,
        ProductDTO product
) {}

