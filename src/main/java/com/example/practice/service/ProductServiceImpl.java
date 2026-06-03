package com.example.practice.service;

import com.example.practice.dto.ProductRequestDTO;
import com.example.practice.dto.ProductResponseDTO;
import com.example.practice.entity.ProductEntity;
import com.example.practice.exception.BusinessException;
import com.example.practice.exception.ErrorCode;
import com.example.practice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public ProductResponseDTO createProduct(ProductRequestDTO request) {
        ProductEntity product = new ProductEntity();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantity());

        return mapToProductResponse(productRepository.save(product));
    }

    @Override
    public List<ProductResponseDTO> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(this::mapToProductResponse)
                .toList();
    }

    @Override
    public ProductResponseDTO getProductById(Long id) {
        return productRepository.findById(id)
                .map(this::mapToProductResponse)
                .orElseThrow(() -> new BusinessException(
                        ErrorCode.RESOURCE_NOT_FOUND,
                        "Product not found with ID: " + id
                ));
    }

    @Override
    public ProductResponseDTO updateProduct(Long id, ProductRequestDTO request) {
        ProductEntity product = productRepository.findById(id)
                .orElseThrow(() -> new BusinessException(
                        ErrorCode.RESOURCE_NOT_FOUND,
                        "Product not found with ID: " + id
                ));

        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantity());

        return mapToProductResponse(productRepository.save(product));
    }

    @Override
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new BusinessException(
                    ErrorCode.RESOURCE_NOT_FOUND,
                    "Product not found with ID: " + id
            );
        }
        productRepository.deleteById(id);
    }

    private ProductResponseDTO  mapToProductResponse(ProductEntity product) {
        return new ProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getQuantity(),
                product.getCreatedAt(),
                product.getUpdatedAt()
        );
    }
}
