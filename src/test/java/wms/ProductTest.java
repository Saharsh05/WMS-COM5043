package wms;

import org.junit.jupiter.api.Test;
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
}
