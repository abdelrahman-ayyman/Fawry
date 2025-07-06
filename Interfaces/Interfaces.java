package Interfaces;

import java.time.LocalDate;

public class Interfaces {
    public interface Shippable {
        // Assuming weight is in grams and unmodifiable
        Double getWeight(); 
    }

    public interface Perishable {
        boolean isExpired();
        LocalDate getExpirationDate();
    }
}
