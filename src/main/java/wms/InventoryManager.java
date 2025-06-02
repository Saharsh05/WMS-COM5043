package wms;

import java.util.ArrayList;
import java.util.List;

public class InventoryManager {
    private List<Product> products;

    public InventoryManager() {
        this.products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        if (product == null) {
            System.out.println("The product doesn't exist");
            return;
        }

        for (Product p : products) {
            if (p.getId() == product.getId()) {
                System.out.println("A product with this ID already exists");
                return;
            }
        }

        products.add(product);
        System.out.println("The product has been added");
    }

    public List<Product> getProducts() {
        return products;
    }

    public Product getProductById(int id) {
        for (Product p : products) {
            if (p.getId() == id) return p;
        }
        return null;
    }
}
