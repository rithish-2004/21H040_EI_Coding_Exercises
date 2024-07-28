package Exercise1_Design_Patterns.Structural.Decorator_Pattern;

import java.util.List;

public class Printer {
    
    public static void printCartDetails(List<Product> products, double total) {
        System.out.printf("%-30s %-10s %-10s %-10s %-10s\n", 
                "Product Description", "Base Price", "Discount", "Tax", "Final Price");
        System.out.println("-------------------------------------------------------------------------------");
        
        for (Product product : products) {
            // Ensure that the product is decorated to include all applied decorators
            double basePrice = product.getBasePrice();
            double discount = product.getDiscount();
            double tax = product.getTax();
            double finalPrice = product.getPrice();
            
            System.out.printf("%-30s %-10.2f %-10.2f %-10.2f %-10.2f\n", 
                    product.getDescription(), basePrice, discount, tax, finalPrice);
        }
        
        System.out.println("-------------------------------------------------------------------------------");
        System.out.printf("%-30s %-10s\n", "Total", String.format("%.2f", total));
    }
}
