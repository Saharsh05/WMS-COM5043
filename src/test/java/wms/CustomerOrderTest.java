package wms;

import org.junit.jupiter.api.Test;

import wms.Product;
import wms.CustomerOrder;

import static org.junit.jupiter.api.Assertions.*;


public class CustomerOrderTest {

    @Test
    public void addProductCorrectValue(){
        CustomerOrder order = new CustomerOrder(301);
        Product product = new Product(201, "Strawberry",0, 1.99, 10);

        order.addProduct(product, 5);

        assertEquals(5, order.getItems().get(product));
    }

    @Test
    public void calculateTotalCorrectAmount(){
        CustomerOrder order = new CustomerOrder(401);
        Product product = new Product(202, "Hammer", 100, 10.00, 50);


        order.addProduct(product, 100);
        assertEquals(1000.00, order.calculateTotal());

    }

    @Test
    public void getItemsReturnsCorrect(){
        CustomerOrder order = new CustomerOrder(402);
        Product product = new Product(203, "Screwdriver", 50, 5.00, 10);

        order.addProduct(product, 25);

        assertEquals(25, order.getItems().get(product));
    }
    
    @Test
    public void getTotalPriceCheck(){
        CustomerOrder order = new CustomerOrder(403);
        Product product = new Product(204, "KitKat", 25, 1.99, 5);

        order.addProduct(product, 5);

        assertEquals(9.95, order.getTotalPrice());
    }

    @Test
    public void addProductNegativeQuantity(){
        CustomerOrder order = new CustomerOrder(404);
        Product product = new Product(405, "Maltesers", 30, 2.65, 10);

        order.addProduct(product, -5);

        assertFalse(order.getItems().containsKey(product));
        assertEquals(0.0, order.calculateTotal());

    }

    @Test
    public void addProductNullProduct(){
        CustomerOrder order = new CustomerOrder(405);
        Product product = new Product(406, "Snickers", 40, 3.00, 20);

        order.addProduct(null, 10);

        assertTrue(order.getItems().isEmpty());
    }
    
}
