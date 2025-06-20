package com.aexils.pocspring.controller;

import com.aexils.pocspring.dto.CartDTO;
import com.aexils.pocspring.dto.CartItemDTO;
import com.aexils.pocspring.entity.Cart;
import com.aexils.pocspring.entity.User;
import com.aexils.pocspring.mapper.CartMapper;
import com.aexils.pocspring.repository.CartRepository;
import com.aexils.pocspring.service.CartService;
import com.aexils.pocspring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final UserService userService;
    private final CartRepository cartRepository;

    @GetMapping
    public CartDTO getCart(User user) {
        Cart cart = cartRepository.findByUserId(user.getId());
        return CartMapper.toDTO(cart); // static ou injecté, selon ton choix
    }

    @PostMapping("/items")
    public ResponseEntity<CartDTO> addItem(
            @RequestBody CartItemDTO dto,
            @AuthenticationPrincipal Jwt principal
    ) {
        User user = userService.findById(principal.getClaim("sub"));
        Cart cart = cartService.addItem(user, dto);
        return ResponseEntity.ok(CartMapper.toDTO(cart));
    }

    @PutMapping("/items/{variantId}")
    public ResponseEntity<CartDTO> updateItem(
            @AuthenticationPrincipal User user,
            @PathVariable String variantId,
            @RequestParam int quantity
    ) {
        Cart cart = cartService.updateItem(user, variantId, quantity);
        return ResponseEntity.ok(CartMapper.toDTO(cart));
    }

    @DeleteMapping("/items/{cartItemId}")
    public ResponseEntity<String> removeItem(
            @PathVariable("cartItemId") String cartItemId
    ) {
        cartService.removeItem(cartItemId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/clear")
    public ResponseEntity<Void> clearCart(@AuthenticationPrincipal User user) {
        cartService.clearCart(user);
        return ResponseEntity.noContent().build();
    }
}
