package wms;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;

// The WarehouseManager class coordinates the key business operations
// including managing products, suppliers, orders, and financial tracking.
public class WarehouseManager {
    private List<Product> products;
    private List<Supplier> suppliers;
    private List<PurchaseOrder> purchases;
    private List<CustomerOrder> customerOrders;
    private FinancialReport financialReport;

    public WarehouseManager() {
        products = new ArrayList<>();
        suppliers = new ArrayList<>();
        purchases = new ArrayList<>();
        customerOrders = new ArrayList<>();
        this.financialReport = new FinancialReport();
    }

    public FinancialReport getFinancialReport() {
        return financialReport;
    }

    // Adds a new product to the warehouse if it's not null and not a duplicate
    public void addProduct(Product product) {
        if (product == null) {
            System.out.println("The product dosent exist");
            return;
        }

        for (Product p : products) {
            if (p.getId() == product.getId()) {
                System.out.println("A product with this id already exists");
                return;
            }
        }

        products.add(product);
        System.out.println("The product has been added");
    }

    public List<Product> getProducts() {
        return products;
    }

    // Adds a supplier if not null and not a duplicate
    public void addSupplier(Supplier supplier) {
        if (supplier == null) {
            System.out.println("The supplier dosent exist");
            return;
        }

        for (Supplier s : suppliers) {
            if (s.getId() == supplier.getId()) {
                System.out.println("A supplier with this ID already exists");
                return;
            }
        }

        suppliers.add(supplier);
        System.out.println("The supplier has been added");
    }

    public List<Supplier> getSuppliers() {
        return suppliers;
    }

    public List<PurchaseOrder> getPurchases() {
    return purchases;
    }


    // Creates a purchase order if inputs are valid and references exist
    public void createPurchaseOrder(int orderId, Supplier supplier, Product product, int quantity) {
        if (supplier == null || product == null || quantity <= 0) {
            System.out.println("Invalid input: supplier/ product is null or quantity is invalid");
            return;
        }

        boolean supplierExists = false;
        for (Supplier s : suppliers) {
            if (s.getId() == supplier.getId()) {
                supplierExists = true;
                break;
            }
        }
        if (!supplierExists) {
            System.out.println("Supplier not found in the system");
            return;
        }

        boolean productExists = false;
        for (Product p : products) {
            if (p.getId() == product.getId()) {
                productExists = true;
                break;
            }
        }
        if (!productExists) {
            System.out.println("Product not found in the system");
            return;
        }

        PurchaseOrder po = new PurchaseOrder(orderId, supplier, product, quantity);
        purchases.add(po);
        supplier.addOrder(po);

        System.out.println("Purchase order created successfully");

    }

    public List<CustomerOrder> getCustomerOrder() {
        return customerOrders;
    }

    public void addCustomerOrder(CustomerOrder order) {
        if (order != null) {
            customerOrders.add(order);
            System.out.println("Customer order added to system.");
        }
    }

    // Processes a received purchase order if it exists and is pending
    public void receivePurchaseOrder(int orderId) {

        boolean orderFound = false;
        for (PurchaseOrder po : purchases) {
            if (po.getId() == orderId) {
                System.out.println("Order found. Proceeding to next processing steps");
                orderFound = true;
                if (po.getStatus().equalsIgnoreCase("Received")) {
                    System.out.println("This order has already been received");
                    return;
                }
                if (po.getStatus().equalsIgnoreCase("Pending")) {
                    Product product = po.getProduct();
                    int quantity = po.getQuantity();
                    product.restock(quantity);

                    System.out.println("The product has been restocked");
                    po.setStatus("Received");
                    System.out.println("Purchase order status updated to: " + po.getStatus());
                    financialReport.addExpense(po.calculateTotal());

                    return;
                }
            }
        }
        if (!orderFound) {
            System.out.println("No purchase found with order number:" + orderId);
        }
    }

    // Processes a customer order by checking stock and reducing quantities
    public void processCustomerOrder(CustomerOrder order) {

        if (order == null || order.getItems().isEmpty()) {
            System.out.println("The order dosent exist or is null");

            return;
        }

        for (Map.Entry<Product, Integer> entry : order.getItems().entrySet()) {
            Product product = entry.getKey();
            int quantityOrdered = entry.getValue();
            boolean found = false;
            for (Product p : products) {

                if (p.getId() == product.getId()) {
                    System.out.println("Found the products, carry on with next steps");
                    found = true;
                    if (quantityOrdered >= p.getQuantity()) {
                        System.out.println("There is not enough stock of: " + order + " to complete your order");

                        return;
                    }
                    break;
                }
            }
            if (!found) {
                System.out.println("Product not found in inventory: " + order);
                return;
            }
        }

        // Reduce stock
        for (Map.Entry<Product, Integer> entry : order.getItems().entrySet()) {
            Product product = entry.getKey();
            int quantityOrdered = entry.getValue();

            for (Product p : products) {
                if (p.getId() == product.getId()) {

                    p.setQuantity(p.getQuantity() - quantityOrdered);
                    break;
                }

            }

        }

        customerOrders.add(order);
        financialReport.addRevenue(order.calculateTotal());
    }

    // Looks up a supplier by ID
    public Supplier getSupplierById(int id) {
        for (Supplier s : suppliers) {
        if (s.getId() == id) {
            return s;
            }
        }
    return null;
    }

    // Prints the order history for a given supplier
    public void printSupplierOrderHistory(Supplier supplier) {
    System.out.println(" Order history for: " + supplier.getName());
    for (PurchaseOrder po : supplier.getOrderHistory()) {
        System.out.println("PO ID: " + po.getId() +
                           ", Product: " + po.getProduct().getName() +
                           ", Quantity: " + po.getQuantity() +
                           ", Status: " + po.getStatus());
        }
    }   


    public void removeSupplier(int id) {
    Supplier toRemove = null;

    for (Supplier s : suppliers) {
        if (s.getId() == id) {
            toRemove = s;
            break;
            }
        }

    if (toRemove != null) {
        suppliers.remove(toRemove);
        System.out.println("Supplier with ID " + id + " removed.");
    } else {
        System.out.println("Supplier not found.");
        }
    }
    public void updateSupplier(int id, String newName, String newContactInfo) {
    Supplier supplier = getSupplierById(id);

    if (supplier != null) {
        if (newName != null && !newName.isBlank()) {
            // You can add a setName method if needed; or make name non-final
            supplier.setName(newName);
        }

        if (newContactInfo != null && !newContactInfo.isBlank()) {
            supplier.setContactInfo(newContactInfo);
        }

        System.out.println("Supplier updated successfully.");
    } else {
        System.out.println("Supplier not found.");
        }
    }

}
