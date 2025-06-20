package com.aexils.pocspring.mapper;

import com.aexils.pocspring.dto.ProductVariantDTO;
import com.aexils.pocspring.entity.Product;
import com.aexils.pocspring.entity.ProductVariant;

public class ProductVariantMapper {

    public static ProductVariantDTO toDTO(ProductVariant variant) {
        return new ProductVariantDTO(
                variant.getId(),
                variant.getVariantName(),
                variant.getPriceOverride(),
                variant.getStock(),
                variant.isActive(),
                variant.getProduct() != null ? variant.getProduct().getId() : null

        );
    }

    public static ProductVariant fromDTO(ProductVariantDTO dto, Product product) {
        ProductVariant productVariant = new ProductVariant();
        productVariant.setVariantName(dto.variantName());
        productVariant.setProduct(product);
        productVariant.setStock(dto.stock());
        productVariant.setActive(dto.active());
        productVariant.setPriceOverride(dto.priceOverride());

        return productVariant;
    }

    public static ProductVariant updateFromDTO(ProductVariantDTO dto, ProductVariant productVariant) {
        productVariant.setVariantName(dto.variantName());
        productVariant.setStock(dto.stock());
        productVariant.setActive(dto.active());
        productVariant.setPriceOverride(dto.priceOverride());

        return productVariant;
    }
}
