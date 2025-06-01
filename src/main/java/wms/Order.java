package wms;

import java.time.LocalDateTime;

// Abstract base class for orders (e.g., PurchaseOrder, CustomerOrder)
public abstract class Order {

    protected int id;
    protected LocalDateTime createdAt;

    // Constructor sets the order ID and creation time to now
    public Order(int id) {
        this.id = id;
        this.createdAt = LocalDateTime.now();
    }

    // Returns the ID of the order
    public int getId() {
        return id;
    }

    // Returns the creation timestamp
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    // Abstract method to be implemented by subclasses for calculating total cost
    public abstract double calculateTotal();
}
