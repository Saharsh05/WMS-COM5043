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

    public double getPrice() {
        return price;
    }

    public void restock(int amount) {
        if (amount >= 0) {
            quantity += amount;
        } else {
            System.out.println("Invalid restock amount: must be postive");
        }
    }

    public boolean isLowStock() {
        return quantity <= lowStock;
    }

}
