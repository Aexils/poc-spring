package com.aexils.pocspring.repository;

import com.aexils.pocspring.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, String> {
}
