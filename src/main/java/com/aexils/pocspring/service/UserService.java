package com.aexils.pocspring.service;

import com.aexils.pocspring.dto.UserDto;
import com.aexils.pocspring.entity.User;
import com.aexils.pocspring.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User register(User user) {
        return userRepository.save(user);
    }
    public User findById(String id) {
        return userRepository.findById(id).orElse(null);
    }
    public Integer getNumberOfUsers() { return userRepository.findAll().size(); }
    public List<User> findAllUsers() { return userRepository.findAll(); }
    public User updateUser(User user, UserDto dto) {
        user.setEmail(dto.email());
        user.setName(dto.name());
        user.setActive(dto.active());

        try {
            user.setRole(User.Role.valueOf(dto.role()));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid role: " + dto.role());
        }

        user.setPicture(dto.picture());
        user.setUpdatedAt(LocalDateTime.now());

        return userRepository.save(user);
    }
    public User deactivateUser(String id) {
        User user = userRepository.findById(id).orElse(null);
        userRepository.delete(user);
        return user;
    }

}

