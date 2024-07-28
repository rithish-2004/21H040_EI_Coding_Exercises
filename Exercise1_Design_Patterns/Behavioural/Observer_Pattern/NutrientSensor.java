package Exercise1_Design_Patterns.Behavioural.Observer_Pattern;

public class NutrientSensor extends ConcreteSubject {
    private boolean lowNutrients;

    public boolean isLowNutrients() {
        return lowNutrients;
    }

    public void setLowNutrients(boolean lowNutrients) {
        this.lowNutrients = lowNutrients;
        notifyObservers();
    }
}
