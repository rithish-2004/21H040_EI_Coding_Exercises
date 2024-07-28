package Exercise1_Design_Patterns.Behavioural.Command_Pattern;

public class LightCommand implements Command {
    private boolean on;
    public static boolean currentState = false; // Default to OFF

    public LightCommand(boolean on) {
        this.on = on;
        currentState = on;
    }

    public boolean isOn() {
        return currentState;
    }

    @Override
    public void execute() {
        if (on) {
            System.out.println("Light is ON");
        } else {
            System.out.println("Light is OFF");
        }
    }
}
