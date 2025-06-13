package com.aexils.pocspring.service;

import com.aexils.pocspring.dto.ProductImageDTO;
import com.aexils.pocspring.mapper.ProductImageMapper;
import com.aexils.pocspring.repository.ProductImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductImageService {

    private final ProductImageRepository imageRepository;
    private final ProductImageMapper mapper;

    public List<ProductImageDTO> findByProduct(String productId) {
        return imageRepository.findByProductId(productId)
                .stream().map(mapper::toDTO).toList();
    }

    public ProductImageDTO create(ProductImageDTO dto) {
        return mapper.toDTO(imageRepository.save(mapper.fromDTO(dto)));
    }

    public void delete(String id) {
        imageRepository.deleteById(id);
    }
}
