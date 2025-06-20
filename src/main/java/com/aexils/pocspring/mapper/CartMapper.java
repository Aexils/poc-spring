package com.aexils.pocspring.mapper;

import com.aexils.pocspring.dto.CartDTO;
import com.aexils.pocspring.entity.Cart;
import lombok.Builder;

@Builder
public class CartMapper {

    public static CartDTO toDTO(Cart cart) {
        if (cart == null) return null;

        return CartDTO.builder()
                .id(cart.getId())
                .items(cart.getItems().stream()
                        .map(CartItemMapper::toDTO)
                        .toList())
                .build();
    }
}
