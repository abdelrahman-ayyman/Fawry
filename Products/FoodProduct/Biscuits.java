package Products.FoodProduct;

import java.time.LocalDate;
import Abstracts.FoodProduct;
import Abstracts.Validator;

public class Biscuits extends FoodProduct {
    private String flavor; // e.g., Chocolate, Vanilla, etc.

    public Biscuits(String name, Double price, LocalDate expirationDate, Double weight, String flavor) throws IllegalArgumentException {
        super(name, price, expirationDate, weight);

        Validator.checkIfNull(flavor);

        this.flavor = flavor;
    }

    public String getFlavor() {
        return flavor;
    }

    @Override
    public String toString() {
        return super.toString() + ", Biscuits{flavor='" + flavor + "'}";
    }
    
}
