package Exercise1_Design_Patterns.Structural.Decorator_Pattern;

public class TaxDecorator extends ProductDecorator {
    private double taxPercentage;

    public TaxDecorator(Product product, double taxPercentage) {
        super(product);
        this.taxPercentage = taxPercentage;
    }

    @Override
    public double getTax() {
        return (super.getBasePrice() * taxPercentage / 100) * super.getQuantity();
    }

    @Override
    public double getPrice() {
        return super.getPrice() + getTax();
    }
}
