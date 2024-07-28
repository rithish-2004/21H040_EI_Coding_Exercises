package Exercise1_Design_Patterns.Structural.Decorator_Pattern;

public class PromotionDecorator extends ProductDecorator {
    private double promotionAmount;

    public PromotionDecorator(Product product, double promotionAmount) {
        super(product);
        this.promotionAmount = promotionAmount;
    }

    @Override
    public double getPrice() {
        return super.getPrice() - promotionAmount;
    }
}
