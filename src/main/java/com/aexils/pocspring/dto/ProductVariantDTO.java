package com.aexils.pocspring.dto;

import java.math.BigDecimal;

public record ProductVariantDTO(
        String id,
        String variantName,       // "Taille M / Rouge"
        BigDecimal priceOverride, // null si on garde le prix du produit parent
        int stock,
        boolean active,
        String productId
) {}
