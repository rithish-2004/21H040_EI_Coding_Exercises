package Exercise1_Design_Patterns.Structural.Composite_Pattern;

public abstract class Component {
    protected String name;

    public Component(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract void play();

    @Override
    public String toString() {
        return name;
    }
}
