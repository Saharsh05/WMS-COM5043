package wms;

import java.util.ArrayList;
import java.util.List;

public class SupplierManager {
    private List<Supplier> suppliers;

    public SupplierManager() {
        this.suppliers = new ArrayList<>();
    }

    public void addSupplier(Supplier supplier) {
        if (supplier == null) {
            System.out.println("The supplier doesn't exist");
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

    public void removeSupplier(int id) {
        Supplier toRemove = getSupplierById(id);
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

    public Supplier getSupplierById(int id) {
        for (Supplier s : suppliers) {
            if (s.getId() == id) return s;
        }
        return null;
    }

    public void printSupplierOrderHistory(Supplier supplier) {
        System.out.println("Order history for: " + supplier.getName());
        for (PurchaseOrder po : supplier.getOrderHistory()) {
            System.out.println("PO ID: " + po.getId() +
                               ", Product: " + po.getProduct().getName() +
                               ", Quantity: " + po.getQuantity() +
                               ", Status: " + po.getStatus());
        }
    }

    public List<Supplier> getSuppliers() {
        return suppliers;
    }
}
