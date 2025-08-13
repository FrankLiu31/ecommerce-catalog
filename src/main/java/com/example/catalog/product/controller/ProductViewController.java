package com.example.catalog.product.controller;

import com.example.catalog.product.entity.Product;
import com.example.catalog.product.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;


/**
 * Controller for rendering the Thymeleaf UI.
 */
@Controller
@RequestMapping("/")
public class ProductViewController {
    private final ProductService productService;

    public ProductViewController(ProductService productService) {
        this.productService = productService;
    }


    /**
     * Renders the main product catalog page with a list of all products.
     * Supports optional search functionality via query parameters for text and price range.
     */
    @GetMapping("/products/main")
    public String showAddProductMain(Model model) {
        return "product-main"; // Refers to the Thymeleaf template products.html
    }

    /**
     * Renders the form to add a new product.
     * This method handles the GET request to the /products/new endpoint.
     */
    @GetMapping("/products/new")
    public String showAddProductForm(Model model) {
        // Create an empty Product object to bind with the form
        model.addAttribute("productForm", new Product());
        System.out.println("/products/new-->controller");
        return "product-form"; // Refers to the new Thymeleaf template product-form.html
    }

    /**
     * Renders the form to edit an existing product.
     * This method handles the GET request to the /products/edit/{id} endpoint.
     */
    @GetMapping("/products/edit/{id}")
    public String showEditProductForm(@PathVariable Long id,
                                      @RequestParam(required = false) String name,
                                      @RequestParam(required = false) String description,
                                      @RequestParam(required = false) String price,
                                      Model model) {

        Product product =  new Product();
        double parsedPrice = Double.parseDouble(price);
        if (parsedPrice >= 0) { // Basic validation
            product.setId(id);
            product.setName(name);
            product.setDescription(description);
            product.setPrice(BigDecimal.valueOf(parsedPrice));
        } else {
            throw new NumberFormatException("Price must be non-negative");
        }
        model.addAttribute("productForm", product);
        return "product-form";
    }
}