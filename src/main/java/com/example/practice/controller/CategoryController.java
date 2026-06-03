package com.example.practice.controller;

import com.example.practice.ApiResponse.ApiResponse;
import com.example.practice.dto.CategoryRequestDTO;
import com.example.practice.dto.CategoryResponseDTO;
import com.example.practice.repository.CategoryRepository;
import com.example.practice.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {
//    private final CategoryRepository categoryRepository;
    private final CategoryService categoryService;

    @PostMapping
    public CategoryResponseDTO createCategory(@Valid @RequestBody CategoryRequestDTO request){
        return categoryService.createCategory(request);
    }

    @GetMapping
    public List<CategoryResponseDTO> getAllCategory(){
        return categoryService.getAllCategory();
    }

    @GetMapping("/{id}")
    public CategoryResponseDTO getCategoryById( @PathVariable Long id){
        return categoryService.getCategoryById(id);
    }

    @DeleteMapping("/{id}")
    ApiResponse<Void> deleteCategoryById(@PathVariable Long id){
        categoryService.deleteCategoryById(id);
        return new ApiResponse<>(
                true,
                "DELETE SUCCESS .",
                null
        );
    }

    @PutMapping("/{id}")
    public CategoryResponseDTO updateCategoryById(@PathVariable  Long id , @Valid @RequestBody CategoryRequestDTO request){
        return categoryService.updateCategory(id, request);
    }


}
