package com.example.catalog.product.controller;

import com.example.catalog.product.dto.ProductDto;
import com.example.catalog.product.entity.Product;
import com.example.catalog.product.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing products.
 */
@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // --- CRUD Operations ---

    /**
     * READ: Retrieves all products.
     * HTTP Method: GET
     * Endpoint: /api/products
     */
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.findAll());
    }

    /**
     * READ: Retrieves a single product by its ID.
     * HTTP Method: GET
     * Endpoint: /api/products/{id}
     *
     * @param id The ID of the product.
     * @return The product or a 404 Not Found response.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return productService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * CREATE: Creates a new product.
     * HTTP Method: POST
     * Endpoint: /api/products
     *
     * @param productDto The product data to create.
     * @return The created product.
     */
    @PostMapping
    public ResponseEntity<Product> createProduct(@Valid @RequestBody ProductDto productDto) {
        Product newProduct = new Product();
        newProduct.setName(productDto.getName());
        newProduct.setDescription(productDto.getDescription());
        newProduct.setPrice(productDto.getPrice());
        Product savedProduct = productService.save(newProduct);
        return ResponseEntity.ok(savedProduct);
    }

    /**
     * UPDATE: Updates an existing product.
     * HTTP Method: PUT
     * Endpoint: /api/products/{id}
     *
     * @param id The ID of the product to update.
     * @param productDto The new product data.
     * @return The updated product or a 404 Not Found response.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @Valid @RequestBody ProductDto productDto) {
        return productService.findById(id).map(existingProduct -> {
            existingProduct.setName(productDto.getName());
            existingProduct.setDescription(productDto.getDescription());
            existingProduct.setPrice(productDto.getPrice());
            Product updatedProduct = productService.save(existingProduct);
            return ResponseEntity.ok(updatedProduct);
        }).orElse(ResponseEntity.notFound().build());
    }

    /**
     * DELETE: Deletes a product by its ID.
     * HTTP Method: DELETE
     * Endpoint: /api/products/{id}
     *
     * @param id The ID of the product to delete.
     * @return A 200 OK response on success.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}