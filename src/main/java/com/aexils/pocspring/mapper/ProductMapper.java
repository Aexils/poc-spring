package com.aexils.pocspring.mapper;

import com.aexils.pocspring.dto.ProductDTO;
import com.aexils.pocspring.entity.Product;
import com.aexils.pocspring.entity.Category;
import com.aexils.pocspring.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ProductMapper {

    private final CategoryRepository categoryRepository;

    public ProductDTO toDTO(Product product) {
        if (product == null) return null;

        return new ProductDTO(
                product.getId(),
                product.getName(),
                product.getSlug(),
                product.getDescription(),
                product.getPrice(),
                product.isActive(),
                product.getCategory() != null ? product.getCategory().getId() : null,
                product.getCategory() != null ? product.getCategory().getName() : null
        );
    }

    public Product fromDTO(ProductDTO dto) {
        if (dto == null) return null;

        Category category = null;
        if (dto.categoryId() != null) {
            category = categoryRepository.findById(dto.categoryId())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid category ID: " + dto.categoryId()));
        }

        Product product = new Product();
        product.setId(dto.id() != null ? dto.id() : UUID.randomUUID().toString());
        product.setName(dto.name());
        product.setSlug(dto.slug() != null ? dto.slug() : slugify(dto.name()));
        product.setDescription(dto.description());
        product.setPrice(dto.price() != null ? dto.price() : BigDecimal.ZERO);
        product.setActive(dto.active());
        product.setCategory(category);
        product.setCreatedAt(LocalDateTime.now());
        return product;
    }

    public void updateFromDTO(Product product, ProductDTO dto) {
        if (product == null || dto == null) return;

        Category category = null;
        if (dto.categoryId() != null) {
            category = categoryRepository.findById(dto.categoryId())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid category ID: " + dto.categoryId()));
        }

        product.setName(dto.name());
        product.setSlug(dto.slug() != null ? dto.slug() : slugify(dto.name()));
        product.setDescription(dto.description());
        product.setPrice(dto.price() != null ? dto.price() : BigDecimal.ZERO);
        product.setActive(dto.active());
        product.setCategory(category);
        product.setUpdatedAt(LocalDateTime.now());
    }

    private String slugify(String input) {
        return input == null ? "" : input.toLowerCase()
                .replaceAll("[^a-z0-9\\s]", "")
                .replaceAll("\\s+", "-")
                .replaceAll("-{2,}", "-");
    }
}
