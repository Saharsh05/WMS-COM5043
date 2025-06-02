package wms;

import org.junit.jupiter.api.Test;


import wms.InventoryManager;

import static org.junit.jupiter.api.Assertions.*;

public class InventoryManagerTest {
     @Test
    public void addProductStoresNewProduct() {
        InventoryManager manager = new InventoryManager();
        Product product = new Product(601, "Pencil", 10, 0.50, 2);
        manager.addProduct(product);

        List<Product> storedProducts = manager.getProducts();
        assertEquals(1, storedProducts.size());
        assertEquals(product, storedProducts.get(0));
    }

    @Test
    public void addProductNullProductTest() {
        InventoryManager manager = new InventoryManager();
        manager.addProduct(null);
        assertEquals(0, manager.getProducts().size());
    }

    @Test
    public void addProductCheckMultipleIDs() {
        InventoryManager manager = new InventoryManager();
        Product product1 = new Product(603, "Ruler", 30, 0.35, 5);
        Product product2 = new Product(603, "Duplicate", 20, 0.99, 2);

        manager.addProduct(product1);
        manager.addProduct(product2);

        assertEquals(1, manager.getProducts().size());
        assertEquals(product1, manager.getProducts().get(0));
    }

    @Test
    public void getProductByIdReturnsCorrectProduct() {
        InventoryManager manager = new InventoryManager();
        Product product = new Product(604, "Pen", 15, 1.20, 3);
        manager.addProduct(product);

        Product found = manager.getProductById(604);
        assertNotNull(found);
        assertEquals("Pen", found.getName());
    }
}
