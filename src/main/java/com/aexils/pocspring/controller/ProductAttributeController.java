package com.aexils.pocspring.controller;

import com.aexils.pocspring.dto.ProductAttributeDTO;
import com.aexils.pocspring.service.ProductAttributeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/products/{productId}/attributes")
@RequiredArgsConstructor
public class ProductAttributeController {

    private final ProductAttributeService service;

    @GetMapping
    public List<ProductAttributeDTO> getAll(@PathVariable String productId) {
        return service.findByProduct(productId);
    }

    @PostMapping
    public ProductAttributeDTO create(@PathVariable String productId,
                                      @RequestBody ProductAttributeDTO dto) {
        return service.create(new ProductAttributeDTO(
                null,
                dto.name(),
                dto.value(),
                dto.type(),
                productId
        ));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}
