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
        try {
            // Example usage of the provided code
            // Initiating Inventory
            Inventory inventory = Inventory.getInventory();

            // Creating products and adding them to the inventory
            Cheese cheese = new Cheese(50.0, LocalDate.of(2025, 12, 31), 200.0, "Cheddar");
            TV tv = new TV("Samsung", 1500.0, "Samsung", 55.0, 12.0);

            inventory.addProduct(cheese, 5);
            inventory.addProduct(tv, 2);

            // Displaying the inventory
            System.out.println(inventory.toString());

            // I assumed one user at a time for simplicity as products are passed by reference. To add a new customer, new instances of products must be created as well.
            Customer customer = new Customer("Abdelrahman", "abdelrahman.akefafy@gmail.com", "Giza, Egypt", 2000.0);

            // Adding products to customer carts
            customer.getCart().addProduct(cheese, 2);
            customer.getCart().addProduct(cheese, 3); // Adding more cheese
            customer.getCart().addProduct(tv, 2);
            customer.getCart().removeProduct(tv, 1); // Removing one TV

            // Displaying the customer's cart
            // System.out.println(customer.getCart().toString());

            // Proceeding to checkout
            Checkout checkout = new Checkout(customer);

            // Printing receipts
            checkout.printReceipt();

            // Displaying the inventory
            System.out.println(inventory.toString());

            // Another failing purchase
            customer.getCart().addProduct(tv, 1);
            checkout = new Checkout(customer); // This will throw an exception due to insufficient stock

            // Displaying the customer's cart
            // System.out.println(customer.getCart().toString());

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}