package wms;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import wms.SupplierManager;

import static org.junit.jupiter.api.Assertions.*;

public class SupplierManagerTest {
     @Test
    public void addSupplierStoresNewSupplier() {
        SupplierManager manager = new SupplierManager();
        Supplier supplier = new Supplier(704, "FruitCo", "fruit@email.com");

        manager.addSupplier(supplier);
        assertEquals(1, manager.getSuppliers().size());
        assertEquals(supplier, manager.getSuppliers().get(0));
    }

    @Test
    public void addSupplierNullCheck() {
        SupplierManager manager = new SupplierManager();
        manager.addSupplier(null);
        assertEquals(0, manager.getSuppliers().size());
    }

    @Test
    public void addSupplierDuplicateCheck() {
        SupplierManager manager = new SupplierManager();
        Supplier s1 = new Supplier(705, "SupplierA", "a@email.com");
        Supplier s2 = new Supplier(705, "SupplierB", "b@email.com");

        manager.addSupplier(s1);
        manager.addSupplier(s2);
        assertEquals(1, manager.getSuppliers().size());
    }

    @Test
    public void updateSupplierUpdates() {
        SupplierManager manager = new SupplierManager();
        Supplier supplier = new Supplier(1, "Old", "old@email.com");

        manager.addSupplier(supplier);
        manager.updateSupplier(1, "New", "new@email.com");

        Supplier updated = manager.getSupplierById(1);
        assertEquals("New", updated.getName());
        assertEquals("new@email.com", updated.getContactInfo());
    }

    @Test
    public void removeSupplierDeletes() {
        SupplierManager manager = new SupplierManager();
        Supplier supplier = new Supplier(2, "RemoveMe", "remove@email.com");

        manager.addSupplier(supplier);
        manager.removeSupplier(2);

        assertEquals(0, manager.getSuppliers().size());
        assertNull(manager.getSupplierById(2));
    }

    @Test
    public void getSupplierByIdReturnsCorrectSupplier() {
        SupplierManager manager = new SupplierManager();
        Supplier supplier = new Supplier(101, "Test", "test@email.com");

        manager.addSupplier(supplier);
        Supplier result = manager.getSupplierById(101);

        assertNotNull(result);
        assertEquals(supplier, result);
    }

    @Test
    public void printSupplierOrderHistoryPrintsCorrectly() {
        SupplierManager manager = new SupplierManager();
        Supplier supplier = new Supplier(102, "PrintMe", "print@email.com");
        Product product = new Product(301, "Notebook", 100, 2.50, 10);
        PurchaseOrder order = new PurchaseOrder(201, supplier, product, 20);

        supplier.addOrder(order);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        manager.printSupplierOrderHistory(supplier);
        System.setOut(System.out);

        String output = outContent.toString();
        assertTrue(output.contains("Order history for: PrintMe"));
        assertTrue(output.contains("PO ID: 201"));
        assertTrue(output.contains("Notebook"));
        assertTrue(output.contains("Quantity: 20"));
        assertTrue(output.contains("Status: Pending"));
    }
}
