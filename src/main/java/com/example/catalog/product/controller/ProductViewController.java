package com.example.catalog.product.controller;

import com.example.catalog.common.exception.NotFoundException;
import com.example.catalog.product.entity.Product;
import com.example.catalog.product.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
    public String viewProductCatalog(
            @RequestParam(value = "searchQuery", required = false) String searchQuery,
            @RequestParam(value = "minPrice", required = false) Double minPrice,
            @RequestParam(value = "maxPrice", required = false) Double maxPrice,
            Model model) {

        List<Product> products = productService.searchProducts(searchQuery, minPrice, maxPrice);

        model.addAttribute("products", products);
        model.addAttribute("searchQuery", searchQuery);
        model.addAttribute("minPrice", minPrice);
        model.addAttribute("maxPrice", maxPrice);
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
        return "product-form"; // Refers to the new Thymeleaf template product-form.html
    }

    /**
     * Handles the form submission to add or update a product.
     * This method handles the POST request to the /products endpoint.
     */
    @PostMapping("/products")
    public String saveOrUpdateProduct(@Valid @ModelAttribute("productForm") Product product, BindingResult result) {
        // Validate the incoming product object
        if (result.hasErrors()) {
            return "product-form"; // If there are validation errors, return to the form page
        }
        productService.save(product);
        // Redirect to the main product catalog page after successful save
        return "redirect:/products/main";
    }

    /**
     * Renders the form to edit an existing product.
     * This method handles the GET request to the /products/edit/{id} endpoint.
     */
    @GetMapping("/products/edit/{id}")
    public String showEditProductForm(@PathVariable Long id, Model model) {
        Product product = productService.findById(id)
                .orElseThrow(() -> new NotFoundException("Product with ID " + id + " not found."));

        model.addAttribute("productForm", product);
        return "product-form";
    }

    /**
     * Handles the deletion of a product.
     * This method handles the POST request to the /products/delete/{id} endpoint.
     */
    @PostMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteById(id);
        return "redirect:/products/main"; // Redirect back to the main product list after deletion
    }

}