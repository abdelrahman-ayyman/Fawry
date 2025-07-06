package Abstracts;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import Interfaces.Interfaces.Perishable;

public class Inventory {
    private Map<Product, Integer> productStock;
    private static Inventory inventoryInstance;

    private Inventory() {
        productStock = new HashMap<>();
    }

    public void addProduct(Product product, int quantity) throws IllegalArgumentException {
        Validator.validateProduct(product);
        Validator.validateAmount(quantity, 0, true);
        productStock.put(product, productStock.getOrDefault(product, 0) + quantity);
    }

    public void removeProduct(Product product, int quantity) throws IllegalArgumentException {
        Validator.validateProduct(product);
        Validator.validateAmount(quantity, productStock.getOrDefault(product, 0), false);
        productStock.put(product, productStock.get(product) - quantity);
    }

    public void removeProducts(Vector<Product> products) throws IllegalArgumentException {
        for (Product product : products) {
            Validator.validateProduct(product);
            Validator.validateAmount(product.getQuantity(), productStock.getOrDefault(product, 0), false);
            productStock.put(product, productStock.get(product) - product.getQuantity());
        }
    }

    public int getProductQuantity(Product product) {
        Validator.validateProduct(product);
        return productStock.getOrDefault(product, 0);
    }

    public void checkAvailability(Vector<Product> cart) throws IllegalArgumentException {
        for (Product product : cart) {
            int availableQuantity = getProductQuantity(product);
            if (availableQuantity < product.getQuantity()) {
                System.out.println(inventoryInstance);
                throw new IllegalArgumentException("Insufficient stock for " + product.getName() + ". Available: " + availableQuantity + ", Requested: " + product.getQuantity());
            }
        }
    }

    public void checkExpiringProducts(Vector<Product> cart) throws IllegalArgumentException {
        for (Product product : cart) {
            if (product instanceof Perishable && ((Perishable) product).isExpired()) {
                throw new IllegalArgumentException("Product " + product.getName() + " is expired and cannot be purchased.");
            }
        }
    }

    public static Inventory getInventory() {
        // Singleton pattern
        if (inventoryInstance != null) {
            return inventoryInstance;
        }
        inventoryInstance = new Inventory();
        return inventoryInstance;
    }

    @Override
    public String toString() {
        StringBuilder inventoryDetails = new StringBuilder("Inventory:\n");
        for (Map.Entry<Product, Integer> entry : productStock.entrySet()) {
            inventoryDetails.append("Product: ").append(entry.getKey().getName())
                            .append(", Quantity: ").append(entry.getValue()).append("\n");
        }
        return inventoryDetails.toString();
    }
}
