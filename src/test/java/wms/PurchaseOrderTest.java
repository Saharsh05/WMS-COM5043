package wms;

import org.junit.jupiter.api.Test;


import wms.PurchaseOrder;

import static org.junit.jupiter.api.Assertions.*;



public class PurchaseOrderTest {
    @Test
    public void calculateTotalTest(){
        Supplier supplier = new Supplier(501,"SportDirect", "contactsports@gmail.com" );
        Product product = new Product(401, "Racket", 50, 10.00, 10);
        PurchaseOrder pOrder = new PurchaseOrder(301, supplier, product, 10 );
        

        assertEquals(100, pOrder.calculateTotal());
    }


    @Test
    public void calculateTotalNull(){
        Supplier supplier = new Supplier(502, "JD", "JD@gmail.com");
        Product product = new Product(402, "Football", 50, 20.00, 5);
        PurchaseOrder pOrder = new PurchaseOrder(302, supplier, null, 10);

        assertEquals(0.0, pOrder.calculateTotal());

    }

    @Test
    public void calculateTotalNegativeQuantity(){
        Supplier supplier = new Supplier(503, "Nike", "Nike@gmail.com");
        Product product = new Product(403, "Shirt", 60, 25.00, 10);
        PurchaseOrder pOrder = new PurchaseOrder(303, supplier, product, -5);

        assertEquals(0.0, pOrder.calculateTotal());
    }

    @Test
    public void calculateTotalNegativePrice(){
        Supplier supplier = new Supplier(504, "Addidas", "Addidas@gmail.com");
        Product product = new Product(404, "Shorts", 100, -4.50, 10);
        PurchaseOrder pOrder = new PurchaseOrder(303, supplier, product, 10);

        assertEquals(0.0, pOrder.calculateTotal());
    }

    @Test
    public void getStatusCorrectValue(){
        Supplier supplier = new Supplier(505, "FootballCo", "FootballCo@gmail.com");
        Product product = new Product(405, "Boots", 200, 50, 45);
        PurchaseOrder  porder = new PurchaseOrder(305, supplier, product, 10);

        assertEquals("Pending", porder.getStatus());
    }

    @Test
    public void setStatusCorrectValue(){
        Supplier supplier = new Supplier(506, "Athletics", "Athletics@gmail.com");
        Product product = new Product(406, "Shotput", 200, 50, 45);
        PurchaseOrder  porder = new PurchaseOrder(306, supplier, product, 10);

        porder.setStatus("Received");
        assertEquals("Received", porder.getStatus());
    }
}
