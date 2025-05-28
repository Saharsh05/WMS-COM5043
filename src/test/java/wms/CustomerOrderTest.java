package wms;

import org.junit.jupiter.api.Test;

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
}
