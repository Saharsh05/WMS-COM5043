package wms;

// PurchaseOrder class represents a restocking request that warehouse class sends to a supplier to order more units. 
// So this class handles just thedata logic not the business logic
public class PurchaseOrder {
    private int id;
    private Supplier supplier;
    private Product product;
    private int quantity;
    private String status;

    public PurchaseOrder(int id, Supplier supplier, Product product, int quantity, String status) {
        this.id = id;
        this.supplier = supplier;
        this.product = product;
        this.quantity = quantity;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public Product geProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String newStatus) {
        this.status = newStatus;
    }

    public int totalPrice() {
        return quantity * product.getPrice();
    }
}
