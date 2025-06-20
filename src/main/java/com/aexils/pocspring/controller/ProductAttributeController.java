package com.aexils.pocspring.controller;

import com.aexils.pocspring.dto.ProductAttributeDTO;
import com.aexils.pocspring.service.ProductAttributeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products/{productId}/attributes")
@RequiredArgsConstructor
public class ProductAttributeController {

    private final ProductAttributeService service;

    @GetMapping
    public List<ProductAttributeDTO> getAll(@PathVariable String productId) {
        return service.findByProduct(productId);
    }
}
