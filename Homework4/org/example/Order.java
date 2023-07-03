package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Order {

    private Customer customer;
    private Map<Product, Integer> products;
    private Map<Product, ArrayList<Double>> discountedProducts;

    public Order(Customer customer) {
        this.customer = customer;
        this.products = new HashMap<>();
        this.discountedProducts = new HashMap<>();
    }

    public void addProduct(Product product, int amount) {
        products.put(product, amount);

    }

    public void addDiscountedProducts(Product product, double discount) {
        ArrayList<Double> info = new ArrayList<>();
        info.add(product.getPrice());
        info.add(product.getDiscountedPrice());
        info.add(discount);
        discountedProducts.put(product, info);

    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Customer: ").append(customer.getFirstName()).append(" ").append(customer.getLastName()).append("\n");

        double totalAmount = 0;

        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            Product product = entry.getKey();
            int amount = entry.getValue();
            double productTotal;

            sb.append("Product: ").append(product.getTitle());
            sb.append(", Amount: ").append(amount);

            ArrayList<Double> discountedProductInfo = discountedProducts.get(product);
            if (discountedProductInfo != null) {
                productTotal = product.getDiscountedPrice() * amount;
                double originalPrice = discountedProductInfo.get(0);
                double discountedPrice = discountedProductInfo.get(1);
                double discount = discountedProductInfo.get(2);
                sb.append(", Price per unit: ").append(String.format("%.2f", originalPrice)).append("$");
                sb.append(", Discount: ").append((int) discount).append("%");
                sb.append(", Discounted price: ").append(String.format("%.2f", discountedPrice)).append("$");
            } else {
                productTotal = product.getPrice() * amount;
                sb.append(", Price per unit: ").append(String.format("%.2f",product.getPrice())).append("$");
                sb.append(", Discount: 0%");
            }

            sb.append(", Total: ").append(String.format("%.2f", productTotal)).append("$").append("\n");

            totalAmount += productTotal;
        }

        sb.append("Total Purchase Amount: ").append(String.format("%.2f",totalAmount)).append("$ \n");

        return sb.toString();
    }
}
