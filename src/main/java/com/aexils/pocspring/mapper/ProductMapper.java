package com.aexils.pocspring.mapper;

import com.aexils.pocspring.dto.ProductAttributeDTO;
import com.aexils.pocspring.dto.ProductDTO;
import com.aexils.pocspring.dto.ProductImageDTO;
import com.aexils.pocspring.dto.ProductVariantDTO;
import com.aexils.pocspring.entity.Category;
import com.aexils.pocspring.entity.Product;
import com.aexils.pocspring.entity.ProductAttribute;
import com.aexils.pocspring.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ProductMapper {

    private final CategoryRepository categoryRepository;
    private final ProductAttributeMapper productAttributeMapper;
    private final ProductImageMapper productImageMapper;
    private final ProductVariantMapper productVariantMapper;

    public ProductDTO toDTO(Product product) {
        if (product == null) return null;

        List<ProductAttributeDTO> attributes = product.getAttributes().stream()
                .map(productAttributeMapper::toDTO)
                .toList();

        List<ProductImageDTO> images = product.getImages().stream()
                .map(productImageMapper::toDto)
                .toList();

        List<ProductVariantDTO> variants = product.getVariants().stream()
                .map(productVariantMapper::toDTO)
                .toList();

        return new ProductDTO(
                product.getId(),
                product.getName(),
                product.getSlug(),
                product.getDescription(),
                product.getPrice(),
                product.isActive(),
                product.getCategory() != null ? product.getCategory().getId() : null,
                product.getCategory() != null ? product.getCategory().getName() : null,
                attributes,
                images,
                variants
        );
    }

    public Product fromDTO(ProductDTO dto) {
        if (dto == null) return null;

        Product product = new Product();
        product.setId(dto.id() != null ? dto.id() : UUID.randomUUID().toString());
        product.setName(dto.name());
        product.setSlug(dto.slug() != null ? dto.slug() : slugify(dto.name()));
        product.setDescription(dto.description());
        product.setPrice(dto.price() != null ? dto.price() : BigDecimal.ZERO);
        product.setActive(dto.active());
        product.setCreatedAt(LocalDateTime.now());

        if (dto.categoryId() != null) {
            Category category = categoryRepository.findById(dto.categoryId())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid category ID"));
            product.setCategory(category);
        }

        return product;
    }

    public Product updateFromDTO(Product product, ProductDTO dto) {
        product.setName(dto.name());
        product.setSlug(dto.slug());
        product.setDescription(dto.description());
        product.setPrice(dto.price());
        product.setActive(dto.active());
        product.setUpdatedAt(LocalDateTime.now());
        return product;
    }

    private String slugify(String input) {
        return input == null ? "" : input.toLowerCase()
                .replaceAll("[^a-z0-9\\s]", "")
                .replaceAll("\\s+", "-")
                .replaceAll("-{2,}", "-");
    }
}