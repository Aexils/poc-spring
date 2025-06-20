package com.aexils.pocspring.service;

import com.aexils.pocspring.dto.ProductAttributeDTO;
import com.aexils.pocspring.dto.ProductDTO;
import com.aexils.pocspring.dto.ProductVariantDTO;
import com.aexils.pocspring.entity.*;
import com.aexils.pocspring.mapper.ProductAttributeMapper;
import com.aexils.pocspring.mapper.ProductMapper;
import com.aexils.pocspring.mapper.ProductVariantMapper;
import com.aexils.pocspring.repository.CategoryRepository;
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
    private final CategoryRepository categoryRepository;

    public List<ProductDTO> findAll() {
        return productRepository.findAll().stream()
                .map(ProductMapper::toDTO)
                .toList();
    }

    public ProductDTO findBySlug(String slug) {
        Product product = productRepository.findBySlug(slug)
                .orElseThrow(() -> new EntityNotFoundException("Produit introuvable"));
        return ProductMapper.toDTO(product);
    }

    public Integer getNumberOfProducts() {
        return productRepository.findAll().size();
    }

    @Transactional
    public ProductDTO create(ProductDTO dto) {
        Category category = categoryRepository.findById(dto.categoryId())
                .orElseThrow(() -> new EntityNotFoundException("Catégorie introuvable"));

        Product product = ProductMapper.fromDTO(dto, category);
        product = productRepository.save(product);

        return ProductMapper.toDTO(product);
    }

    @Transactional
    public ProductDTO update(String id, ProductDTO dto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produit introuvable"));

        Category category = categoryRepository.findById(dto.categoryId())
                .orElseThrow(() -> new EntityNotFoundException("Catégorie introuvable"));

        product = ProductMapper.updateFromDTO(product, dto, category);

        // Mise à jour ou création des attributs
        List<ProductAttribute> updatedAttributes = new ArrayList<>();
        for (ProductAttributeDTO attrDto : dto.attributes()) {
            Optional<ProductAttribute> existingAttr = product.getAttributes().stream()
                    .filter(a -> a.getId().equals(attrDto.id()))
                    .findFirst();

            if (existingAttr.isPresent()) {
                ProductAttribute attr = existingAttr.get();
                ProductAttribute updated = ProductAttributeMapper.updateFromDTO(attrDto, attr);
                updatedAttributes.add(updated);
            } else {
                ProductAttribute created = ProductAttributeMapper.fromDTO(attrDto, product);
                updatedAttributes.add(created);
            }
        }
        product.getAttributes().clear();
        product.getAttributes().addAll(updatedAttributes);

        // Mise à jour ou création des variantes
        List<ProductVariant> updatedVariants = new ArrayList<>();
        for (ProductVariantDTO variantDto : dto.variants()) {
            Optional<ProductVariant> existingVariant = product.getVariants().stream()
                    .filter(v -> v.getId().equals(variantDto.id()))
                    .findFirst();

            if (existingVariant.isPresent()) {
                ProductVariant variant = existingVariant.get();
                ProductVariant updated = ProductVariantMapper.updateFromDTO(variantDto, variant);
                updatedVariants.add(updated);
            } else {
                ProductVariant created = ProductVariantMapper.fromDTO(variantDto, product);
                updatedVariants.add(created);
            }
        }
        product.getVariants().clear();
        product.getVariants().addAll(updatedVariants);

        product = productRepository.save(product);

        return ProductMapper.toDTO(product);
    }

    public void delete(String id) {
        productRepository.deleteById(id);
    }
}
