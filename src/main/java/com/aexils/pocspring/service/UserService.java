package com.aexils.pocspring.service;

import com.aexils.pocspring.dto.UserDTO;
import com.aexils.pocspring.entity.*;
import com.aexils.pocspring.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User registerOrLogin(User userFromJwt) {
        return userRepository.findById(userFromJwt.getId()).orElseGet(() -> {
            // Créer Customer
            Customer customer = new Customer();
            customer.setUser(userFromJwt);
            userFromJwt.setCustomer(customer);

            // Ajouter une adresse de facturation vide
            BillingAddress billingAddress = new BillingAddress();
            billingAddress.setCustomer(customer);
            customer.getBillingAddresses().add(billingAddress);

            ShippingAddress shippingAddress = new ShippingAddress();
            shippingAddress.setCustomer(customer);
            customer.getShippingAddresses().add(shippingAddress);

            Cart cart = new Cart();
            cart.setUser(userFromJwt);
            userFromJwt.setCart(cart);

            return userRepository.save(userFromJwt);
        });
    }
    public User findById(String id) {
        return userRepository.findById(id).orElse(null);
    }
    public Integer getNumberOfUsers() { return userRepository.findAll().size(); }
    public List<User> findAllUsers() { return userRepository.findAll(); }
    public User updateUser(User user, UserDTO dto) {
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

