package com.aexils.pocspring.controller.admin;

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

@RestController
@RequestMapping("/admin/products")
@RequiredArgsConstructor
public class AdminProductController {

    private final ProductService productService;
    private final UserService userService;

    private boolean isAdmin(Jwt principal) {
        if (principal == null) return false;
        User user = userService.findById(principal.getClaim("sub"));
        return user != null && user.getRole() == User.Role.ADMIN;
    }

    @PostMapping
    public ResponseEntity<ProductDTO> create(@RequestBody ProductDTO dto, @AuthenticationPrincipal Jwt principal) {
        if (!isAdmin(principal)) return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        ProductDTO created = productService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> update(@PathVariable("id") String id, @RequestBody ProductDTO dto, @AuthenticationPrincipal Jwt principal) {
        if (!isAdmin(principal)) return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        ProductDTO updated = productService.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String id, @AuthenticationPrincipal Jwt principal) {
        if (!isAdmin(principal)) return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        productService.delete(id);
        return ResponseEntity.ok().build();
    }
}
