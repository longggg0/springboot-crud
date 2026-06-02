package com.example.practice.mapper;

import com.example.practice.dto.ProductRequestDTO;
import com.example.practice.dto.ProductResponseDTO;
import com.example.practice.entity.ProductEntity;

public interface ProductMapper {

    ProductResponseDTO toDto(ProductRequestDTO productRequestDTO);

    ProductRequestDTO toEntity(ProductEntity productEntity);
}
