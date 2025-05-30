package wms;
import java.util.List;
import org.junit.jupiter.api.Test;

<<<<<<< HEAD
=======
import src.main.java.wms.CustomerOrder;
import src.main.java.wms.Product;
import src.main.java.wms.PurchaseOrder;
>>>>>>> 9ce4aa0f6413f8dce8ce0a6f18620448d135eb48
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
}
