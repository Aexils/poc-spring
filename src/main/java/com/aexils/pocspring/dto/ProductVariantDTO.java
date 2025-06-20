package com.aexils.pocspring.dto;

import com.aexils.pocspring.entity.ProductVariant;
import lombok.Builder;
import java.math.BigDecimal;

@Builder
public record ProductVariantDTO(
        String id,
        String variantName,
        BigDecimal priceOverride,
        int stock,
        boolean active,
        String productId
) {
    public static ProductVariantDTO from(ProductVariant variant) {
        return ProductVariantDTO.builder()
                .id(variant.getId())
                .variantName(variant.getVariantName())
                .priceOverride(variant.getPriceOverride())
                .stock(variant.getStock())
                .active(variant.isActive())
                .productId(variant.getProduct().getId())
                .build();
    }
}
