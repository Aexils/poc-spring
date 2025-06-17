package com.aexils.pocspring.mapper;

import com.aexils.pocspring.dto.ProductImageDTO;
import com.aexils.pocspring.entity.ProductImage;
import com.aexils.pocspring.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductImageMapper {

    private final ProductRepository productRepository;

    public ProductImageDTO toDto(ProductImage image) {
        if (image == null) return null;

        return new ProductImageDTO(
                image.getId(),
                image.getUrl(),
                image.isMain(),
                image.getProduct() != null ? image.getProduct().getId() : null
        );
    }
}
