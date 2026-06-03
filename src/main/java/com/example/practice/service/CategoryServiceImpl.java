package com.example.practice.service;

import com.example.practice.dto.CategoryRequestDTO;
import com.example.practice.dto.CategoryResponseDTO;
import com.example.practice.entity.CategoryEntity;
import com.example.practice.exception.BusinessException;
import com.example.practice.exception.ErrorCode;
import com.example.practice.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;

    @Override
    public CategoryResponseDTO createCategory(CategoryRequestDTO request) {
        CategoryEntity category = new CategoryEntity();
        category.setName(request.getName());
        CategoryEntity savedCategory = categoryRepository.save(category);

        return mapToCategoryResponse(savedCategory);
    }

    @Override
    public List<CategoryResponseDTO> getAllCategory(){
        List<CategoryEntity> categories = categoryRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        return categories.stream()
                .map(category -> new CategoryResponseDTO(
                        category.getId(),
                        category.getName(),
                        category.getCreatedAt(),
                        category.getUpdatedAt()
                ))
                .toList();
    }

    @Override
    public CategoryResponseDTO updateCategory(Long id , CategoryRequestDTO request ){

        CategoryEntity category = categoryRepository.findById(id)
                .orElseThrow(() -> new BusinessException(
                        ErrorCode.RESOURCE_NOT_FOUND,"Category not found ID : " + id
                ));
        category.setName(request.getName());

        CategoryEntity res = categoryRepository.save(category);

        return mapToCategoryResponse(res);

    }


    @Override
    public CategoryResponseDTO getCategoryById(Long id ){

        CategoryEntity category = categoryRepository.findById(id)
                .orElseThrow(() -> new BusinessException(

                        ErrorCode.RESOURCE_NOT_FOUND,
                        "Category Not Found ID : " + id

                ));

        return mapToCategoryResponse(category);

    }

   public void deleteCategoryById(Long id ){

        if(!categoryRepository.existsById(id)){
            throw new BusinessException(
                    ErrorCode.RESOURCE_NOT_FOUND,
                    "Category Not Found ID : " + id);
        }
        categoryRepository.deleteById(id);
   }


    private CategoryResponseDTO mapToCategoryResponse(CategoryEntity category){
        return new CategoryResponseDTO(
                category.getId(),
                category.getName(),
                category.getCreatedAt(),
                category.getUpdatedAt()
        );
    }


}
