package wms;

import org.junit.jupiter.api.Test;

import wms.Product;

import static org.junit.jupiter.api.Assertions.*;


public class ProductTest {

    @Test
    public void testRestockIncreasesQuantity() {
        // Arrange
        Product product = new Product(101, "Banana", 5, 0.99, 2);

        // Act
        product.restock(3);

        // Assert
        assertEquals(8, product.getQuantity());
    }

      @Test
    public void isLowStockTrue() {
        Product product = new Product(102, "Apple", 2, 1.50, 5);
        assertTrue(product.isLowStock());
    }

    @Test
    public void isLowStockFalse() {
        Product product = new Product(103, "Orange", 10, 1.20, 3);
        assertFalse(product.isLowStock());
    }

    @Test
    public void isQuantityValidated(){
        Product product = new Product(104, "Brry", 10, 1, 6);

        product.setQuantity(0);

        assertEquals(0, product.getQuantity());
    }

    @Test
    public void setQuantityNegative(){
        Product product = new Product(105, "Pear", 10, 1.35, 3);

        product.setQuantity(-5);

        assertEquals(10, product.getQuantity());

    }

     @Test
    public void restockNegative() {
        Product product = new Product(106, "Mango", 10, 2.00, 10);

        product.restock(-5);  // Act

        assertEquals(10, product.getQuantity());  // Assert
    }

    @Test 
    
    public void getPriceCorrect() {
        Product product = new Product(107, "Guava", 20, 1.50, 10);

        assertEquals(1.50, product.getPrice());
    }


    @Test

    public void checkGettersValues() {
        Product product = new Product(108, "Dates", 30, 2.00, 10);

        assertEquals(108, product.getId());
        assertTrue(product.getName().equalsIgnoreCase("dates"));
        assertEquals(30, product.getQuantity());
        assertEquals(2.00, product.getPrice());
        assertEquals(10, product.getLowStock());
    }

}
