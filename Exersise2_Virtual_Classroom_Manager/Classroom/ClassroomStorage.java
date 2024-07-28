package Classroom;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClassroomStorage {
    private static final String FILE_NAME = "./Exersise2_Virtual_Classroom_Manager/Classroom/classrooms.txt";
    private static final Logger logger = Logger.getLogger(ClassroomStorage.class.getName());

    // Load classrooms from file
    public Map<String, String> loadClassroomsFromFile() {
        Map<String, String> classrooms = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", 2);
                if (parts.length == 2) {
                    classrooms.put(parts[0].trim(), parts[1].trim());
                }
            }
            logger.log(Level.INFO, "Classrooms loaded from file");
        } catch (FileNotFoundException e) {
            logger.log(Level.WARNING, "Classroom file not found: {0}", FILE_NAME);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error reading classroom file: {0}", e.getMessage());
        }
        return classrooms;
    }

    // Save classrooms to file
    public void saveClassroomsToFile(Map<String, String> classrooms) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Map.Entry<String, String> entry : classrooms.entrySet()) {
                writer.write(entry.getKey() + "," + entry.getValue());
                writer.newLine();
            }
            logger.log(Level.INFO, "Classrooms saved to file");
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error writing to classroom file: {0}", e.getMessage());
        }
    }
}
