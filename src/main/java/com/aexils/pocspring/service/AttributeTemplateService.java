package com.aexils.pocspring.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class AttributeTemplateService {

    private Map<String, List<Map<String, Object>>> templates;

    @PostConstruct
    public void init() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            templates = mapper.readValue(
                    new ClassPathResource("attribute-template.json").getInputStream(),
                    new TypeReference<>() {}
            );
        } catch (IOException e) {
            log.error("Erreur chargement template attributs", e);
        }
    }

    public List<Map<String, Object>> getAttributesForCategory(String categoryName) {
        return templates.getOrDefault(categoryName, List.of());
    }

    public Map<String, List<Map<String, Object>>> getAllTemplates() {
        return templates;
    }
}
