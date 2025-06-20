package com.aexils.pocspring.service;

import com.aexils.pocspring.dto.ProductAttributeDTO;
import com.aexils.pocspring.entity.Product;
import com.aexils.pocspring.entity.ProductAttribute;
import com.aexils.pocspring.mapper.ProductAttributeMapper;
import com.aexils.pocspring.repository.ProductAttributeRepository;
import com.aexils.pocspring.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductAttributeService {

    private final ProductAttributeRepository attributeRepository;

    public List<ProductAttributeDTO> findByProduct(String productId) {
        return attributeRepository.findByProductId(productId)
                .stream().map(ProductAttributeMapper::toDTO).toList();
    }

    public void delete(String id) {
        attributeRepository.deleteById(id);
    }
}
