package wms;

public class Product {
    private int id;
    private String name;
    private int quantity;
    private double price;
    private int lowStock;

    public Product(int id, String name, int quantity, double price, int lowStock) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.lowStock = lowStock;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int newQuantity) {
        if(newQuantity >= 0){
        this.quantity = newQuantity;
        }
        else{
            System.out.println("Quantity cannot be negative");
        }
    }

    public double getPrice() {
        return price;
    }

    public int getLowStock() {
        return lowStock;
    }

    public void restock(int amount) {
        if (amount >= 0) {
            quantity += amount;
        } else {
            System.out.println("Invalid restock amount: must be positive");
        }
    }

    public boolean isLowStock() {
        return quantity <= lowStock;
    }

    @Override
    public String toString() {
        return "Product [ID=" + id +
                ", Name='" + name + '\'' +
                ", Quantity=" + quantity +
                ", Price=£" + String.format("%.2f", price) +
                ", LowStockThreshold=" + lowStock + "]";
    }

}
