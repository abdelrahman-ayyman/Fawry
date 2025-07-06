package Abstracts;

import java.util.Vector;

public class Cart {
    private Vector<Product> products;

    public Cart() {
        products = new Vector<>();
    }

    public void clearCart() {
        for (Product product : products) {
            product.setQuantity(0); // Resetting quantity of each product
        }
        products.clear();
    }

    public void addProduct(Product product, int quantity) throws IllegalArgumentException {
        Validator.validateProduct(product);
        Validator.validateAmount(quantity, 0, true);
        int availableQuantity = Inventory.getInventory().getProductQuantity(product);
        for (Product p : products) {
            if (p == product) {
                if (availableQuantity < p.getQuantity() + quantity) {
                    throw new IllegalArgumentException("Insufficient stock for " + product.getName() + ". Available: " + availableQuantity + ", Requested: " + (p.getQuantity() + quantity));
                }
                p.addQuantity(quantity);
                return;
            }
        }
        product.addQuantity(quantity);
        products.add(product);
    }

    public void removeProduct(Product product, int quantity) throws IllegalArgumentException {
        Validator.validateProduct(product);
        for (Product p : products) {
            if (p == product) {
                Validator.validateAmount(quantity, p.getQuantity(), false);
                p.removeQuantity(quantity);
                if (p.getQuantity() <= 0) {
                    products.remove(p);
                }
                return;
            }
        }
        throw new IllegalArgumentException("Product not found in cart: " + product.getName());
    }

    public Vector<Product> getProducts() {
        return products;
    }

    @Override
    public String toString() {
        StringBuilder cartDetails = new StringBuilder();
        for (Product product : products) {
            cartDetails.append(product.getQuantity()).append("x ").append(product.getName()).append("\t\t").append(product.getPrice() * product.getQuantity()).append("\n");
        }
        return cartDetails.toString();
    }

    public double getTotalPrice() {
        double total = 0;
        for (Product product : products) {
            total += product.getPrice() * product.getQuantity();
        }
        return total;
    }
}
