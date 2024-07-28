package Exercise1_Design_Patterns.Structural.Decorator_Pattern;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<Product> products;

    public ShoppingCart() {
        this.products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public double calculateTotal() {
        double total = 0;
        for (Product product : products) {
            total += product.getPrice();
        }
        return total;
    }

    public void showCartDetails() {
        for (Product product : products) {
            System.out.println(product.getDescription() + ": $" + product.getPrice());
        }
    }
}
