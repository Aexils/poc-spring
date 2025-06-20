package com.aexils.pocspring.repository;

import com.aexils.pocspring.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, String> {
    Cart findByUserId(String userId);
}
