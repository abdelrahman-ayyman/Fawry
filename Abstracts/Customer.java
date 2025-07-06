package Abstracts;

public class Customer {
    private String name;
    private String email;
    private String address;
    private double balance = 0.0; // Assuming balance is a double
    private Cart cart;

    public Customer(String name, String email, String address, double balance) throws IllegalArgumentException {
        Validator.checkIfNull(name);
        Validator.validateEmail(email);
        Validator.checkIfNull(address);
        Validator.validateBalance(balance);
        this.name = name;
        this.email = email;
        this.address = address;
        this.balance = balance;
        this.cart = new Cart();
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public double getBalance() {
        return balance;
    }

    public Cart getCart() {
        return cart;
    }

    public void setName(String name) {
        Validator.checkIfNull(name);
        this.name = name;
    }

    public void setEmail(String email) {
        Validator.validateEmail(email);
        this.email = email;
    }

    public void setAddress(String address) {
        Validator.checkIfNull(address);
        this.address = address;
    }

    public void setBalance(double balance) {
        Validator.validateBalance(balance);
        this.balance = balance;
    }

    public void canBuy(double amount) throws IllegalArgumentException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero.");
        }
        if (this.balance < amount) {
            throw new IllegalArgumentException("Insufficient balance.");
        }
    }

    @Override
    public String toString() {
        return "Customer:\n " + name + "\nEmail: " + email + "\nAddress: " + address + "\nBalance: " + balance + "\n";
    }
}
