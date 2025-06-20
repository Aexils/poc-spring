package com.aexils.pocspring.dto;

import com.aexils.pocspring.entity.Cart;
import com.aexils.pocspring.entity.Customer;
import lombok.Builder;

import java.util.List;

@Builder
public record CartDTO(
        String id,
        List<CartItemDTO> items
) {}
