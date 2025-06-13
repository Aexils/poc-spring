package com.aexils.pocspring.dto;

import com.aexils.pocspring.entity.User;
import lombok.Builder;

@Builder
public record UserResponseDto(
        String id,
        String email,
        String name,
        boolean active,
        String role,
        String picture
) {
    public static UserResponseDto from(User user) {
        return UserResponseDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .active(user.isActive())
                .role(user.getRole().name())
                .picture(user.getPicture())
                .build();
    }
}
