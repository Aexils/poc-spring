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

    public ProductImageDTO toDTO(ProductImage image) {
        return new ProductImageDTO(
                image.getId(),
                image.getUrl(),
                image.isMain(),
                image.getProduct().getId()
        );
    }

    public ProductImage fromDTO(ProductImageDTO dto) {
        ProductImage image = new ProductImage();
        image.setId(dto.id());
        image.setUrl(dto.url());
        image.setMain(dto.isMain());
        image.setProduct(productRepository.findById(dto.productId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid product ID")));
        return image;
    }
}
