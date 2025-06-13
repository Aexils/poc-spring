package com.aexils.pocspring.mapper;

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
}
