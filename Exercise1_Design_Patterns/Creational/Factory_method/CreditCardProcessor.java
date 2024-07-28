package Exercise1_Design_Patterns.Creational.Factory_method;

// Concrete Product for Credit Card
public class CreditCardProcessor implements PaymentProcessor {
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing Credit Card payment of Rs. " + amount);
    }

    @Override
    public String getType() {
        return "Credit Card Processor";
    }
}
