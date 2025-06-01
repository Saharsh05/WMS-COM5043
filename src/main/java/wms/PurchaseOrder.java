package wms;

// PurchaseOrder class represents a restocking request that warehouse class sends to a supplier to order more units. 
// So this class handles just thedata logic not the business logic
public class PurchaseOrder extends Order {
    private Supplier supplier;
    private Product product;
    private int quantity;
    private String status;

    // Constructor to initialise all fields and default the status to "Pending"
    public PurchaseOrder(int id, Supplier supplier, Product product, int quantity) {
        super(id);
        this.supplier = supplier;
        this.product = product;
        this.quantity = quantity;
        this.status = "Pending";

    }

    // Returns the supplier associated with this purchase order
    public Supplier getSupplier() {
        return supplier;
    }

    // Returns the product being ordered
    public Product getProduct() {
        return product;
    }

    // Returns the quantity ordered
    public int getQuantity() {
        return quantity;
    }

    // Returns the current status of the purchase order
    public String getStatus() {
        return status;
    }

    // Updates the status of the purchase order
    public void setStatus(String newStatus) {
        this.status = newStatus;
    }

    // Calculates the total cost of the order based on product price and quantity
    @Override
    public double calculateTotal() {

        if(product == null || quantity < 0 || product.getPrice() < 0){
            System.out.println("Warning: Invalid product, quantity or price");
            return 0.0;
        }

        return quantity * product.getPrice();
    }
}
