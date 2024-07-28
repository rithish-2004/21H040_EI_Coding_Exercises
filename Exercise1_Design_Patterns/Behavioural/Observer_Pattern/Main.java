package Exercise1_Design_Patterns.Behavioural.Observer_Pattern;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        SoilMoistureSensor soilMoistureSensor = new SoilMoistureSensor();
        IrrigationSystem irrigationSystem = new IrrigationSystem();
        soilMoistureSensor.attach(irrigationSystem);

        NutrientSensor nutrientSensor = new NutrientSensor();
        FertilizerSystem fertilizerSystem = new FertilizerSystem();
        nutrientSensor.attach(fertilizerSystem);

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter soil moisture state (true for dry, false for wet):");
            boolean dry = scanner.nextBoolean();
            soilMoistureSensor.setDry(dry);

            System.out.println("Enter nutrient level state (true for low, false for normal):");
            boolean lowNutrients = scanner.nextBoolean();
            nutrientSensor.setLowNutrients(lowNutrients);
        }

        List<String> states = new ArrayList<>();
        states.add("Soil Moisture: " + (soilMoistureSensor.isDry() ? "Dry" : "Wet"));
        states.add("Nutrient Level: " + (nutrientSensor.isLowNutrients() ? "Low" : "Normal"));

        try {
            FileStorage.saveStateToFile("./Exercise1_Design_Patterns/Behavioural/Observer_Pattern/system_state.txt", states);
        } catch (IOException e) {
            logger.severe("Error saving state to file: " + e.getMessage());
        }

        try {
            List<String> loadedStates = FileStorage.loadStateFromFile("./Exercise1_Design_Patterns/Behavioural/Observer_Pattern/system_state.txt");
            System.out.println("Current states from file:");
            for (String state : loadedStates) {
                System.out.println(state);
            }
        } catch (IOException e) {
            logger.severe("Error loading state from file: " + e.getMessage());
        }
    }
}
