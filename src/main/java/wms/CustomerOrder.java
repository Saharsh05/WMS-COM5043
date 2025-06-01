package wms;

//import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

// this class represents a customers order. it handles the data logic of what the customer buys. 
public class CustomerOrder extends Order {
    // Stores the products and their quantities in the order
    private Map<Product, Integer> items;

    public CustomerOrder(int id) {
        super(id);
        this.items = new HashMap<>();
    }

    // Returns the current items in the order.
    public Map<Product, Integer> getItems() {
        return items;
    }

    // Adds a product to the order or increases its quantity if already present.
    public void addProduct(Product product, int quantity) {
        if (product == null || quantity <= 0) {
            System.out.println("Invalid product or quantity");

        } else {
            items.put(product, items.getOrDefault(product, 0) + quantity);
        }
    }

    // Calculates the total cost of the order by summing price * quantity for each product.
    @Override
    public double calculateTotal() {
        double total = 0.0;

        for (Map.Entry<Product, Integer> entry : items.entrySet()) {

            Product p = entry.getKey();
            int quantity = entry.getValue();

            total += p.getPrice() * quantity;
        }

        return total;
    }

    // Convenience method to get the total price.
    public double getTotalPrice() {
        return calculateTotal();
    }
}
