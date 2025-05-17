package wms;

import java.time.LocalDateTime;

// PurchaseOrder class represents a restocking request that warehouse class sends to a supplier to order more units. 
// So this class handles just thedata logic not the business logic
public class PurchaseOrder {
    private int id;
    private Supplier supplier;
    private Product product;
    private int quantity;
    private String status;
    private LocalDateTime createdAt;

    public PurchaseOrder(int id, Supplier supplier, Product product, int quantity) {
        this.id = id;
        this.supplier = supplier;
        this.product = product;
        this.quantity = quantity;
        this.status = "Pending";
        this.createdAt = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public LocalDateTime getCreatedAT() {
        return createdAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String newStatus) {
        this.status = newStatus;
    }

    public double totalPrice() {
        return quantity * product.getPrice();
    }
}
