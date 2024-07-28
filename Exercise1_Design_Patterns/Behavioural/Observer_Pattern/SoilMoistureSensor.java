package Exercise1_Design_Patterns.Behavioural.Observer_Pattern;

public class SoilMoistureSensor extends ConcreteSubject {
    private boolean dry;

    public boolean isDry() {
        return dry;
    }

    public void setDry(boolean dry) {
        this.dry = dry;
        notifyObservers();
    }
}
