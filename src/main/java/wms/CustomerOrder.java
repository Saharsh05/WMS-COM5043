package wms;

//import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.time.LocalDateTime;

// this class represents a customers order. it handles the data logic of what the customer buys. 
public class CustomerOrder {
    private int id;
    // private ArrayList<Product> products;
    private Map<Product, Integer> items;
    private LocalDateTime createdAt;

    public CustomerOrder(int id) {
        this.id = id;
        // this.products = new ArrayList<>();
        this.items = new HashMap<>();
        this.createdAt = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Map<Product, Integer> getItems() {
        return items;
    }

    public void addProduct(Product product, int quantity) {
        if (product == null || quantity <= 0) {
            System.out.println("Invalid product or quantity");

        } else {
            items.put(product, items.getOrDefault(product, 0) + quantity);
        }
    }

    public double calculateTotal() {
        double total = 0.0;

        for (Map.Entry<Product, Integer> entry : items.entrySet()) {

            Product p = entry.getKey();
            int quantity = entry.getValue();

            total += p.getPrice() * quantity;
        }

        return total;
    }

    public double getTotalPrice() {
        return calculateTotal();
    }
}
