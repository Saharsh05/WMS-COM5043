package wms;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;

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
}
