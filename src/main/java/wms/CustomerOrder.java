package wms;

import java.util.ArrayList;
import java.time.LocalDateTime;

// this class represents a customers order. it handles the data logic of what the customer buys. 
public class CustomerOrder {
    private int id;
    private ArrayList<Product> products;
    private LocalDateTime createdAt;

    public CustomerOrder(int id) {
        this.id = id;
        this.products = new ArrayList<>();
        this.createdAt = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public ArrayList<Product> getItems() {
        return products;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void addProduct(Product product) {
        if (product != null) {
            products.add(product);

        } else {
            System.out.println("Enter an actual product");
        }
    }

    public double calculateTotal() {
        double total = 0.0;

        for (Product p : products) {

            total += p.getPrice();
        }

        return total;
    }

    public double getTotalPrice() {
        return calculateTotal();
    }
}
