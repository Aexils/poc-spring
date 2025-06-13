package com.aexils.pocspring.controller;

import com.aexils.pocspring.dto.UserDto;
import com.aexils.pocspring.dto.UserResponseDto;
import com.aexils.pocspring.entity.User;
import com.aexils.pocspring.mapper.UserMapper;
import com.aexils.pocspring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping("/auth/me")
    public ResponseEntity<UserResponseDto> registerOrLogin(@AuthenticationPrincipal Jwt principal) {
        if (principal == null) {
            throw new ResponseStatusException(UNAUTHORIZED, "Unauthorized");
        }

        User userMapped = userMapper.fromJwt(principal);
        User user = userService.findById(userMapped.getId());

        if (user == null) {
            user = userService.register(userMapped);
        }

        return ResponseEntity.ok(UserResponseDto.from(user));
    }

    @GetMapping("/auth/me")
    public ResponseEntity<UserResponseDto> getCurrentUser(@AuthenticationPrincipal Jwt principal) {
        if (principal == null) {
            throw new ResponseStatusException(UNAUTHORIZED, "Unauthorized");
        }

        User user = userService.findById(principal.getSubject());
        if (user == null) {
            throw new ResponseStatusException(NOT_FOUND, "User not found");
        }

        return ResponseEntity.ok(UserResponseDto.from(user));
    }
}
