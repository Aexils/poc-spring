package com.aexils.pocspring.dto;

import java.util.List;

public record CartDTO<CartItemDTO>(
        String id,
        List<CartItemDTO> items
) {}
