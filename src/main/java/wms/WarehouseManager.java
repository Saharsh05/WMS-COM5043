package wms;

import java.util.List;
import java.util.ArrayList;

public class WarehouseManager {
    private List<Product> products;
    private List<Supplier> suppliers;
    private List<PurchaseOrder> purchases;

    public WarehouseManager() {
        products = new ArrayList<>();
        suppliers = new ArrayList<>();
        purchases = new ArrayList<>();
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

    public void receivePurchaseOrder(int orderId) {
        for (PurchaseOrder po : purchases) {
            if (po.getId() == orderId) {
                System.out.println("Order found. Proceeding to next processing steps");
                if (po.getStatus().equals("Received")) {
                    System.out.println("This order has already been received");
                    return;
                }
            }

            if (po.getStatus().equals("Pending")) {
                Product product = po.getProduct();
                int quantity = po.getQuantity();
                product.restock(quantity);

                System.out.println("The product has been restocked");
                po.setStatus("Received");
            }

        }

        System.out.println("No purchase found with order number:" + orderId);
    }
}
