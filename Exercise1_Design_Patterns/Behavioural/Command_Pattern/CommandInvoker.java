package Exercise1_Design_Patterns.Behavioural.Command_Pattern;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class CommandInvoker {
    private List<Command> commands = new ArrayList<>();
    private static final String FILE_NAME = "commands.txt";

    public void addCommand(Command command) {
        commands.add(command);
    }

    public void executeCommands() {
        for (Command command : commands) {
            command.execute();
        }
    }

    public void saveCommandsToFile() {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(FILE_NAME)))) {
            for (Command command : commands) {
                if (command instanceof LightCommand) {
                    LightCommand lightCommand = (LightCommand) command;
                    writer.println("Light," + (lightCommand.isOn() ? "on" : "off"));
                } else if (command instanceof ThermostatCommand) {
                    ThermostatCommand thermostatCommand = (ThermostatCommand) command;
                    writer.println("Thermostat," + thermostatCommand.getTemperature());
                } else if (command instanceof DoorLockCommand) {
                    DoorLockCommand doorLockCommand = (DoorLockCommand) command;
                    writer.println("DoorLock," + (doorLockCommand.isLocked() ? "lock" : "unlock"));
                }
            }
        } catch (IOException e) {
            System.err.println("Error saving commands to file: " + e.getMessage());
        }
    }

    public void loadCommandsFromFile() {
        // For simplicity, we will not implement this method as it involves parsing the file and re-creating commands
        // This is a placeholder for loading commands from file if needed
    }

    public void printCurrentState() {
        System.out.println("Current states:");
        System.out.println("Light is " + (LightCommand.currentState ? "ON" : "OFF"));
        System.out.println("Thermostat is set to " + ThermostatCommand.currentTemperature + "°C");
        System.out.println("Door is " + (DoorLockCommand.currentLockState ? "LOCKED" : "UNLOCKED"));
    }
}
