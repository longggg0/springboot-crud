package com.example.practice.repository;

import com.example.practice.entity.CategoryEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

//    Sort id(Long id);
//
//    List<CategoryEntity> findAll(Sort.Direction direction, String id);
}
