package wms;
import java.util.List;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


import wms.WarehouseManager;

import static org.junit.jupiter.api.Assertions.*;



public class WarehouseManagerTest {
    @Test
    public void addProductStoresNewProduct(){
        WarehouseManager wManager = new WarehouseManager();
        Product product = new Product(601, "Pencil", 10, 0.50, 2);
        wManager.addProduct(product);

        List<Product> storedProducts = wManager.getProducts();

        assertEquals(1, storedProducts.size());
        assertEquals(product, storedProducts.get(0));
    }


    @Test
    public void addProductNullProductTest(){
        WarehouseManager wManager = new WarehouseManager();
        Product product = new Product(602, "Pen", 20, 2.50, 2);
        wManager.addProduct(null);
        List<Product> storeProducts = wManager.getProducts();

        assertEquals(0, storeProducts.size());
        
    }

    @Test
    public void addProductCheckMultipleIDs(){
        WarehouseManager wManager = new WarehouseManager();
        Product product = new Product(603, "Ruler", 30, 0.35, 5);
        Product product1 = new Product(603, "Ruler", 40, 0.45, 10);
        
        wManager.addProduct(product);
        wManager.addProduct(product1);

        List<Product> storeProducts = wManager.getProducts();

        assertEquals(1, storeProducts.size());
        assertEquals(product, storeProducts.get(0));

    }

    @Test
    public void addSupplierStoresNewSupplier(){

        WarehouseManager wManager = new WarehouseManager();
        Supplier supplier = new Supplier(704, "FruitCo", "FruitCo@email.com");

        wManager.addSupplier(supplier);
        List<Supplier> storeSuppliers = wManager.getSuppliers();

        assertEquals(1, storeSuppliers.size());
        assertEquals(supplier, storeSuppliers.get(0));
    }

    @Test
    public void addSuppliersCheckNull(){
        WarehouseManager wManager = new WarehouseManager();
        Supplier supplier = new Supplier(705, "OrganicCo", "Organic@gmail.com");

        wManager.addSupplier(null);
        List<Supplier> storeSuppliers = wManager.getSuppliers();

        assertEquals(0, storeSuppliers.size());
    }

    @Test
    public void addSuppliersCheckSameID(){
        WarehouseManager wManager = new WarehouseManager();
        Supplier supplier = new Supplier(705, "Temu", "Temu@gmail.com");
        Supplier supplier1 = new Supplier(705, "Temu", "Temu@gmail.com");

        wManager.addSupplier(supplier);
        wManager.addSupplier(supplier1);

        List<Supplier> storeSuppliers = wManager.getSuppliers();

        assertEquals(1, storeSuppliers.size());
    }

    @Test
    public void createPurchaseOrderWorks(){
        WarehouseManager wManager = new WarehouseManager();
        Supplier supplier = new Supplier(705, "Temu", "Temu@gmail.com");
        Product product = new Product(601, "Pencil", 10, 0.50, 2);
        
        wManager.addSupplier(supplier);
        wManager.addProduct(product);
        wManager.createPurchaseOrder(801, supplier, product, 5);

        List<PurchaseOrder> storeOrders = wManager.getPurchases();

        assertEquals(1, storeOrders.size());
    }
    
    @Test
    public void createPurchaseOrderNullSupplier(){
        WarehouseManager wManager = new WarehouseManager();
        Product product = new Product(602, "Book", 50, 9.50, 15);

        
        wManager.addProduct(product);
        wManager.createPurchaseOrder(802, null, product, 10);

        List<PurchaseOrder> storeOrders = wManager.getPurchases();

        assertEquals(0, storeOrders.size());

    }

    @Test
    public void createPurchaseOrderNullProduct() {
        WarehouseManager wManager = new WarehouseManager();
        Supplier supplier = new Supplier(706, "PaperCo", "paperco@email.com");

        wManager.addSupplier(supplier);
        wManager.createPurchaseOrder(803, supplier, null, 10); // product is null

        List<PurchaseOrder> storeOrders = wManager.getPurchases();
        assertEquals(0, storeOrders.size());
    }

    @Test
    public void createPurchaseOrderInvalidQuantity(){
        WarehouseManager wManager = new WarehouseManager();
        Supplier supplier = new Supplier(803, "Sony", "Sony@gmail.com");
        Product product = new Product(603, "TV", 1000, 550, 200);

        wManager.addSupplier(supplier);
        wManager.addProduct(product);
        wManager.createPurchaseOrder(202, supplier, product, -5);

        List<PurchaseOrder> storeOrders = wManager.getPurchases();
        assertEquals(0, storeOrders.size());
    }

    @Test
    public void addCustomerOrderWorks(){
        WarehouseManager wManager = new WarehouseManager();
        CustomerOrder cOrder = new CustomerOrder(303);
        wManager.addCustomerOrder(cOrder);

        List<CustomerOrder> storeCustomer = wManager.getCustomerOrder();
        assertEquals(1, storeCustomer.size());
    }

    @Test
    public void addCustomerOrderNull(){
        WarehouseManager wManager = new WarehouseManager();
        wManager.addCustomerOrder(null);

        List<CustomerOrder> storeCustomer = wManager.getCustomerOrder();
        assertEquals(0, storeCustomer.size());
    }

    @Test
    public void receivePurchaseOrderUpdates(){
        WarehouseManager wManager = new WarehouseManager();
        Product product = new Product(602, "Compass", 100, 2.50, 25);
        Supplier supplier = new Supplier(804, "MathsCo", "MatchCo@email.com");

        wManager.addSupplier(supplier);
        wManager.addProduct(product);
        wManager.createPurchaseOrder(101, supplier, product, 10);
        double expectedExpense = 10 * 2.50;
        wManager.receivePurchaseOrder(101);
        PurchaseOrder pOrder = wManager.getPurchases().get(0);
        assertEquals(110, product.getQuantity());
        assertEquals("Received", pOrder.getStatus());
        assertEquals(expectedExpense, wManager.getFinancialReport().getTotalExpenses());
    }

    @Test
    public void receivePurchaseOrderAlreadyReceived(){
        WarehouseManager wManager = new WarehouseManager();
        Product product = new Product(701, "Eraser", 50, 1.00, 10);
        Supplier supplier = new Supplier(901, "StationeryCo", "supply@stationery.com");

        wManager.addSupplier(supplier);
        wManager.addProduct(product);

        wManager.createPurchaseOrder(201, supplier, product, 20);

        wManager.receivePurchaseOrder(201);

        int firstQuantity = product.getQuantity();

        double firstExpenses = wManager.getFinancialReport().getTotalExpenses();

        wManager.receivePurchaseOrder(201);

        assertEquals(firstQuantity, product.getQuantity());
        assertEquals(firstExpenses, wManager.getFinancialReport().getTotalExpenses());
 
    }

    @Test
    public void receivePurchaseOrderFailed(){
        WarehouseManager wManager = new WarehouseManager();
        Product product = new Product(702, "Calculator", 30, 15.00, 5);
        Supplier supplier = new Supplier(902, "EduTech", "contact@edutech.com");

        wManager.addSupplier(supplier);
        wManager.addProduct(product);

        wManager.receivePurchaseOrder(123);

        assertEquals(30, product.getQuantity());
        assertEquals(0, wManager.getPurchases().size());
        assertEquals(0.0, wManager.getFinancialReport().getTotalExpenses());
    }

    @Test
    public void processCustomerOrderUpdates(){
        wms.WarehouseManager wManager = new WarehouseManager();
        Product product = new Product(801, "Notebook", 50, 2.00, 10);
        CustomerOrder order = new CustomerOrder(1001);
        List<CustomerOrder> storedOrders = wManager.getCustomerOrder();
        
        wManager.addProduct(product);

        order.addProduct(product, 10);

        double expectedRevenue = 10 * 2;

        int remainingStock = 50 - 10;

        wManager.processCustomerOrder(order);

        assertEquals(expectedRevenue, wManager.getFinancialReport().getTotalRevenue());
        assertEquals(remainingStock, product.getQuantity());
        assertEquals(1, storedOrders.size());
        assertEquals(order, storedOrders.get(0));

    }

    @Test
    public void processCustomerOrderNullOrder(){
        WarehouseManager wManager = new WarehouseManager();
        Product product = new Product(802, "Envelope", 45, 0.35, 10);
        CustomerOrder order = null;
        wManager.processCustomerOrder(order);
        List<CustomerOrder> storedOrder = wManager.getCustomerOrder();

        wManager.processCustomerOrder(order);
        assertEquals(0, wManager.getFinancialReport().getTotalRevenue());
        assertEquals(45, product.getQuantity());
        assertEquals(0, storedOrder.size());
    }

    @Test
    public void processCustomerOrderEmptyItems() {
        WarehouseManager wManager = new WarehouseManager();

        CustomerOrder order = new CustomerOrder(1002); 

        wManager.processCustomerOrder(order);

        assertEquals(0.0, wManager.getFinancialReport().getTotalRevenue(), 0.01);
        assertEquals(0, wManager.getCustomerOrder().size());
    }

    @Test
    public void processCustomerOrderProductNotFound() {
        WarehouseManager wManager = new WarehouseManager();

        Product externalProduct = new Product(803, "Stapler", 20, 3.50, 5); 
        CustomerOrder order = new CustomerOrder(1003);
        order.addProduct(externalProduct, 5); 

        wManager.processCustomerOrder(order);

        assertEquals(0.0, wManager.getFinancialReport().getTotalRevenue(), 0.01);
        assertEquals(0, wManager.getCustomerOrder().size());
    }

    @Test
    public void processCustomerOrderNotEnoughStock() {
        WarehouseManager wManager = new WarehouseManager();

        Product product = new Product(804, "Marker", 5, 1.00, 2); 
        wManager.addProduct(product);

        CustomerOrder order = new CustomerOrder(1004);
        order.addProduct(product, 10); 

        wManager.processCustomerOrder(order);

        assertEquals(5, product.getQuantity());
        assertEquals(0.0, wManager.getFinancialReport().getTotalRevenue(), 0.01);
        assertEquals(0, wManager.getCustomerOrder().size());
    }

    @Test
    public void getSupplierByIdReturnsCorrectSupplier() {
        WarehouseManager manager = new WarehouseManager();
        Supplier supplier = new Supplier(101, "TestSupplier", "test@example.com");

        manager.addSupplier(supplier);

        Supplier result = manager.getSupplierById(101);

        assertNotNull(result);
        assertEquals(supplier, result);
    }

    @Test
    public void printSupplierOrderHistoryPrintsCorrectly() {
    
        WarehouseManager manager = new WarehouseManager();
        Supplier supplier = new Supplier(102, "PaperCo", "paperco@example.com");
        Product product = new Product(301, "Notebook", 100, 2.50, 10);
        PurchaseOrder order = new PurchaseOrder(201, supplier, product, 20);

        supplier.addOrder(order);

    
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

    
        manager.printSupplierOrderHistory(supplier);

    
        System.setOut(System.out);

    
        String output = outContent.toString();
        assertTrue(output.contains("Order history for: PaperCo"));
        assertTrue(output.contains("PO ID: 201"));
        assertTrue(output.contains("Notebook"));
        assertTrue(output.contains("Quantity: 20"));
        assertTrue(output.contains("Status: Pending"));
    }

    @Test
        public void updateSupplierUpdates() {
        WarehouseManager manager = new WarehouseManager();
        Supplier supplier = new Supplier(1, "OldName", "old@email.com");

        manager.addSupplier(supplier);
        manager.updateSupplier(1, "NewName", "new@email.com");

        Supplier updated = manager.getSupplierById(1);
        assertEquals("NewName", updated.getName());
        assertEquals("new@email.com", updated.getContactInfo());
    }

    @Test
    public void removeSupplierDeletes() {
    WarehouseManager manager = new WarehouseManager();
    Supplier supplier = new Supplier(2, "DeleteMe", "deleteme@email.com");

    manager.addSupplier(supplier);
    manager.removeSupplier(2);

    assertNull(manager.getSupplierById(2));
    assertEquals(0, manager.getSuppliers().size());
    }

    @Test
    public void processCustomerOrderTriggersLowStockWarning() {
        // Arrange
        WarehouseManager manager = new WarehouseManager();
        Product product = new Product(101, "Notebook", 11, 1.50, 10); 
        manager.addProduct(product);

        CustomerOrder order = new CustomerOrder(201);
        order.addProduct(product, 2);

        
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        
        manager.processCustomerOrder(order);

        
        System.setOut(System.out);

        
        String output = outContent.toString();
        assertTrue(output.contains("Warning: Stock for product Notebook is now low (9 units remaining)."));
    }




}
