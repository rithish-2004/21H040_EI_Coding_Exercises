package Exercise1_Design_Patterns.Behavioural.Command_Pattern;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CommandInvoker invoker = new CommandInvoker();
        Scanner scanner = new Scanner(System.in);

        // Load previously saved commands
        invoker.loadCommandsFromFile();

        while (true) {
            System.out.println("Select a command: (1) Light (2) Thermostat (3) DoorLock (4) Exit");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (choice == 4) {
                break;
            }

            switch (choice) {
                case 1:
                    System.out.println("Enter action for Light (on/off):");
                    String lightAction = scanner.nextLine();
                    invoker.addCommand(new LightCommand(lightAction.equals("on")));
                    break;
                case 2:
                    System.out.println("Enter temperature for Thermostat:");
                    int temperature = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    invoker.addCommand(new ThermostatCommand(temperature));
                    break;
                case 3:
                    System.out.println("Enter action for DoorLock (lock/unlock):");
                    String doorAction = scanner.nextLine();
                    invoker.addCommand(new DoorLockCommand(doorAction.equals("lock")));
                    break;
                default:
                    System.out.println("Invalid choice.");
            }

            // Execute commands and save to file
            invoker.executeCommands();
            invoker.saveCommandsToFile();
        }

        // Print current state of devices
        invoker.printCurrentState();
        scanner.close();
    }
}
