package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Main {

    enum Discount {
        D0,
        D5,
        D10,
        D15,
        D20
    }

    static Customer[] customers;
    static Product[] products;
    static Random rand = new Random();
    static Discount discount;


    public static void main(String[] args) {

        Customer customer1 = new Customer("John", "Smith", 36, "+1 (870) 972-0755", Customer.Gender.MALE);
        Customer customer2 = new Customer("Amy", "Green", 21, "+1 (517) 647-7039", Customer.Gender.FEMALE);
        Customer customer3 = new Customer("Jack", "Black", 46, "+1 (717) 569-0733", Customer.Gender.MALE);
        Customer customer4 = new Customer("Martha", "White", 57, "+1 (309) 699-6206", Customer.Gender.FEMALE);

        customers = new Customer[]{customer1, customer2, customer3, customer4};

        products = new Product[]{
                new Product("Milk", 2.99, "Standard"),
                new Product("Eggs", 2.50, "Standard"),
                new Product("Water", 1.99, "Standard"),
                new Product("Apples", 0.99, "Premium"),
                new Product("Sausages", 5.99, "Premium")
        };

        Map<String, Integer> productList1 = new HashMap<>();
        {
            productList1.put("Eggs", 1);
            productList1.put("Milk", 2);
            productList1.put("Pork", 1);
        }

        Map<String, Integer> productList2 = new HashMap<>();
        {
            productList2.put("Eggs", 3);
            productList2.put("Milk", 1);
            productList2.put("Apples", 3);
        }

        Map<String, Integer> productList3 = new HashMap<>();
        {
            productList3.put("Water", 1);
            productList3.put("Apples", 20);
            productList3.put("Sausages", -1);
        }

        Map<String, Integer> productList4 = new HashMap<>();
        {
            productList4.put("Water", 101);
        }

        Map<String, Integer> productList5 = new HashMap<>();
        {
            productList5.put("Apples", 10);
            productList5.put("Eggs", 10);
            productList5.put("Milk", 1);


        }

        Order[] orders = new Order[5];

        Map<String, Integer>[] purchases = new HashMap[5];
        purchases[0] = productList1;
        purchases[1] = productList2;
        purchases[2] = productList3;
        purchases[3] = productList4;
        purchases[4] = productList5;


        String[] phones = {"+1 (870) 972-0755", "+1 (517) 647-7039", "+1 (717) 569-0733", "+1 (309) 699-6206", "+1 (703) 780-0913"};

        int purchaseCount = 0;

        String discountedProduct = "Apples";
        Discount[] allDiscounts = Discount.values();
        discount = allDiscounts[rand.nextInt(allDiscounts.length)];

        for (int i = 0; i < 5; i++) {
            String phone = phones[i];
            Map<String, Integer> productList = purchases[i];

            try {
                Order order = makePurchase(phone, productList, discountedProduct);
                orders[i] = order;
                purchaseCount++;
            } catch (ProductException e) {
                System.out.println("Error! " + e.getMessage());
            } catch (AmountException e) {
                System.out.println("Error! " + e.getMessage());
                Order order = new Order(customers[i]);
                orders[i] = order;
                for (Map.Entry<String, Integer> entry : productList.entrySet()) {
                    String productName = entry.getKey();
                    int amount = entry.getValue();
                    Product product = null;
                    for (Product p : products) {
                        if (p.getTitle().equals(productName)) {
                            product = p;
                        }
                    }
                    if (product != null) {
                        if (amount <= 0 || amount > 100) {
                            amount = 1;
                        }
                        if (product.getTitle().equals(discountedProduct)) {
                            applyRandomDiscount(product, discount);
                            order.addDiscountedProducts(product, Double.parseDouble(discount.name().substring(1)));
                        }
                        order.addProduct(product, amount);
                    } else {
                        System.out.println("Error! Product not found: " + productName);
                    }
                }
                purchaseCount++;
            } catch (CustomerException e) {
                System.out.println("Error! " + e.getMessage());
            }
        }

        System.out.println("Total orders: " + purchaseCount);
        System.out.println("\n");

        for (Order order : orders) {
            if(order != null) {
                System.out.println(order);
            }
        }
    }

    public static Order makePurchase(String phone, Map<String, Integer> productList, String discountedProduct)
            throws CustomerException, ProductException, AmountException {

        Customer customer = null;

        for (Customer c : customers) {
            if (c.getPhone().equals(phone)) {
                customer = c;
            }
        }

        if (customer == null) {
            throw new CustomerException("Customer not found");
        }

        Order order = new Order(customer);

        for (Map.Entry<String, Integer> entry : productList.entrySet()) {
            String productName = entry.getKey();
            int amount = entry.getValue();

            Product product = null;

            for (Product p : products) {
                if (p.getTitle().equals(productName)) {
                    product = p;
                    break;
                }
            }

            if (product == null) {
                throw new ProductException("Product not found: " + productName);
            }

            if (amount <= 0 || amount > 100) {
                order.addProduct(product, 1);
                throw new AmountException("Invalid product amount: " + productName + ". Setting amount to 1.");

            }

            boolean isDiscounted = false;

            if (productName.equals(discountedProduct)) {
                System.out.println("Discount: " + discount + ". Price before discount: " + product.getPrice());
                Product discounted = new Product(product.getTitle(), product.getPrice(), product.getCategory());
                applyRandomDiscount(discounted, discount);
                double discountedPrice = discounted.getDiscountedPrice();
                order.addDiscountedProducts(discounted, Double.parseDouble(discount.name().substring(1)));
                System.out.println("Price after discount: " + discountedPrice);
                order.addProduct(discounted, amount);
                isDiscounted = true;

            }

            if (!isDiscounted) {
                order.addProduct(product, amount);
            }
        }

        return order;
    }


    public static void applyRandomDiscount(Product product, Discount discount) {
        double price = product.getPrice();
        double discountedPrice = price;
        switch (discount) {
            case D5:
                discountedPrice = price * 0.95;
                break;
            case D10:
                discountedPrice = price * 0.90;
                break;
            case D15:
                discountedPrice = price * 0.85;
                break;
            case D20:
                discountedPrice = price * 0.80;
                break;
            default:
                break;
        }
        product.setDiscountedPrice(discountedPrice);
    }

}
