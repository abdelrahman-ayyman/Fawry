package Abstracts;

import Interfaces.Interfaces;

import java.time.LocalDate;

public class FoodProduct extends Product implements Interfaces.Shippable, Interfaces.Perishable {
    private LocalDate expirationDate;
    private Double weight;

    public FoodProduct(String name, Double price, LocalDate expirationDate, Double weight) throws IllegalArgumentException {
        super(name, price);
        
        Validator.validateExpirationDate(expirationDate);
        Validator.validateWeight(weight);

        this.expirationDate = expirationDate;
        this.weight = weight;
    }

    public boolean isExpired() {
        return LocalDate.now().isAfter(expirationDate);
    }

    public LocalDate getExpirationDate() { // Assuming expiration date can't be updated after creation
        return expirationDate;
    }

    @Override
    public Double getWeight() {
        return weight;
    }
}