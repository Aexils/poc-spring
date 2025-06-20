package com.aexils.pocspring.controller.admin;

import com.aexils.pocspring.service.ProductAttributeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/products/{productId}/attributes")
@RequiredArgsConstructor
public class AdminProductAttributeController {

    private final ProductAttributeService service;

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}
