package com.example.catalog.product.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Data Transfer Object for Product.
 * The DTO is used to transfer data between the client and the server,
 * and it includes validation annotations for incoming requests.
 */
@Data // Lombok annotation for getters, setters, etc.
@NoArgsConstructor // Lombok annotation for a no-args constructor
public class ProductDto {
    @NotBlank(message = "Product name is required.")
    @Size(max = 255, message = "Product name cannot exceed 255 characters.")
    private String name;

    @Size(max = 1000, message = "Description cannot exceed 1000 characters.")
    private String description;

    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0.")
    private BigDecimal price;
}