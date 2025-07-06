package Abstracts;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private HashMap<Product, Integer> products;

    public Cart() {
        products = new HashMap<>();
    }

    public void clearCart() {
        products.clear();
    }

    public void addProduct(Product product, int quantity) throws IllegalArgumentException {
        Validator.validateProduct(product);
        Validator.validateAmount(quantity, 0, true);
        int availableQuantity = Inventory.getInventory().getProductQuantity(product);

        if (availableQuantity < quantity) {
            throw new IllegalArgumentException("Insufficient stock for " + product.getName() + ". Available: " + availableQuantity + ", Requested: " + quantity);
        }

        products.put(product, products.getOrDefault(product, 0) + quantity);
    }

    public void removeProduct(Product product, int quantity) throws IllegalArgumentException {
        Validator.validateProduct(product);
        Validator.validateAmount(quantity, products.getOrDefault(product, 0), false);
        
        if (!products.containsKey(product)) {
            throw new IllegalArgumentException("Cannot remove " + quantity + " of " + product.getName() + ". Not enough in cart.");
        }

        products.put(product, products.get(product) - quantity);
    }

    public Integer getProductQuantity(Product product) {
        Validator.validateProduct(product);
        return products.getOrDefault(product, 0);
    }

    public HashMap<Product, Integer> getProducts() {
        return products;
    }

    @Override
    public String toString() {
        StringBuilder cartDetails = new StringBuilder();
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            cartDetails.append(quantity).append("x ").append(product.getName()).append("\t\t").append(product.getPrice() * quantity).append("\n");
        }
        return cartDetails.toString();
    }

    public double getTotalPrice() {
        double total = 0;
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            total += product.getPrice() * quantity;
        }
        return total;
    }
}
