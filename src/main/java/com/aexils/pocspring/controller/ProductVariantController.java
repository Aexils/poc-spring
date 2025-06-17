package com.aexils.pocspring.controller;

import com.aexils.pocspring.dto.ProductVariantDTO;
import com.aexils.pocspring.service.ProductVariantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/products/{productId}/variants")
@RequiredArgsConstructor
public class ProductVariantController {

    private final ProductVariantService service;

    @GetMapping
    public List<ProductVariantDTO> getAll(@PathVariable String productId) {
        return service.findByProductId(productId);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}
