package com.example.catalog.product.service;

import com.example.catalog.product.entity.Product;
import com.example.catalog.product.repository.ProductRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service layer for managing products.
 */
@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Finds all products.
     * @return A list of all products.
     */
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    /**
     * Finds a product by its ID.
     * @param id The ID of the product.
     * @return An Optional containing the product if found, otherwise empty.
     */
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    /**
     * Creates a new product.
     * @param product The product to create.
     * @return The created product.
     */
    public Product save(Product product) {
        return productRepository.save(product);
    }

    /**
     * Deletes a product by its ID.
     * @param id The ID of the product to delete.
     */
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    /**
     * Searches for products based on a flexible Specification combining name, description,
     * and price range.
     *
     * @param searchQuery The keyword to search in name or description.
     * @param minPrice The minimum price for the search range.
     * @param maxPrice The maximum price for the search range.
     * @return A list of products matching the criteria.
     */
    public List<Product> searchProducts(String searchQuery, Double minPrice, Double maxPrice) {
        Specification<Product> spec = Specification.where(null);

        // Add search query criteria if present
        if (searchQuery != null && !searchQuery.trim().isEmpty()) {
            String likeQuery = "%" + searchQuery.trim().toLowerCase() + "%";
            // A specification that checks if name OR description contains the query string (case-insensitive)
            spec = spec.and((root, query, cb) ->
                    cb.or(
                            cb.like(cb.lower(root.get("name")), likeQuery),
                            cb.like(cb.lower(root.get("description")), likeQuery)
                    )
            );
        }

        // Add minimum price criteria if present
        if (minPrice != null) {
            spec = spec.and((root, query, cb) ->
                    cb.greaterThanOrEqualTo(root.get("price"), minPrice)
            );
        }

        // Add maximum price criteria if present
        if (maxPrice != null) {
            spec = spec.and((root, query, cb) ->
                    cb.lessThanOrEqualTo(root.get("price"), maxPrice)
            );
        }

        // Pass the dynamically built specification to the repository
        return productRepository.findAll(spec);
    }
}
