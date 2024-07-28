package Exercise1_Design_Patterns.Structural.Decorator_Pattern;

public class Product {
    private String description;
    private double basePrice;
    private int quantity;

    public Product(String description, double basePrice, int quantity) {
        this.description = description;
        this.basePrice = basePrice;
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return basePrice * quantity;
    }

    public double getDiscount() {
        return 0;
    }

    public double getTax() {
        return 0;
    }
}
