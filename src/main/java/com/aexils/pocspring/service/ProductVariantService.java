package com.aexils.pocspring.service;

import com.aexils.pocspring.dto.ProductVariantDTO;
import com.aexils.pocspring.entity.ProductVariant;
import com.aexils.pocspring.mapper.ProductVariantMapper;
import com.aexils.pocspring.repository.ProductVariantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductVariantService {

    private final ProductVariantRepository repository;

    public List<ProductVariantDTO> findByProductId(String productId) {
        return repository.findByProductId(productId)
                .stream().map(ProductVariantMapper::toDTO).toList();
    }

    public void delete(String id) {
        repository.deleteById(id);
    }
}
