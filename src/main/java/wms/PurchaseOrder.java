package wms;

// PurchaseOrder class represents a restocking request that warehouse class sends to a supplier to order more units. 
// So this class handles just thedata logic not the business logic
public class PurchaseOrder extends Order {
    private Supplier supplier;
    private Product product;
    private int quantity;
    private String status;

    public PurchaseOrder(int id, Supplier supplier, Product product, int quantity) {
        super(id);
        this.supplier = supplier;
        this.product = product;
        this.quantity = quantity;
        this.status = "Pending";

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String newStatus) {
        this.status = newStatus;
    }

    @Override
    public double calculateTotal() {

        if(product == null || quantity < 0 || product.getPrice() < 0){
            System.out.println("Warning: Invalid product, quantity or price");
            return 0.0;
        }

        return quantity * product.getPrice();
    }
}
