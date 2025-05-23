package main.java.com.example.productservice.service;

import com.example.productservice.dto.ProductRequest;
import com.example.productservice.dto.ProductResponse;
import java.util.List;

public interface ProductService {
    ProductResponse createProduct(ProductRequest productRequest);
    ProductResponse getProductById(Long id);
    List<ProductResponse> getAllProducts();
    ProductResponse updateProduct(Long id, ProductRequest productRequest);
    void deleteProduct(Long id);
    List<ProductResponse> getProductsByUser(Long userId);
    List<ProductResponse> getProductsByCategory(String category);
    ProductResponse updateProductStatus(Long id, String status);
}