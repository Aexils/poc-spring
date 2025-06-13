package com.aexils.pocspring.mapper;

import com.aexils.pocspring.dto.CategoryDTO;
import com.aexils.pocspring.entity.Category;
import com.aexils.pocspring.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CategoryMapper {

    private final CategoryRepository categoryRepository;

    public CategoryDTO toDTO(Category category) {
        if (category == null) return null;

        return new CategoryDTO(
                category.getId(),
                category.getName(),
                category.getSlug(),
                category.getParent() != null ? category.getParent().getId() : null
        );
    }

    public Category fromDTO(CategoryDTO dto) {
        if (dto == null) return null;

        Category category = new Category();
        category.setId(dto.id() != null ? dto.id() : UUID.randomUUID().toString());
        category.setName(dto.name());
        category.setSlug(dto.slug() != null ? dto.slug() : slugify(dto.name()));

        if (dto.parentId() != null) {
            category.setParent(categoryRepository.findById(dto.parentId())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid parent ID: " + dto.parentId())));
        }

        return category;
    }

    public void updateFromDTO(Category category, CategoryDTO dto) {
        category.setName(dto.name());
        category.setSlug(dto.slug() != null ? dto.slug() : slugify(dto.name()));

        if (dto.parentId() != null) {
            category.setParent(categoryRepository.findById(dto.parentId())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid parent ID: " + dto.parentId())));
        } else {
            category.setParent(null);
        }
    }

    private String slugify(String input) {
        return input == null ? "" : input.toLowerCase()
                .replaceAll("[^a-z0-9\\s]", "")
                .replaceAll("\\s+", "-")
                .replaceAll("-{2,}", "-");
    }
}
