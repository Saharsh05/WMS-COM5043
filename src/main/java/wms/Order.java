package wms;

import java.time.LocalDateTime;

public abstract class Order {

    protected int id;
    protected LocalDateTime createdAt;

    public Order(int id) {
        this.id = id;
        this.createdAt = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public abstract double calculateTotal();
}
