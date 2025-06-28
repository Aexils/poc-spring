package com.aexils.pocspring.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record UserDTO(
        String id,
        String email,
        String name,
        String picture,
        String role,
        boolean active,
        LocalDateTime createdAt,
        CustomerDTO customer,
        CartDTO cart
) {}
