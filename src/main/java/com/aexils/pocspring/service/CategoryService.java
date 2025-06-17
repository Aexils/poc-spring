package com.aexils.pocspring.service;

import com.aexils.pocspring.dto.CategoryDTO;
import com.aexils.pocspring.entity.Category;
import com.aexils.pocspring.mapper.CategoryMapper;
import com.aexils.pocspring.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public List<CategoryDTO> findAll() {
        return categoryRepository.findAll().stream()
                .map(categoryMapper::toDTO)
                .sorted((c1, c2) -> {
                    String name1 = Optional.ofNullable(c1.name()).orElse("");
                    String name2 = Optional.ofNullable(c2.name()).orElse("");

                    if ("Toutes catégories".equalsIgnoreCase(name1)) return -1;
                    if ("Toutes catégories".equalsIgnoreCase(name2)) return 1;

                    return name1.compareToIgnoreCase(name2);
                })
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
