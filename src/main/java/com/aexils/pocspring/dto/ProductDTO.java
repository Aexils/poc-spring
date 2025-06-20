package com.aexils.pocspring.dto;

import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;

@Builder
public record ProductDTO(
        String id,
        String name,
        String slug,
        String description,
        String sku,
        BigDecimal price,
        boolean active,
        String categoryId,
        String categoryName, // pour le front
        List<ProductImageDTO> images,
        List<ProductAttributeDTO> attributes,
        List<ProductVariantDTO> variants
) {}
