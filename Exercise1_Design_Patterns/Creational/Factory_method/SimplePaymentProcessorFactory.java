package Exercise1_Design_Patterns.Creational.Factory_method;

import java.util.logging.Logger;

// Concrete Factory
public class SimplePaymentProcessorFactory extends PaymentProcessorFactory {
    private static final Logger logger = Logger.getLogger(SimplePaymentProcessorFactory.class.getName());

    @Override
    public PaymentProcessor createPaymentProcessor(String type) {
        PaymentProcessor processor = null;
        switch (type.toLowerCase()) {
            case "creditcard":
                processor = new CreditCardProcessor();
                break;
            case "paypal":
                processor = new PayPalProcessor();
                break;
            case "bitcoin":
                processor = new BitcoinProcessor();
                break;
            default:
                logger.warning("Unknown payment processor type: " + type);
                throw new IllegalArgumentException("Unknown payment processor type: " + type);
        }
        logger.info("Created " + processor.getType());
        return processor;
    }
}
