package com.aexils.pocspring.service;

import com.aexils.pocspring.dto.ProductAttributeDTO;
import com.aexils.pocspring.dto.ProductDTO;
import com.aexils.pocspring.dto.ProductVariantDTO;
import com.aexils.pocspring.entity.Product;
import com.aexils.pocspring.entity.ProductAttribute;
import com.aexils.pocspring.entity.ProductVariant;
import com.aexils.pocspring.mapper.ProductAttributeMapper;
import com.aexils.pocspring.mapper.ProductMapper;
import com.aexils.pocspring.mapper.ProductVariantMapper;
import com.aexils.pocspring.repository.ProductAttributeRepository;
import com.aexils.pocspring.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductAttributeRepository productAttributeRepository;
    private final ProductMapper productMapper;
    private final ProductAttributeMapper productAttributeMapper;
    private final ProductVariantMapper productVariantMapper;

    public List<ProductDTO> findAll() {
        return productRepository.findAll().stream()
                .map(productMapper::toDTO)
                .toList();
    }

    public ProductDTO findBySlug(String slug) {
        Product product = productRepository.findBySlug(slug)
                .orElseThrow(() -> new EntityNotFoundException("Produit introuvable"));
        return productMapper.toDTO(product);
    }

    public Integer getNumberOfProducts() {
        return productRepository.findAll().size();
    }

    @Transactional
    public ProductDTO create(ProductDTO dto) {
        Product product = productMapper.fromDTO(dto);
        product = productRepository.save(product);

        for (ProductAttributeDTO attrDto : dto.attributes()) {
            ProductAttribute productAttribute = productAttributeMapper.fromDTO(attrDto, product);
            product.getAttributes().add(productAttribute);
        }

        for (ProductVariantDTO variantDto : dto.variants()) {
            ProductVariant variant = productVariantMapper.fromDTO(variantDto, product);
            product.getVariants().add(variant);
        }

        return productMapper.toDTO(product);
    }

    @Transactional
    public ProductDTO update(String id, ProductDTO dto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produit introuvable"));

        product = productMapper.updateFromDTO(product, dto);

        List<ProductAttribute> updatedAttributes = new ArrayList<>();
        for (ProductAttributeDTO attrDto : dto.attributes()) {
            Optional<ProductAttribute> existingAttr = product.getAttributes().stream()
                    .filter(a -> a.getId().equals(attrDto.id()))
                    .findFirst();

            if (existingAttr.isPresent()) {
                ProductAttribute attr = existingAttr.get();
                ProductAttribute productAttribute = productAttributeMapper.updateFromDTO(attrDto, attr);
                updatedAttributes.add(productAttribute);
            } else {
                ProductAttribute newAttr = productAttributeMapper.fromDTO(attrDto, product);
                updatedAttributes.add(newAttr);
            }
        }
        product.getAttributes().clear();
        product.getAttributes().addAll(updatedAttributes);

        List<ProductVariant> updatedVariants = new ArrayList<>();
        for (ProductVariantDTO variantDto : dto.variants()) {
            Optional<ProductVariant> existingVariant = product.getVariants().stream()
                    .filter(v -> v.getId().equals(variantDto.id()))
                    .findFirst();

            if (existingVariant.isPresent()) {
                ProductVariant variant = existingVariant.get();
                ProductVariant productVariant = productVariantMapper.updateFromDTO(variantDto, variant);
                updatedVariants.add(productVariant);
            } else {
                ProductVariant newVariant = productVariantMapper.fromDTO(variantDto, product);
                updatedVariants.add(newVariant);
            }
        }
        product.getVariants().clear();
        product.getVariants().addAll(updatedVariants);

        return productMapper.toDTO(productRepository.save(product));
    }

    public void delete(String id) {
        productRepository.deleteById(id);
    }
}