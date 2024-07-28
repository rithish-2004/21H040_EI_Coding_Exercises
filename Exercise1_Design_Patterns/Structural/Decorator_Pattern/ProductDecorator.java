package Exercise1_Design_Patterns.Structural.Decorator_Pattern;

public abstract class ProductDecorator extends Product {
    protected Product product;

    public ProductDecorator(Product product) {
        super(product.getDescription(), product.getBasePrice(), product.getQuantity());
        this.product = product;
    }

    @Override
    public double getBasePrice() {
        return product.getBasePrice();
    }

    @Override
    public double getPrice() {
        return product.getPrice();
    }

    @Override
    public double getDiscount() {
        return product.getDiscount();
    }

    @Override
    public double getTax() {
        return product.getTax();
    }
}
