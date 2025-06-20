package com.aexils.pocspring.mapper;

import com.aexils.pocspring.dto.ProductAttributeDTO;
import com.aexils.pocspring.entity.Product;
import com.aexils.pocspring.entity.ProductAttribute;

public class ProductAttributeMapper {

    public static ProductAttributeDTO toDTO(ProductAttribute attr) {
        return new ProductAttributeDTO(
                attr.getId(),
                attr.getName(),
                attr.getValue(),
                attr.getType(),
                attr.getProduct() != null ? attr.getProduct().getId() : null
        );
    }

    public static ProductAttribute fromDTO(ProductAttributeDTO dto, Product product) {
        ProductAttribute attribute = new ProductAttribute();
        attribute.setId(dto.id()); // facultatif : seulement si tu veux rééditer un existant
        attribute.setProduct(product);
        attribute.setName(dto.name());
        attribute.setValue(dto.value());
        attribute.setType(dto.type());
        return attribute;
    }

    public static ProductAttribute updateFromDTO(ProductAttributeDTO dto, ProductAttribute existing) {
        existing.setName(dto.name());
        existing.setValue(dto.value());
        existing.setType(dto.type());
        return existing;
    }
}
