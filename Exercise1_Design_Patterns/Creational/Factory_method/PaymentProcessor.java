package Exercise1_Design_Patterns.Creational.Factory_method;

public interface PaymentProcessor {
    void processPayment(double amount);
    String getType();
}
