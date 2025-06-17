package com.aexils.pocspring.controller;

import com.aexils.pocspring.dto.ProductImageDTO;
import com.aexils.pocspring.entity.ProductImage;
import com.aexils.pocspring.entity.User;
import com.aexils.pocspring.service.ProductImageService;
import com.aexils.pocspring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("admin/products")
@RequiredArgsConstructor
public class ProductImageController {

    private final ProductImageService productImageService;
    private final UserService userService;

    private boolean isAdmin(Jwt principal) {
        if (principal == null) return false;
        User user = userService.findById(principal.getClaim("sub"));
        return user != null && user.getRole() == User.Role.ADMIN;
    }

    @GetMapping("/{id}/images")
    public List<ProductImage> getImages(@PathVariable String id) {
        return productImageService.getImagesByProductId(id);
    }

    @PostMapping(
            value = "/{productId}/images",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<ProductImageDTO> uploadImage(
            @PathVariable("productId") String productId,
            @RequestParam("file") MultipartFile file,
            @RequestParam("isMain") boolean isMain
    ) throws IOException {
        ProductImageDTO image = productImageService.addImage(productId, file, isMain);
        return ResponseEntity.status(HttpStatus.CREATED).body(image);
    }

    @DeleteMapping("/images/{imageId}")
    public ResponseEntity<Object> deleteImage(@PathVariable("imageId") String imageId, @AuthenticationPrincipal Jwt principal) {
        if (!isAdmin(principal)) return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        productImageService.deleteImage(imageId);
        return null;
    }

    @PutMapping("/{productId}/images/{imageId}/main")
    public ResponseEntity<Object> setMainImage(@PathVariable("productId") String productId, @PathVariable("imageId") String imageId, @AuthenticationPrincipal Jwt principal) {
        if (!isAdmin(principal)) return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        productImageService.setMainImage(productId, imageId);
        return ResponseEntity.noContent().build();
    }
}
