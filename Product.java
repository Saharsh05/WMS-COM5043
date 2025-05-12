public class Product {
    private int id;
    private String name;
    private int quantity;
    private int price;
    private int lowStock;

    public Product(int id, String name, int quantity, int price, int lowStock) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.lowStock = lowStock;
    }
}
