package com.aexils.pocspring.dto;

public record ProductAttributeDTO(
        String id,
        String name,
        String value,
        String type,
        String productId
) {}
