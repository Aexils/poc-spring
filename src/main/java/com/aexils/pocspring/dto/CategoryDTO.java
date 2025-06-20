package com.aexils.pocspring.dto;

public record CategoryDTO(
        String id,
        String name,
        String slug,
        String parentId
) {
    public static CategoryDTO from(com.aexils.pocspring.entity.Category category) {
        return new CategoryDTO(
                category.getId(),
                category.getName(),
                category.getSlug(),
                category.getParent() != null ? category.getParent().getId() : null
        );
    }
}
