package com.example.catalog.common.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Global exception handler for the application.
 * This class uses @ControllerAdvice to handle exceptions across all controllers.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles the custom ProductNotFoundException.
     * When a product is not found (e.g., during edit/delete), this handler redirects
     * the user back to the main product catalog with an error message.
     *
     * @param ex The ProductNotFoundException instance.
     * @param ra RedirectAttributes for passing messages to the redirected view.
     * @return The redirect view to the main page.
     */
    @ExceptionHandler(NotFoundException.class)
    public String handleProductNotFoundException(NotFoundException ex, RedirectAttributes ra) {
        // Add an error message to RedirectAttributes to be displayed on the next page
        ra.addFlashAttribute("errorMessage", "Error: " + ex.getMessage());
        return "redirect:/";
    }

    /**
     * Handles all other generic exceptions.
     * This acts as a fallback for any unexpected errors.
     *
     * @param ex The generic Exception instance.
     * @param model The UI model.
     * @return The name of a generic error view.
     */
    @ExceptionHandler(Exception.class)
    public String handleGeneralException(Exception ex, Model model) {
        // Add a general error message to the model
        model.addAttribute("errorMessage", "An unexpected error occurred: " + ex.getMessage());
        return "error"; // Assuming you have a generic error.html template
    }
}
