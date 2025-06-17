package com.aexils.pocspring.dto;

import java.math.BigDecimal;

public record ProductDTO(
        String id,
        String name,
        String slug,
        String description,
        BigDecimal price,
        boolean active,
        String categoryId,
        String categoryName,
        java.util.List<ProductAttributeDTO> attributes,
        java.util.List<ProductImageDTO> images,
        java.util.List<ProductVariantDTO> variants
) {}
