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
}
