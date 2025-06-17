package com.aexils.pocspring.controller;

import com.aexils.pocspring.service.AttributeTemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class AttributeTemplateController {

    private final AttributeTemplateService attributeTemplateService;

    @GetMapping("/{categoryName}/attribute-template")
    public List<Map<String, Object>> getAttributesForCategory(@PathVariable("categoryName") String categoryName) {
        return attributeTemplateService.getAttributesForCategory(categoryName);
    }

    @GetMapping("/attribute-templates")
    public Map<String, List<Map<String, Object>>> getAllTemplates() {
        return attributeTemplateService.getAllTemplates();
    }
}
