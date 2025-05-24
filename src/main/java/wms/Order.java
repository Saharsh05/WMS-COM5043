package wms;

import java.util.ArrayList;
import java.rmi.server.LoaderHandler;
import java.time.LocalDateTime;

public abstract class Order {

    private int id;
    // private ArrayList<Product> history;
    private LocalDateTime createdAt;

    public Order(int id) {
        this.id = id;
        this.createdAt = LocalDateTime.now();
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
