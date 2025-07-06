import java.time.LocalDate;

import Abstracts.Customer;
import Abstracts.Inventory;
import Products.TV;
import Products.FoodProduct.Cheese;
import Services.Checkout;

class Main
{
    public static void main(String[] args)
    {
        // Initiating Inventory
        Inventory inventory = Inventory.getInventory();

        // Creating products and adding them to the inventory
        Cheese cheese = new Cheese(50.0, LocalDate.of(2025, 12, 31), 200.0, "Cheddar");
        TV tv = new TV("Samsung", 1500.0, "Samsung", 55.0, 12.0);

        inventory.addProduct(cheese, 5);
        inventory.addProduct(tv, 2);

        // Displaying the inventory
        System.out.println(inventory.toString());

        Customer customer = new Customer("Abdelrahman", "abdelrahman.akefafy@gmail.com", "Giza, Egypt", 2000.0);
        Customer customer2 = new Customer("Ahmed", "ahmed@example.com", "Cairo, Egypt", 1530.0);

        // Valid purchase test
        System.out.println("\nVALID PURCHASE TEST\n");
        try {
            // Adding products to customer carts
            customer.getCart().addProduct(cheese, 2);
            customer.getCart().addProduct(cheese, 2); // Adding more cheese
            customer.getCart().addProduct(tv, 2);
            customer.getCart().removeProduct(tv, 1); // Removing one TV
            customer2.getCart().addProduct(tv, 1);

            // Displaying the customer's cart
            // System.out.println(customer.getCart().toString());
            // System.out.println(customer2.getCart().toString());

            // Proceeding to checkout
            Checkout checkout = new Checkout(customer);
            Checkout checkout2 = new Checkout(customer2); // Another customer checkout

            // Printing receipts
            System.out.println("\nCustomer 1 Receipt:");
            checkout.printReceipt();
            System.out.println("\nCustomer 2 Receipt:");
            checkout2.printReceipt();

            // Displaying the inventory
            System.out.println(inventory.toString());

            // Displaying the customer's cart
            // System.out.println(customer.getCart().toString());

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        // Insufficient stock test
        System.out.println("\nINSUFFICIENT STOCK TEST\n");
        try {
             // Another failing purchase
            customer.getCart().addProduct(tv, 1);
            Checkout checkout = new Checkout(customer); // This will throw an exception due to insufficient stock

            // Printing the receipt
            checkout.printReceipt();

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        // Insufficient balance test
        System.out.println("\nINSUFFICIENT BALANCE TEST\n");
        try {
            customer2.getCart().addProduct(cheese, 1);
            Checkout checkout = new Checkout(customer2); // This will throw an exception due to insufficient balance

            // Printing the receipt
            checkout.printReceipt();

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        // Empty cart test
        System.out.println("\nEMPTY CART TEST\n");
        try {
            Checkout checkout = new Checkout(customer); // This will throw an exception due to empty cart
            checkout.printReceipt();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        // Expired product test (Handled in both inventory and on checkout)
        System.out.println("\nEXPIRED PRODUCT TEST\n");
        try {
            // Adding an expired product to the cart
            Cheese expiredCheese = new Cheese(50.0, LocalDate.of(2025, 01, 07), 200.0, "Expired Cheddar");
            customer.getCart().addProduct(expiredCheese, 1);
            Checkout checkout = new Checkout(customer);
            checkout.printReceipt();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }   

    }
}