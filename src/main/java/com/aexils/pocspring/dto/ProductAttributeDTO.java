package com.aexils.pocspring.dto;

import com.aexils.pocspring.entity.ProductAttribute;

public record ProductAttributeDTO(
        String id,
        String name,
        String value,
        String type,
        String productId
) {
    public static ProductAttributeDTO from(ProductAttribute attr) {
        return new ProductAttributeDTO(
                attr.getId(),
                attr.getName(),
                attr.getValue(),
                attr.getType(),
                attr.getProduct() != null ? attr.getProduct().getId() : null
        );
    }
}
