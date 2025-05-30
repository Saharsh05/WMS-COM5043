package wms;
import java.util.List;
import org.junit.jupiter.api.Test;

import src.main.java.wms.Supplier;
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

}
