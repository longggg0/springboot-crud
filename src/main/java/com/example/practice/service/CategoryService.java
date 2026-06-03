package com.example.practice.service;

import com.example.practice.dto.CategoryRequestDTO;
import com.example.practice.dto.CategoryResponseDTO;

import java.util.List;

public interface CategoryService {
    CategoryResponseDTO createCategory(CategoryRequestDTO request);
    List<CategoryResponseDTO> getAllCategory();
    CategoryResponseDTO updateCategory(Long id , CategoryRequestDTO request);
    void deleteCategoryById(Long id );
    CategoryResponseDTO getCategoryById(Long id );
}
