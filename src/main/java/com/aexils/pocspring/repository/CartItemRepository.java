package com.aexils.pocspring.repository;

import com.aexils.pocspring.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, String> {
    Optional<CartItem> findByCartIdAndVariantId(String cartId, String variantId);
}
