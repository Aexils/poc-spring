package com.aexils.pocspring.controller;

import com.aexils.pocspring.dto.UserDTO;
import com.aexils.pocspring.entity.User;
import com.aexils.pocspring.mapper.UserMapper;
import com.aexils.pocspring.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
public class UserController {

    public UserController(UserService userService) {
        this.userService = userService;
    }

    private final UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/users/size")
    public ResponseEntity<Integer> GetUserFromJWT(
            @AuthenticationPrincipal Jwt principal
    ) {
        if (principal == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Integer users = userService.getNumberOfUsers();

        return ResponseEntity.ok(users);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> GetAllUsers(
            @AuthenticationPrincipal Jwt principal
    ) {
        if (principal == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        List<User> users = userService.findAllUsers();

        return ResponseEntity.ok(users);
    }

    @PutMapping("/user")
    public ResponseEntity<UserDTO> updateUser(
            @Valid @RequestBody UserDTO userDto,
            @AuthenticationPrincipal Jwt principal
    ) {
        if (principal == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        User userRequest = userService.findById(principal.getClaim("sub"));
        if (userRequest == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        User userToUpdate = userService.findById(userDto.id());
        if (userToUpdate == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        boolean isAdmin = userRequest.getRole() == User.Role.ADMIN;
        boolean isSelf = userRequest.getId().equals(userToUpdate.getId());

        if (!(isAdmin || isSelf)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        User updated = userService.updateUser(userToUpdate, userDto);
        return ResponseEntity.ok(UserMapper.toDTO(updated));

    }

}
