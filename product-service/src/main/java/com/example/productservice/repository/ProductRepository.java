package com.example.productservice.repository;

import com.example.productservice.model.Product;
import com.example.productservice.model.ProductStatus;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByUserId(Long userId);
    List<Product> findByCategory(String category);
    List<Product> findByStatusAndVisible(ProductStatus status, Boolean visible);
}