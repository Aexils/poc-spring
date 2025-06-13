package com.aexils.pocspring.service;

import com.aexils.pocspring.dto.CategoryDTO;
import com.aexils.pocspring.entity.Category;
import com.aexils.pocspring.mapper.CategoryMapper;
import com.aexils.pocspring.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public List<CategoryDTO> findAll() {
        return categoryRepository.findAll().stream()
                .map(categoryMapper::toDTO)
                .toList();
    }

    public CategoryDTO findById(String id) {
        return categoryMapper.toDTO(
                categoryRepository.findById(id).orElseThrow()
        );
    }

    public CategoryDTO create(CategoryDTO dto) {
        Category category = categoryMapper.fromDTO(dto);
        return categoryMapper.toDTO(categoryRepository.save(category));
    }

    public CategoryDTO update(String id, CategoryDTO dto) {
        Category category = categoryRepository.findById(id).orElseThrow();
        categoryMapper.updateFromDTO(category, dto);
        return categoryMapper.toDTO(categoryRepository.save(category));
    }

    public void delete(String id) {
        categoryRepository.deleteById(id);
    }
}
