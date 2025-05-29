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
        PurchaseOrder porder = new PurchaseOrder(302, supplier, null, 10);

        assertEquals(0.0, porder.calculateTotal());

    }

    @Test
    public void calculateTotalNegativeQuantity(){
        Supplier supplier = new Supplier(503, "Nike", "Nike@gmail.com");
        Product product = new Product(403, "Shirt", 60, 25.00, 10);
        PurchaseOrder porder = new PurchaseOrder(303, supplier, product, -5);

        assertEquals(0.0, porder.calculateTotal());
    }

}
