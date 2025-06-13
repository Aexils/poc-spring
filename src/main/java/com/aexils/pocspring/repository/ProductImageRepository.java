package com.aexils.pocspring.repository;

import com.aexils.pocspring.entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductImageRepository extends JpaRepository<ProductImage,String> {
    List<ProductImage> findByProductId(String productId);
}
