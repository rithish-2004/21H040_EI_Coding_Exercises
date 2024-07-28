package Exercise1_Design_Patterns.Behavioural.Command_Pattern;

public class ThermostatCommand implements Command {
    private int temperature;
    public static int currentTemperature = 20; // Default temperature

    public ThermostatCommand(int temperature) {
        this.temperature = temperature;
        currentTemperature = temperature;
    }

    public int getTemperature() {
        return currentTemperature;
    }

    @Override
    public void execute() {
        System.out.println("Thermostat temperature set to " + temperature + "°C");
    }
}
