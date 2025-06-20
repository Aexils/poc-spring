package com.aexils.pocspring.dto;

import lombok.Builder;

@Builder
public record UserDTO(
        String id,
        String email,
        String name,
        String picture,
        String role,
        boolean active,
        CustomerDTO customer,
        CartDTO cart
) {}
