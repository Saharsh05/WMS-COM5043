package wms;

import java.util.ArrayList;

// this class represents a customers order. it handles the data logic of what the customer buys. 
public class CustomerOrder {
    private int id;
    private ArrayList<Product> products;

    public CustomerOrder(int id) {
        this.id = id;
        this.products = new ArrayList<>();
    }

    public int getId() {
        return id;
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
}
