package com.aexils.pocspring.dto;

import com.aexils.pocspring.entity.ProductImage;

public record ProductImageDTO(String id, String url, boolean isMain, String productId) {
    public static ProductImageDTO from(ProductImage image) {
        return new ProductImageDTO(image.getId(), image.getUrl(), image.isMain(), image.getProduct().getId());
    }
}
