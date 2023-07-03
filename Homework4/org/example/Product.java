package org.example;

public class Product {

    private String title;
    private double price;
    private double discountedPrice;
    private String category;

    public Product(String title, double price, String category) {
        this.title = title;
        this.price = price;
        this.category = category;
        this.discountedPrice = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(double discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
