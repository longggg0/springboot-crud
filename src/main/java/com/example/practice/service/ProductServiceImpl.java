package com.example.practice.service;

import com.example.practice.dto.ProductRequestDTO;
import com.example.practice.dto.ProductResponseDTO;
import com.example.practice.entity.ProductEntity;
import com.example.practice.exception.ResourceNotFoundException;
import com.example.practice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public ProductResponseDTO createProduct(ProductRequestDTO request) {
        ProductEntity product = new ProductEntity();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantity());
        ProductEntity savedProduct = productRepository.save(product);


        log.info(String.format("Created At: %s", savedProduct.getCreatedAt()));
        log.info(String.format("Updated At: %s", savedProduct.getUpdatedAt()));


        return mapToProductResponse(savedProduct);
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
        ProductEntity product = findProductById(id);
        return mapToProductResponse(product);
    }

    @Override
    public ProductResponseDTO updateProduct(Long id, ProductRequestDTO request) {
        ProductEntity product = findProductById(id);

        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantity());

        ProductEntity updatedProduct = productRepository.save(product);

        return mapToProductResponse(updatedProduct);
    }

    @Override
    public void deleteProduct(Long id) {
        ProductEntity product = findProductById(id);
        productRepository.delete(product);
    }

    private ProductEntity findProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + id));
    }

    private ProductResponseDTO mapToProductResponse(ProductEntity product) {
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