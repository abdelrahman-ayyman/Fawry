package Abstracts;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Vector;

public class Validator {

    public static void checkIfNull(String property) {
        if (property == null || property.trim().isEmpty()) {
            throw new IllegalArgumentException("Property cannot be null or empty.");
        }
    }

    public static void checkEmptyVector(Vector<?> vector) {
        if (vector == null || vector.isEmpty()) {
            throw new IllegalArgumentException("Vector cannot be null or empty.");
        }
    }

    public static void checkEmptyMap(HashMap<?, ?> map) {
        if (map == null || map.isEmpty()) {
            throw new IllegalArgumentException("Map cannot be null or empty.");
        }
    }

    public static void validateBalance(double balance) {
        if (balance < 0) {
            throw new IllegalArgumentException("Balance cannot be negative.");
        }
    }

    public static void validateEmail(String email) {
        if (email == null || !email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            throw new IllegalArgumentException("Invalid email format.");
        }
    }

    public static void validatePrice(Double price) {
        if (price == null) {
            throw new IllegalArgumentException("Price cannot be null.");
        }
        if (price <= 0) {
            throw new IllegalArgumentException("Price must be greater than zero.");
        }
    }

    public static void validateAmount(int amount, int currentAmount, boolean isAddition) {
        if (isAddition) {
            if (amount < 0) {
                throw new IllegalArgumentException("Amount to add cannot be negative.");
            }
        } else {
            if (amount < 0 || amount > currentAmount) {
                throw new IllegalArgumentException("Amount to remove must be between 0 and " + currentAmount + ".");
            }
        }
    }

    public static void validateWeight(Double weight) {
        if (weight == null) {
            throw new IllegalArgumentException("Weight cannot be null.");
        }
        if (weight <= 0) {
            throw new IllegalArgumentException("Weight must be greater than zero.");
        }
    }

    public static void validateExpirationDate(LocalDate expirationDate) {
        if (expirationDate == null) {
            throw new IllegalArgumentException("Expiration date cannot be null.");
        }
        if (expirationDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Expiration date must be in the future.");
        }
    }

    public static void validateProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null.");
        }
    }
}