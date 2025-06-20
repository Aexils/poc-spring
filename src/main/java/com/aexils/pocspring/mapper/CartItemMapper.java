package com.aexils.pocspring.mapper;

import com.aexils.pocspring.dto.CartItemDTO;
import com.aexils.pocspring.entity.CartItem;

public class CartItemMapper {

    public static CartItemDTO toDTO(CartItem item) {
        return CartItemDTO.builder()
                .id(item.getId())
                .quantity(item.getQuantity())
                .product(ProductMapper.toDTO(item.getVariant().getProduct()))
                .build();
    }
}
