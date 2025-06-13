package com.aexils.pocspring.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record UserDto(
        @NotBlank String id,
        @Email @NotBlank String email,
        @NotBlank String name,
        @NotNull Boolean active,
        @NotBlank String role,
        String picture
) {}
