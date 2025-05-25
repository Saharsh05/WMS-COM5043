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
        if (supplier == null || quantity <= 0) {
            System.out.println("One of supplier or quantity is incorrect");
            return;
        }

        for (Supplier s : suppliers) {
            if (s.getName().equals(supplier.getName())) {
                System.out.println("The supplier already exsits");
                return;
            }
        }

        for (Product p : products) {
            if (p.getName().equals(product.getName())) {
                System.out.println("This product already exists");
                return;
            }
        }

        PurchaseOrder po = new PurchaseOrder(orderId, supplier, product, quantity);
        purchases.add(po);

        supplier.addOrder(po);

        System.out.println("Purchase order created successfully");

    }
}
