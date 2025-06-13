package com.aexils.pocspring.repository;

import com.aexils.pocspring.entity.ProductAttribute;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductAttributeRepository extends JpaRepository<ProductAttribute, String> {

    List<ProductAttribute> findByProductId(String productId);
}
