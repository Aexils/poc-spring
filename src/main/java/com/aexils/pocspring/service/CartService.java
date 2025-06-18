package com.aexils.pocspring.service;

import com.aexils.pocspring.dto.CartItemDTO;
import com.aexils.pocspring.entity.Cart;
import com.aexils.pocspring.entity.CartItem;
import com.aexils.pocspring.entity.ProductVariant;
import com.aexils.pocspring.entity.User;
import com.aexils.pocspring.repository.CartItemRepository;
import com.aexils.pocspring.repository.CartRepository;
import com.aexils.pocspring.repository.ProductVariantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductVariantRepository productVariantRepository;

    public Cart getOrCreateCartForUser(User user) {
        return cartRepository.findByUserId(user.getId())
                .orElseGet(() -> cartRepository.save(Cart.builder()
                        .id(UUID.randomUUID().toString())
                        .user(user)
                        .items(new ArrayList<>())
                        .build()));
    }

    public Cart addItem(User user, CartItemDTO dto) {
        Cart cart = getOrCreateCartForUser(user);
        ProductVariant variant = productVariantRepository.findById(dto.variantId())
                .orElseThrow(() -> new RuntimeException("Variant not found"));

        Optional<CartItem> existingItem = cartItemRepository.findByCartIdAndVariantId(cart.getId(), dto.variantId());
        if (existingItem.isPresent()) {
            CartItem item = existingItem.get();
            item.setQuantity(item.getQuantity() + dto.quantity());
            cartItemRepository.save(item);
        } else {
            CartItem item = CartItem.builder()
                    .id(UUID.randomUUID().toString())
                    .cart(cart)
                    .variant(variant)
                    .quantity(dto.quantity())
                    .build();
            cartItemRepository.save(item); // important
            cart.getItems().add(item);
        }

        return cartRepository.save(cart);
    }

    public Cart updateItem(User user, String variantId, int quantity) {
        Cart cart = getOrCreateCartForUser(user);
        CartItem item = cartItemRepository.findByCartIdAndVariantId(cart.getId(), variantId)
                .orElseThrow(() -> new RuntimeException("Item not found"));

        item.setQuantity(quantity);
        cartItemRepository.save(item);

        return cart;
    }

    public String removeItem(String cartItemId) {
        CartItem item = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new RuntimeException("Item not found"));

        cartItemRepository.delete(item);
        return "Deleted";
    }

    public Cart clearCart(User user) {
        Cart cart = getOrCreateCartForUser(user);
        cart.getItems().clear();
        return cartRepository.save(cart);
    }
}
