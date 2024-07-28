package Exercise1_Design_Patterns.Structural.Decorator_Pattern;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ECommerceBillingSystem {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        List<Product> products = new ArrayList<>();
        double total = 0;

        System.out.println("Welcome to the E-commerce Billing System.");

        // Adding products
        boolean addingProducts = true;
        while (addingProducts) {
            String description = getProductDescription();
            if (description.equalsIgnoreCase("done")) {
                addingProducts = false;
                break;
            }

            double price = getProductPrice();
            int quantity = getProductQuantity();

            Product product = new Product(description, price, quantity);

            product = applyDecorators(product);
            products.add(product);
            total += product.getPrice();
        }

        // Print the final bill
        Printer.printCartDetails(products, total);

        scanner.close();
    }

    private static String getProductDescription() {
        System.out.println("Enter product Name (or type 'done' to finish):");
        return scanner.nextLine();
    }

    private static double getProductPrice() {
        System.out.println("Enter product price:");
        double price = scanner.nextDouble();
        scanner.nextLine(); // consume newline
        return price;
    }

    private static int getProductQuantity() {
        System.out.println("Enter product quantity:");
        int quantity = scanner.nextInt();
        scanner.nextLine(); // consume newline
        return quantity;
    }

    private static Product applyDecorators(Product product) {
        System.out.println("Apply discount? (yes/no):");
        if (scanner.nextLine().equalsIgnoreCase("yes")) {
            double discountPercentage = getDiscountPercentage();
            product = new DiscountDecorator(product, discountPercentage);
        }

        System.out.println("Apply tax? (yes/no):");
        if (scanner.nextLine().equalsIgnoreCase("yes")) {
            double taxPercentage = getTaxPercentage();
            product = new TaxDecorator(product, taxPercentage);
        }

        System.out.println("Apply promotion? (yes/no):");
        if (scanner.nextLine().equalsIgnoreCase("yes")) {
            double promotionAmount = getPromotionAmount();
            product = new PromotionDecorator(product, promotionAmount);
        }

        return product;
    }

    private static double getDiscountPercentage() {
        System.out.println("Enter discount percentage:");
        double discountPercentage = scanner.nextDouble();
        scanner.nextLine(); // consume newline
        return discountPercentage;
    }

    private static double getTaxPercentage() {
        System.out.println("Enter tax percentage:");
        double taxPercentage = scanner.nextDouble();
        scanner.nextLine(); // consume newline
        return taxPercentage;
    }

    private static double getPromotionAmount() {
        System.out.println("Enter promotion amount:");
        double promotionAmount = scanner.nextDouble();
        scanner.nextLine(); // consume newline
        return promotionAmount;
    }
}
