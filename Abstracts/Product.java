package Abstracts;

public abstract class Product {
    protected String name;
    protected Double price;
    protected int quantity = 0;

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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        Validator.validateAmount(quantity, this.quantity, true);
        this.quantity = quantity;
    }

    public void setName(String name) {
        Validator.checkIfNull(name);
        this.name = name;
    }

    public void setPrice(Double price) {
        Validator.validatePrice(price);
        this.price = price;
    }

    public void addQuantity(int amount) {
        Validator.validateAmount(amount, this.quantity, true);
        this.quantity += amount;
    }

    public void removeQuantity(int amount) throws IllegalArgumentException {
        Validator.validateAmount(amount, this.quantity, false);
        this.quantity -= amount;
    }

    @Override
    public String toString() {
        return quantity + "x " + name + "\t\t";
    }
}