package wms;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OrderManager {
    private List<PurchaseOrder> purchases;
    private List<CustomerOrder> customerOrders;

    private InventoryManager inventoryManager;
    private SupplierManager supplierManager;
    private FinanceManager financeManager;

    public OrderManager(InventoryManager inventoryManager, SupplierManager supplierManager, FinanceManager financeManager) {
        this.inventoryManager = inventoryManager;
        this.supplierManager = supplierManager;
        this.financeManager = financeManager;
        this.purchases = new ArrayList<>();
        this.customerOrders = new ArrayList<>();
    }

    public void createPurchaseOrder(int orderId, Supplier supplier, Product product, int quantity) {
        if (supplier == null || product == null || quantity <= 0) {
            System.out.println("Invalid input: supplier/product is null or quantity is invalid");
            return;
        }

        if (supplierManager.getSupplierById(supplier.getId()) == null ||
            inventoryManager.getProductById(product.getId()) == null) {
            System.out.println("Supplier or product not found in the system");
            return;
        }

        PurchaseOrder po = new PurchaseOrder(orderId, supplier, product, quantity);
        purchases.add(po);
        supplier.addOrder(po);

        System.out.println("Purchase order created successfully");
    }

    public void receivePurchaseOrder(int orderId) {
        for (PurchaseOrder po : purchases) {
            if (po.getId() == orderId) {
                if (po.getStatus().equalsIgnoreCase("Received")) {
                    System.out.println("This order has already been received");
                    return;
                }

                Product product = po.getProduct();
                product.restock(po.getQuantity());
                po.setStatus("Received");

                financeManager.addExpense(po.calculateTotal());

                System.out.println("Purchase order received and stock updated");
                return;
            }
        }
        System.out.println("Purchase order not found");
    }

    public void processCustomerOrder(CustomerOrder order) {
        if (order == null || order.getItems().isEmpty()) {
            System.out.println("Order is null or has no items");
            return;
        }

        for (Map.Entry<Product, Integer> entry : order.getItems().entrySet()) {
            Product p = inventoryManager.getProductById(entry.getKey().getId());
            if (p == null) {
                System.out.println("Product not found: " + entry.getKey().getName());
                return;
            }
            if (entry.getValue() > p.getQuantity()) {
                System.out.println("Not enough stock for: " + p.getName());
                return;
            }
        }

        // Update stock and log low stock warning
        for (Map.Entry<Product, Integer> entry : order.getItems().entrySet()) {
            Product p = inventoryManager.getProductById(entry.getKey().getId());
            int newQuantity = p.getQuantity() - entry.getValue();
            p.setQuantity(newQuantity);
            if (p.isLowStock()) {
                System.out.println("Warning: Stock for product " + p.getName() + " is now low (" + p.getQuantity() + " units remaining).");
            }
        }

        customerOrders.add(order);
        financeManager.addRevenue(order.calculateTotal());
    }

    public void addCustomerOrder(CustomerOrder order) {
        if (order != null) {
            customerOrders.add(order);
        }
    }

    public List<PurchaseOrder> getPurchases() {
        return purchases;
    }

    public List<CustomerOrder> getCustomerOrders() {
        return customerOrders;
    }
}
