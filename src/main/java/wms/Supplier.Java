package wms;

import java.util.ArrayList;

public class Supplier {
    private int id;
    private String name;
    private String contactInfo;
    private ArrayList<PurchaseOrder> orderHistory;

    public Supplier(int id, String name, String contactInfo) {
        this.id = id;
        this.name = name;
        this.contactInfo = contactInfo;
        this.orderHistory = new ArrayList<>();
    }
}
