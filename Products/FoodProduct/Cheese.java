package Products.FoodProduct;

import java.time.LocalDate;
import Abstracts.Validator;

public class Cheese extends Abstracts.FoodProduct {
    private String type; // e.g., Cheddar, Mozzarella, etc.

    public Cheese(Double price, LocalDate expirationDate, Double weight, String type) throws IllegalArgumentException {
        super("Cheese", price, expirationDate, weight);

        Validator.checkIfNull(type);

        this.type = type;
    }

    public String getType() {
        return type;
    }
    
}
