package com.aexils.pocspring.mapper;

import com.aexils.pocspring.dto.ProductAttributeDTO;
import com.aexils.pocspring.dto.ProductDTO;
import com.aexils.pocspring.dto.ProductImageDTO;
import com.aexils.pocspring.dto.ProductVariantDTO;
import com.aexils.pocspring.entity.Category;
import com.aexils.pocspring.entity.Product;

public class ProductMapper {

    public static ProductDTO toDTO(Product product) {
        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .slug(product.getSlug())
                .description(product.getDescription())
                .sku(product.getSku())
                .price(product.getPrice())
                .active(product.isActive())
                .categoryId(product.getCategory() != null ? product.getCategory().getId() : null)
                .categoryName(product.getCategory() != null ? product.getCategory().getName() : null)
                .images(product.getImages().stream().map(ProductImageDTO::from).toList())
                .attributes(product.getAttributes().stream().map(ProductAttributeDTO::from).toList())
                .variants(product.getVariants().stream().map(ProductVariantDTO::from).toList())
                .build();
    }

    public static Product fromDTO(ProductDTO dto, Category category) {
        Product product = new Product();
        return updateFromDTO(product, dto, category);
    }

    public static Product updateFromDTO(Product product, ProductDTO dto, Category category) {
        product.setName(dto.name());
        product.setSlug(dto.slug());
        product.setDescription(dto.description());
        product.setSku(dto.sku());
        product.setPrice(dto.price());
        product.setActive(dto.active());
        product.setCategory(category);

        product.setImages(dto.images().stream()
                .map(i -> ProductImageMapper.fromDTO(i, product)).toList());

        product.setAttributes(dto.attributes().stream()
                .map(a -> ProductAttributeMapper.fromDTO(a, product)).toList());

        product.setVariants(dto.variants().stream()
                .map(v -> ProductVariantMapper.fromDTO(v, product)).toList());

        return product;
    }
}
