# Fawry

A Java-based inventory and checkout system simulating e-commerce functionality. The project manages products, customer accounts, shopping carts, and order processing, including inventory tracking, input validation, and receipt generation. The codebase applies object-oriented design, the Singleton pattern for centralized inventory control, and SOLID principles to ensure modularity, extensibility, and maintainability.

## Features

- **Inventory Management:** Add, remove, and track product quantities in a centralized inventory using the Singleton pattern.
- **Customer & Cart:** Customers can add/remove products to their carts and view their current balance and cart contents.
- **Checkout Process:** Validates cart contents, checks inventory, calculates totals (including shipping), and generates receipts.
- **Validation:** All input and business rules are enforced through a centralized validation utility.
- **Extensible Design:** Easily add new product types or extend business logic thanks to interface-driven architecture and adherence to SOLID principles.

## Code Quality

- **Object-Oriented:** Modular classes for each responsibility (Inventory, Customer, Cart, Checkout, etc.).
- **Design Patterns:** Implements Singleton for inventory and uses interfaces for extensibility (e.g., Perishable, Shippable).
- **SOLID Principles:** Code structure allows for easy extension and testing.

## Getting Started

### Prerequisites

- Java 8 or higher
- (Optional) Java IDE such as IntelliJ IDEA or Eclipse

### How to Run

1. **Clone the repository**
   ```sh
   git clone https://github.com/abdelrahman-ayyman/Fawry.git
   ```
2. **Open the project**
   - Open the project folder in your preferred Java IDE.

3. **Build the project**
   - Compile the code. No external dependencies are required.

4. **Run the program**
   - Locate the `Main.java` file.
   - Run it through your IDE or use the command line:
     ```sh
     javac src/Main.java
     java -cp src Main
     ```

5. **Interact**
   - Check the console for receipts, shipment notices, and error messages for invalid operations.

## Example Output

```
VALID PURCHASE TEST

Customer 1 Receipt:
** Checkout receipt **
4x Cheddar          800.0
1x Samsung          1500.0
----------------------------
Subtotal: 2300.0
Shipping: 10.0
Amount: 2310.0
Customer's remaining balance: 310.0
```

## Invalid Cases
<div align='center'> <img width='600px' src="https://github.com/user-attachments/assets/059bb7ed-1077-4a12-8244-af609148b72d" /> </div>

## Project Structure

- `Abstracts/` & `Interfaces/` — Core abstract classes and interfaces (e.g., Product, Cart, Inventory, Validator)
- `Products/` — Concrete product implementations (e.g., TV, Cheese)
- `Services/` — Business logic (e.g., Checkout, ShippingService)
- `Main.java` — Entry point for the application
