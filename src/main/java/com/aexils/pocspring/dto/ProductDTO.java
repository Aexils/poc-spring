package com.aexils.pocspring.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;

public record ProductDTO(
        @JsonProperty("id") String id,
        @JsonProperty("name") String name,
        @JsonProperty("slug") String slug,
        @JsonProperty("description") String description,
        @JsonProperty("price") BigDecimal price,
        @JsonProperty("active") boolean active,
        @JsonProperty("categoryId") String categoryId,
        @JsonProperty("categoryName") String categoryName
) {}
