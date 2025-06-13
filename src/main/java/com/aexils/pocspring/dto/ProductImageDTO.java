package com.aexils.pocspring.dto;

public record ProductImageDTO(
        String id,
        String url,
        boolean isMain,
        String productId
) {}
