package com.aexils.pocspring.mapper;

import com.aexils.pocspring.dto.ProductAttributeDTO;
import com.aexils.pocspring.entity.ProductAttribute;
import com.aexils.pocspring.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductAttributeMapper {

    private final ProductRepository productRepository;

    public ProductAttributeDTO toDTO(ProductAttribute attr) {
        return new ProductAttributeDTO(
                attr.getId(),
                attr.getName(),
                attr.getValue(),
                attr.getType(),
                attr.getProduct().getId()
        );
    }

    public ProductAttribute fromDTO(ProductAttributeDTO dto) {
        ProductAttribute attr = new ProductAttribute();
        attr.setId(dto.id());
        attr.setName(dto.name());
        attr.setValue(dto.value());
        attr.setType(dto.type());
        attr.setProduct(productRepository.findById(dto.productId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid product ID")));
        return attr;
    }
}
