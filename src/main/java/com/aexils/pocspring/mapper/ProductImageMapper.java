package com.aexils.pocspring.mapper;

import com.aexils.pocspring.dto.ProductImageDTO;
import com.aexils.pocspring.entity.Product;
import com.aexils.pocspring.entity.ProductImage;
import com.aexils.pocspring.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductImageMapper {

    private final ProductRepository productRepository;

    public static ProductImageDTO toDto(ProductImage image) {
        if (image == null) return null;

        return new ProductImageDTO(
                image.getId(),
                image.getUrl(),
                image.isMain(),
                image.getProduct() != null ? image.getProduct().getId() : null
        );
    }

    public static ProductImage fromDTO(ProductImageDTO dto, Product product) {
        return ProductImage.builder()
                .id(dto.id()) // à garder si tu veux mettre à jour une image existante
                .url(dto.url())
                .isMain(dto.isMain())
                .product(product)
                .build();
    }

    public static ProductImage updateFromDTO(ProductImageDTO dto, ProductImage existing) {
        existing.setUrl(dto.url());
        existing.setMain(dto.isMain());
        return existing;
    }
}
