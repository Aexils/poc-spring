package com.aexils.pocspring.mapper;

import com.aexils.pocspring.dto.UserDTO;
import com.aexils.pocspring.entity.User;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UserMapper {

    public User fromJwt(Jwt jwt) {
        return User.builder()
                .id(jwt.getSubject())
                .email(jwt.getClaim("email"))
                .name(jwt.getClaim("name"))
                .picture(jwt.getClaim("picture"))
                .emailVerified(Boolean.TRUE.equals(jwt.getClaim("email_verified")))
                .build();
    }

    public static UserDTO toDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .picture(user.getPicture())
                .role(user.getRole().name())
                .active(user.isActive())
                .customer(user.getCustomer() != null ? CustomerMapper.toDTO(user.getCustomer()) : null)
                .cart(user.getCart() != null ? CartMapper.toDTO(user.getCart()) : null)
                .build();
    }
}
