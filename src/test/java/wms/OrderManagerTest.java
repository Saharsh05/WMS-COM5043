package wms;

import org.junit.jupiter.api.Test;


import wms.OrderManager;

import static org.junit.jupiter.api.Assertions.*;

public class OrderManagerTest {
     @Test
    public void createPurchaseOrderWorks() {
        InventoryManager inv = new InventoryManager();
        SupplierManager sup = new SupplierManager();
        FinanceManager fin = new FinanceManager();
        OrderManager ord = new OrderManager(inv, sup, fin);

        Product product = new Product(601, "Pencil", 10, 0.50, 2);
        Supplier supplier = new Supplier(705, "Temu", "temu@email.com");

        inv.addProduct(product);
        sup.addSupplier(supplier);

        ord.createPurchaseOrder(801, supplier, product, 5);
        assertEquals(1, ord.getPurchases().size());
    }

    @Test
    public void receivePurchaseOrderUpdates() {
        InventoryManager inv = new InventoryManager();
        SupplierManager sup = new SupplierManager();
        FinanceManager fin = new FinanceManager();
        OrderManager ord = new OrderManager(inv, sup, fin);

        Product product = new Product(602, "Compass", 100, 2.50, 25);
        Supplier supplier = new Supplier(804, "MathsCo", "maths@email.com");

        inv.addProduct(product);
        sup.addSupplier(supplier);
        ord.createPurchaseOrder(101, supplier, product, 10);

        ord.receivePurchaseOrder(101);

        assertEquals(110, product.getQuantity());
        assertEquals("Received", ord.getPurchases().get(0).getStatus());
        assertEquals(25.0, fin.getTotalExpenses());
    }

    @Test
    public void addCustomerOrderWorks() {
        OrderManager ord = new OrderManager(new InventoryManager(), new SupplierManager(), new FinanceManager());
        CustomerOrder order = new CustomerOrder(303);
        ord.addCustomerOrder(order);
        assertEquals(1, ord.getCustomerOrders().size());
    }

    @Test
    public void processCustomerOrderUpdatesStockAndRevenue() {
        InventoryManager inv = new InventoryManager();
        SupplierManager sup = new SupplierManager();
        FinanceManager fin = new FinanceManager();
        OrderManager ord = new OrderManager(inv, sup, fin);

        Product product = new Product(801, "Notebook", 50, 2.00, 10);
        inv.addProduct(product);

        CustomerOrder order = new CustomerOrder(1001);
        order.addProduct(product, 10);

        ord.processCustomerOrder(order);

        assertEquals(40, product.getQuantity());
        assertEquals(20.0, fin.getTotalRevenue());
        assertEquals(1, ord.getCustomerOrders().size());
    }

    @Test
    public void processCustomerOrderTriggersLowStockWarning() {
        InventoryManager inv = new InventoryManager();
        SupplierManager sup = new SupplierManager();
        FinanceManager fin = new FinanceManager();
        OrderManager ord = new OrderManager(inv, sup, fin);

        Product product = new Product(101, "Notebook", 11, 1.50, 10);
        inv.addProduct(product);

        CustomerOrder order = new CustomerOrder(201);
        order.addProduct(product, 2);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        ord.processCustomerOrder(order);
        System.setOut(System.out);

        assertTrue(out.toString().contains("Warning: Stock for product Notebook is now low (9 units remaining)."));
    }
}
