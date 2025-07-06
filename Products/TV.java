package Products;

public class TV extends Abstracts.Product implements Interfaces.Interfaces.Shippable {
    private String brand; // e.g., Samsung, LG, etc.
    private Double screenSize; // in inches
    private double weight; // in grams

    public TV(String name, Double price, String brand, Double screenSize, Double weight) throws IllegalArgumentException { // weight in kg
        super(name, price);
        
        Abstracts.Validator.checkIfNull(brand);
        Abstracts.Validator.validateWeight(weight);

        this.brand = brand;
        this.screenSize = screenSize;
        this.weight = weight * 1000; // Convert kg to grams
    }

    public String getBrand() {
        return brand;
    }

    public Double getScreenSize() {
        return screenSize;
    }

    @Override
    public Double getWeight() {
        return weight;
    }
    
}
