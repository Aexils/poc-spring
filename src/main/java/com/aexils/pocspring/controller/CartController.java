package com.aexils.pocspring.controller;

import com.aexils.pocspring.dto.CartDTO;
import com.aexils.pocspring.dto.CartItemDTO;
import com.aexils.pocspring.entity.Cart;
import com.aexils.pocspring.entity.User;
import com.aexils.pocspring.mapper.CartMapper;
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
    private final CartMapper cartMapper;
    private final UserService userService;

    @GetMapping
    public ResponseEntity<CartDTO> getCart(@AuthenticationPrincipal Jwt principal) {
        User user = userService.findById(principal.getClaim("sub"));
        Cart cart = cartService.getOrCreateCartForUser(user);
        return ResponseEntity.ok(cartMapper.toDTO(cart));
    }

    @PostMapping("/items")
    public ResponseEntity<CartDTO> addItem(
            @RequestBody CartItemDTO dto,
            @AuthenticationPrincipal Jwt principal
    ) {
        User user = userService.findById(principal.getClaim("sub"));
        Cart cart = cartService.addItem(user, dto);
        return ResponseEntity.ok(cartMapper.toDTO(cart));
    }

    @PutMapping("/items/{variantId}")
    public ResponseEntity<CartDTO> updateItem(
            @AuthenticationPrincipal User user,
            @PathVariable String variantId,
            @RequestParam int quantity
    ) {
        Cart cart = cartService.updateItem(user, variantId, quantity);
        return ResponseEntity.ok(cartMapper.toDTO(cart));
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
