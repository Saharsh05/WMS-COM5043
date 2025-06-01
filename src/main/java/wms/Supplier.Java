package wms;

import java.util.ArrayList;

// Represents a supplier entity in the warehouse system
public class Supplier {
    private int id;
    private String name;
    private String contactInfo;
    private ArrayList<PurchaseOrder> orderHistory;

    // Constructor to initialise all fields
    public Supplier(int id, String name, String contactInfo) {
        this.id = id;
        this.name = name;
        this.contactInfo = contactInfo;
        this.orderHistory = new ArrayList<>();
    }

    // Returns the supplier's ID
    public int getId() {
        return id;
    }

    // Returns the supplier's name
    public String getName() {
        return name;
    }

    // Returns the supplier's contact details
    public String getContactInfo() {
        return contactInfo;
    }

    // Sets a different name for the supplier
    public void setName(String name) {
    this.name = name;
    }

    // Sets different contact information for the supplier
    public void setContactInfo(String contactInfo) {
    this.contactInfo = contactInfo;
    }


    // Returns the full list of purchase orders associated with this supplier
    public ArrayList<PurchaseOrder> getOrderHistory() {
        return orderHistory;
    }

    // Adds a new order to the supplier's order history, if not null
    public void addOrder(PurchaseOrder order) {

        if (order != null) {
            orderHistory.add(order);
        }

    }
}
