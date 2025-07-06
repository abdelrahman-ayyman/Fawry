package Abstracts;

public abstract class Product {
    protected String name;
    protected Double price;

    public Product(String name, Double price) throws IllegalArgumentException {
        
        Validator.checkIfNull(name);
        Validator.validatePrice(price);

        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public void setName(String name) {
        Validator.checkIfNull(name);
        this.name = name;
    }

    public void setPrice(Double price) {
        Validator.validatePrice(price);
        this.price = price;
    }

    @Override
    public String toString() {
        return name + "\t\t";
    }
}