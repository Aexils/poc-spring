package com.aexils.pocspring.controller;

import com.aexils.pocspring.dto.ProductDTO;
import com.aexils.pocspring.entity.User;
import com.aexils.pocspring.service.ProductService;
import com.aexils.pocspring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final UserService userService;

    private boolean isAdmin(Jwt principal) {
        if (principal == null) return false;
        User user = userService.findById(principal.getClaim("sub"));
        return user != null && user.getRole() == User.Role.ADMIN;
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAll(@AuthenticationPrincipal Jwt principal) {
        if (!isAdmin(principal)) return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getOne(@PathVariable("id") String id, @AuthenticationPrincipal Jwt principal) {
        if (!isAdmin(principal)) return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        return ResponseEntity.ok(productService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ProductDTO> create(@AuthenticationPrincipal Jwt principal, @RequestBody ProductDTO dto) {
        if (!isAdmin(principal)) return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        return ResponseEntity.ok(productService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> update(@PathVariable("id") String id, @RequestBody ProductDTO dto, @AuthenticationPrincipal Jwt principal) {
        if (!isAdmin(principal)) return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        return ResponseEntity.ok(productService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String id, @AuthenticationPrincipal Jwt principal) {
        if (!isAdmin(principal)) return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        productService.delete(id);
        return ResponseEntity.ok().build();
    }
}
