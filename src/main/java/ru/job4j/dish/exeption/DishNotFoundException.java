package ru.job4j.dish.exeption;

/**
 * Oywayten 27.05.2023.
 */
public class DishNotFoundException extends RuntimeException {
    public DishNotFoundException(String message) {
        super(message);
    }
}
