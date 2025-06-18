package com.aexils.pocspring.mapper;

import com.aexils.pocspring.dto.*;
import com.aexils.pocspring.entity.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CartMapper {

    public CartDTO toDTO(Cart cart) {
        List<CartItemDTO> itemDTOs = cart.getItems().stream()
                .map(this::mapItem)
                .toList();

        return new CartDTO(cart.getId(), itemDTOs);
    }

    public CartItemDTO mapItem(CartItem item) {
        ProductVariant variant = item.getVariant();
        Product product = variant.getProduct();

        List<ProductAttributeDTO> attributes = product.getAttributes().stream()
                .map(attr -> new ProductAttributeDTO(
                        attr.getId(),
                        attr.getName(),
                        attr.getValue(),
                        attr.getType(),
                        product.getId()
                ))
                .toList();

        List<ProductImageDTO> images = product.getImages().stream()
                .map(img -> new ProductImageDTO(
                        img.getId(),
                        img.getUrl(),
                        img.isMain(),
                        product.getId()
                ))
                .toList();

        List<ProductVariantDTO> variants = product.getVariants().stream()
                .map(v -> new ProductVariantDTO(
                        v.getId(),
                        v.getVariantName(),
                        v.getPriceOverride(),
                        v.getStock(),
                        true,
                        product.getId()
                ))
                .toList();

        return new CartItemDTO(
                item.getId(),
                item.getQuantity(),
                item.getCart().getId(),
                variant.getId(),
                variant.getVariantName(),
                variant.getPriceOverride(),
                new ProductDTO(
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
                )
        );
    }
}
