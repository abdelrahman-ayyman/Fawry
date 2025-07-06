package Services;

import Abstracts.Customer;
import Abstracts.Inventory;
import Abstracts.Product;
import Abstracts.Validator;

import java.util.HashMap;

import Abstracts.Cart;

public class Checkout {
    private ShippingService shippingService;
    private StringBuilder checkoutDetails;
    private Inventory inventory = Inventory.getInventory();
    private double subtotal = 0;
    private double shipping = 0;
    private double amount = 0;

    public Checkout(Customer customer) throws IllegalArgumentException {

        // Validate empty cart
        try {
            Validator.checkEmptyMap(customer.getCart().getProducts());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Cart is empty.");
        }

        // Check inventory for availability and expiration
        inventory.checkAvailability(customer.getCart().getProducts());
        inventory.checkExpiringProducts(customer.getCart().getProducts());

        // Initiate shipping service if there are shippable products
        HashMap<Product, Integer> shippableProducts = ShippingService.getShippableProducts(customer.getCart().getProducts());
        if (!shippableProducts.isEmpty()) {
            initiateShippingService(shippableProducts); // Strictly items with getWeight and getName methods
        }

        this.subtotal = customer.getCart().getTotalPrice();
        this.amount = this.subtotal + this.shipping;
        customer.canBuy(this.amount);

        generateReceipt(customer.getCart());

        // Update inventory and customer balance
        inventory.removeProducts(customer.getCart().getProducts());
        customer.setBalance(customer.getBalance() - this.amount);
        customer.getCart().clearCart(); // Clear the cart after checkout
        checkoutDetails.append("\nCustomer's remaining balance: " + customer.getBalance()).append("\n");
    }

    private void generateReceipt(Cart cart) {
        this.checkoutDetails = new StringBuilder("** Checkout receipt **\n");
        this.checkoutDetails.append(cart.toString() + "----------------------------\n");
        this.checkoutDetails.append("Subtotal: ").append(this.subtotal).append("\n");
        if (this.shippingService != null) {
            this.checkoutDetails.insert(0, this.shippingService.toString() + "\n\n");
            this.checkoutDetails.append("Shipping: ").append(this.shippingService.getShippingPrice()).append("\n");
        }
        this.checkoutDetails.append("Amount: ").append(this.amount);
    }

    private void initiateShippingService(HashMap<Product, Integer> shippableProducts) throws IllegalArgumentException {
        Validator.checkEmptyMap(shippableProducts);
        this.shippingService = new ShippingService(shippableProducts);
        this.shipping = shippingService.getShippingPrice();
    }

    public void printReceipt() {
        System.out.println(this.checkoutDetails.toString());
    }
    
}
