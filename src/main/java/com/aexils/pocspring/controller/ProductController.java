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
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAll(@AuthenticationPrincipal Jwt principal) {
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/size")
    public ResponseEntity<Integer> GetUserFromJWT(@AuthenticationPrincipal Jwt principal) {

        Integer productSize = productService.getNumberOfProducts();

        return ResponseEntity.ok(productSize);
    }

    @GetMapping("/slug/{slug}")
    public ResponseEntity<ProductDTO> findBySlug(@PathVariable("slug") String slug, @AuthenticationPrincipal Jwt principal) {
        return ResponseEntity.ok(productService.findBySlug(slug));
    }
}
