package wms;

//import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

// this class represents a customers order. it handles the data logic of what the customer buys. 
public class CustomerOrder extends Order {
    private Map<Product, Integer> items;

    public CustomerOrder(int id) {
        super(id);
        this.items = new HashMap<>();
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

    public double getTotalPrice() {
        return calculateTotal();
    }
}
