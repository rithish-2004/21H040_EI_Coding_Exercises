package Exercise1_Design_Patterns.Creational.Factory_method;

import java.util.Scanner;

// Main application class
public class PaymentProcessorManager {

    public static void main(String[] args) {
        PaymentProcessorFactory factory = new SimplePaymentProcessorFactory();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Payment Gateway System.");
        System.out.println("Enter payment processor type (CreditCard/PayPal/Bitcoin):");
        String processorType = scanner.nextLine();

        System.out.println("Enter the amount to process:");
        double amount = scanner.nextDouble();

        try {
            PaymentProcessor processor = factory.createPaymentProcessor(processorType);
            processor.processPayment(amount);
            System.out.println("Payment processed using: " + processor.getType());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        scanner.close();
    }
}
