package Exercise1_Design_Patterns.Behavioural.Observer_Pattern;
public interface Subject {
    void attach(Observer o);
    void detach(Observer o);
    void notifyObservers();
}
