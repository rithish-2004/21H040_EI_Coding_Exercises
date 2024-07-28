package Exercise1_Design_Patterns.Creational.Factory_method;

// Concrete Product for Bitcoin
public class BitcoinProcessor implements PaymentProcessor {
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing " + amount + "Bitcoin");
    }

    @Override
    public String getType() {
        return "Bitcoin Processor";
    }
}
