package com.example.catalog.common.exception;

/**
 * A custom exception to be thrown when a resource cannot be found.
 * This is a generic exception that can be used for any entity (e.g., Product, User, etc.).
 * It extends RuntimeException, so it does not need to be explicitly
 * caught or declared in method signatures (unchecked exception).
 */
public class NotFoundException extends RuntimeException {

    /**
     * Constructs a new NotFoundException with the specified detail message.
     *
     * @param message The detail message. The detail message is saved for later retrieval by the getMessage() method.
     */
    public NotFoundException(String message) {
        super(message);
    }
}
