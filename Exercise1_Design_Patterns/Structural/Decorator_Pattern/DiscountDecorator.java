package Exercise1_Design_Patterns.Structural.Decorator_Pattern;

public class DiscountDecorator extends ProductDecorator {
    private double discountPercentage;

    public DiscountDecorator(Product product, double discountPercentage) {
        super(product);
        this.discountPercentage = discountPercentage;
    }

    @Override
    public double getDiscount() {
        return (super.getBasePrice() * discountPercentage / 100) * super.getQuantity();
    }

    @Override
    public double getPrice() {
        return super.getPrice() - getDiscount();
    }
}
