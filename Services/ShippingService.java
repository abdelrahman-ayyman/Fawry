package Services;

import java.util.Vector;

import Abstracts.Product;
import Abstracts.Validator;
import Interfaces.Interfaces.Shippable;

public class ShippingService {

    // private String shippingInfo;
    private StringBuilder shippingDetails;
    private Vector<Product> shippableProducts;
    private double totalWeight;
    private double shippingPrice;

    ShippingService(/* Customer customer, */ Vector<Product> shippableProducts) throws IllegalArgumentException { // Might be expaned in future to include customer details
        // this.shippingInfo = customer.getAddress();
        Validator.checkEmptyVector(shippableProducts);

        this.totalWeight = 0;
        this.shippingPrice = 0;
        this.shippableProducts = shippableProducts;
        setProductsWeights();
        generateNotice();
        setShippingPrice();
    }

    public void generateNotice() {
        shippingDetails = new StringBuilder("** Shipment notice **\n");
        for (Product product : shippableProducts) {
            shippingDetails.append(product.toString())
                            .append(formattedWeight(Shippable.class.cast(product).getWeight() * product.getQuantity()))
                           .append("\n");
        }

        shippingDetails.append("Total package weight:\t").append(formattedWeight(totalWeight));
    }

    public void setProductsWeights() {
        for (Product product : shippableProducts) {
            this.totalWeight += Shippable.class.cast(product).getWeight() * product.getQuantity();
        }
    }

    public static Vector<Product> getShippableProducts(Vector<Product> products) {
        Vector<Product> shippableProducts = new Vector<>();
        for (Product product : products) {
            if (product instanceof Shippable) {
                shippableProducts.add(product);
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
