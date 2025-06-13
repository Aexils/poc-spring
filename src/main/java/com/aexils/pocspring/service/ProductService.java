package com.aexils.pocspring.service;

import com.aexils.pocspring.dto.ProductDTO;
import com.aexils.pocspring.entity.Product;
import com.aexils.pocspring.mapper.ProductMapper;
import com.aexils.pocspring.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public List<ProductDTO> findAll() {
        return productRepository.findAll().stream()
                .map(productMapper::toDTO)
                .toList();
    }

    public ProductDTO findById(String id) {
        return productRepository.findById(id)
                .map(productMapper::toDTO)
                .orElseThrow();
    }

    public ProductDTO create(ProductDTO dto) {
        Product product = productMapper.fromDTO(dto);
        return productMapper.toDTO(productRepository.save(product));

    }

    public ProductDTO update(String id, ProductDTO dto) {
        Product product = productRepository.findById(id).orElseThrow();
        productMapper.updateFromDTO(product, dto);
        return productMapper.toDTO(productRepository.save(product));
    }

    public void delete(String id) {
        productRepository.deleteById(id);
    }
}
