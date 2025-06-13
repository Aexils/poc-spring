package com.aexils.pocspring.dto;

public record CategoryDTO(
        String id,
        String name,
        String slug,
        String parentId
) {}
