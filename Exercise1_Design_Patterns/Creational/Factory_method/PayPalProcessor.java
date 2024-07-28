package Exercise1_Design_Patterns.Creational.Factory_method;

// Concrete Product for PayPal
public class PayPalProcessor implements PaymentProcessor {
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing PayPal payment of Rs. " + amount);
    }

    @Override
    public String getType() {
        return "PayPal Processor";
    }
}
