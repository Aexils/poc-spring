package com.aexils.pocspring.controller;

import com.aexils.pocspring.dto.ProductImageDTO;
import com.aexils.pocspring.service.ProductImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/products/{productId}/images")
@RequiredArgsConstructor
public class ProductImageController {

    private final ProductImageService service;

    @GetMapping
    public List<ProductImageDTO> getAll(@PathVariable String productId) {
        return service.findByProduct(productId);
    }

    @PostMapping
    public ProductImageDTO create(@PathVariable String productId,
                                  @RequestBody ProductImageDTO dto) {
        return service.create(new ProductImageDTO(
                null,
                dto.url(),
                dto.isMain(),
                productId
        ));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}
