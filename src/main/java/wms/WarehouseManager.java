package wms;

import java.util.List;
import java.util.ArrayList;

public class WarehouseManager {
    private List<Product> products;

    public WarehouseManager() {
        products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        if (product == null) {
            System.out.println("The product dosent exist");
        }

        for (Product p : products) {
            if (p.getId() == product.getId()) {
                System.out.println("A product with this id already exists");
                return;
            }
        }

        products.add(product);
        System.out.println("The product has been added");
    }
}
