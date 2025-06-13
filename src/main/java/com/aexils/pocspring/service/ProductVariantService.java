package com.aexils.pocspring.service;

import com.aexils.pocspring.dto.ProductVariantDTO;
import com.aexils.pocspring.mapper.ProductVariantMapper;
import com.aexils.pocspring.repository.ProductVariantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductVariantService {

    private final ProductVariantRepository repository;
    private final ProductVariantMapper mapper;

    public List<ProductVariantDTO> findByProduct(String productId) {
        return repository.findByProductId(productId)
                .stream().map(mapper::toDTO).toList();
    }

    public ProductVariantDTO create(ProductVariantDTO dto) {
        return mapper.toDTO(repository.save(mapper.fromDTO(dto)));
    }

    public void delete(String id) {
        repository.deleteById(id);
    }
}
