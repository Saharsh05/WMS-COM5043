package wms;
// Represents a product in the warehouse or inventory
public class Product {
    private int id;
    private String name;
    private int quantity;
    private double price;
    private int lowStock;

    // Constructor to initialise all product attributes
    public Product(int id, String name, int quantity, double price, int lowStock) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.lowStock = lowStock;
    }

    // Returns the product ID
    public int getId() {
        return id;
    }

    // Returns the product name
    public String getName() {
        return name;
    }

    // Returns the current stock level
    public int getQuantity() {
        return quantity;
    }

    // Updates the stock level if the new quantity is valid
    public void setQuantity(int newQuantity) {
        if(newQuantity >= 0){
        this.quantity = newQuantity;
        }
        else{
            System.out.println("Quantity cannot be negative");
        }
    }

    // Returns the unit price
    public double getPrice() {
        return price;
    }

    // Returns the low stock threshold
    public int getLowStock() {
        return lowStock;
    }

    // Adds more stock, provided the restock amount is positive
    public void restock(int amount) {
        if (amount >= 0) {
            quantity += amount;
        } else {
            System.out.println("Invalid restock amount: must be positive");
        }
    }

    // Determines whether the stock is at or below the low stock level
    public boolean isLowStock() {
        return quantity <= lowStock;
    }

    // Returns a string representation of the product for display/logging
    @Override
    public String toString() {
        return "Product [ID=" + id +
                ", Name='" + name + '\'' +
                ", Quantity=" + quantity +
                ", Price=£" + String.format("%.2f", price) +
                ", LowStockThreshold=" + lowStock + "]";
    }

}
