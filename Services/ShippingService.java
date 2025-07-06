package Services;

import java.util.HashMap;
import java.util.Vector;

import Abstracts.Product;
import Abstracts.Validator;
import Interfaces.Interfaces.Shippable;

public class ShippingService {

    // private String shippingInfo;
    private StringBuilder shippingDetails;
    private HashMap<Product, Integer> shippableProducts;
    private double totalWeight;
    private double shippingPrice;

    ShippingService(/* Customer customer, */ HashMap<Product, Integer> shippableProducts) throws IllegalArgumentException { // Might be expaned in future to include customer details
        // this.shippingInfo = customer.getAddress();
        Validator.checkEmptyMap(shippableProducts);

        this.totalWeight = 0;
        this.shippingPrice = 0;
        this.shippableProducts = shippableProducts;
        setProductsWeights();
        generateNotice();
        setShippingPrice();
    }

    public void generateNotice() {
        shippingDetails = new StringBuilder("** Shipment notice **\n");
        for (Product product : shippableProducts.keySet()) {
            shippingDetails.append(shippableProducts.get(product))
                            .append("x ")
                            .append(product.toString())
                            .append(formattedWeight(Shippable.class.cast(product).getWeight() * shippableProducts.get(product)))
                           .append("\n");
        }

        shippingDetails.append("Total package weight:\t").append(formattedWeight(totalWeight));
    }

    public void setProductsWeights() {
        for (Product product : shippableProducts.keySet()) {
            this.totalWeight += Shippable.class.cast(product).getWeight() * shippableProducts.get(product);
        }
    }

    public static HashMap<Product, Integer> getShippableProducts(HashMap<Product, Integer> products) {
        HashMap<Product, Integer> shippableProducts = new HashMap<>();
        for (HashMap.Entry<Product, Integer> entry : products.entrySet()) {
            Product product = entry.getKey();
            if (product instanceof Shippable) {
                shippableProducts.put(product, entry.getValue());
            }
        }
        return shippableProducts;
    }

    public double getShippingPrice() {
        return shippingPrice;
    }

    public double getTotalWeight() {
        return totalWeight;
    }

    public void setShippingPrice() {
        if (this.totalWeight < 1000) { // Assuming flat rates
            this.shippingPrice = 10;
        } else {
            this.shippingPrice = 20;
        }
    }

    public String formattedWeight(double weight) {
        if (weight < 1000) {
            return String.format("%.2fg", weight);
        } else {
            return String.format("%.2fkg", weight / 1000);
        }
    }

    @Override
    public String toString() {
        return shippingDetails.toString();
    }
    
}
