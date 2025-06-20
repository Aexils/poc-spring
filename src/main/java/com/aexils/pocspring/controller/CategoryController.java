package com.aexils.pocspring.controller;

import com.aexils.pocspring.dto.CategoryDTO;
import com.aexils.pocspring.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public List<CategoryDTO> getAll() {
        return categoryService.findAll();
    }

    @GetMapping("/{id}")
    public CategoryDTO getOne(@PathVariable String id) {
        return categoryService.findById(id);
    }
}
