package com.aexils.pocspring.service;

import com.aexils.pocspring.dto.ProductAttributeDTO;
import com.aexils.pocspring.mapper.ProductAttributeMapper;
import com.aexils.pocspring.repository.ProductAttributeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductAttributeService {

    private final ProductAttributeRepository attributeRepository;
    private final ProductAttributeMapper mapper;

    public List<ProductAttributeDTO> findByProduct(String productId) {
        return attributeRepository.findByProductId(productId)
                .stream().map(mapper::toDTO).toList();
    }

    public ProductAttributeDTO create(ProductAttributeDTO dto) {
        return mapper.toDTO(attributeRepository.save(mapper.fromDTO(dto)));
    }

    public void delete(String id) {
        attributeRepository.deleteById(id);
    }
}
