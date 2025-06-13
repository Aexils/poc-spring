package com.aexils.pocspring.mapper;

import com.aexils.pocspring.dto.ProductVariantDTO;
import com.aexils.pocspring.entity.ProductVariant;
import com.aexils.pocspring.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductVariantMapper {

    private final ProductRepository productRepository;

    public ProductVariantDTO toDTO(ProductVariant variant) {
        return new ProductVariantDTO(
                variant.getId(),
                variant.getVariantName(),
                variant.getPriceOverride(),
                variant.getStock(),
                variant.isActive(),
                variant.getProduct().getId()
        );
    }

    public ProductVariant fromDTO(ProductVariantDTO dto) {
        ProductVariant variant = new ProductVariant();
        variant.setId(dto.id());
        variant.setVariantName(dto.variantName());
        variant.setPriceOverride(dto.priceOverride());
        variant.setStock(dto.stock());
        variant.setActive(dto.active());
        variant.setProduct(productRepository.findById(dto.productId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid product ID")));
        return variant;
    }
}
