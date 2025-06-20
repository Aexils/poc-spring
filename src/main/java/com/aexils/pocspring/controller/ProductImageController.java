package com.aexils.pocspring.controller;

import com.aexils.pocspring.entity.ProductImage;
import com.aexils.pocspring.service.ProductImageService;
import com.aexils.pocspring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductImageController {

    private final ProductImageService productImageService;

    @GetMapping("/{id}/images")
    public List<ProductImage> getImages(@PathVariable String id) {
        return productImageService.getImagesByProductId(id);
    }
}
