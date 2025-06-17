package com.aexils.pocspring.mapper;

import com.aexils.pocspring.dto.ProductAttributeDTO;
import com.aexils.pocspring.entity.Product;
import com.aexils.pocspring.entity.ProductAttribute;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductAttributeMapper {

    public ProductAttributeDTO toDTO(ProductAttribute attr) {
        return new ProductAttributeDTO(
                attr.getId(),
                attr.getName(),
                attr.getValue(),
                attr.getType(),
                attr.getProduct() != null ? attr.getProduct().getId() : null
        );
    }

    public ProductAttribute fromDTO(ProductAttributeDTO dto, Product product) {
            ProductAttribute productAttribute = new ProductAttribute();
            productAttribute.setProduct(product);
            productAttribute.setName(dto.name());
            productAttribute.setValue(dto.value());
            productAttribute.setType(dto.type());

        return productAttribute;
    }

    public ProductAttribute updateFromDTO(ProductAttributeDTO dto, ProductAttribute productAttribute) {
        productAttribute.setName(dto.name());
        productAttribute.setValue(dto.value());
        productAttribute.setType(dto.type());

        return productAttribute;
    }
}
