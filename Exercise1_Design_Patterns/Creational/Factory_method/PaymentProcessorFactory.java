package Exercise1_Design_Patterns.Creational.Factory_method;

// Abstract Creator
public abstract class PaymentProcessorFactory {
    public abstract PaymentProcessor createPaymentProcessor(String type);
}
