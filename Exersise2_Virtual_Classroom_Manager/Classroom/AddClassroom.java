package Classroom;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

// Manages classroom information and interactions with the classroom file
public class AddClassroom {
    private Map<String, String> classrooms;
    private static final String FILE_NAME = "./Exersise2_Virtual_Classroom_Manager/Classroom/classrooms.txt";
    private static final Logger logger = Logger.getLogger(AddClassroom.class.getName());

    public AddClassroom(ClassroomStorage storage) {
        classrooms = new HashMap<>();
        loadClassroomsFromFile();
    }

    // Adds a new classroom if it does not already exist
    public void addClassroom(String classroomName) {
        String lowerCaseName = classroomName.toLowerCase();
        
        if (doesClassroomExist(lowerCaseName)) {
            System.out.println("Warning: Classroom already exists: " + classroomName);
            logger.log(Level.WARNING, "Attempt to add an existing classroom: {0}", classroomName);
            return;
        }

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        classrooms.put(lowerCaseName, timestamp);
        saveClassroomToFile(lowerCaseName, timestamp);
        System.out.println("Classroom added: " + classroomName);
        logger.log(Level.INFO, "Classroom added: {0}", classroomName);
    }

    // Get all classrooms
    public Map<String, String> getClassrooms() {
        return classrooms;
    }

    // Check if a classroom exists
    public boolean doesClassroomExist(String classroomName) {
        return classrooms.containsKey(classroomName.toLowerCase());
    }

    
    // Check if all given classroom names are valid
    public boolean areClassroomsValid(String[] classNames) {
        for (String className : classNames) {
            if (!doesClassroomExist(className.trim())) {
                return false;
            }
        }
        return true;
    }

    private void loadClassroomsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", 2);
                if (parts.length == 2) {
                    classrooms.put(parts[0].trim(), parts[1].trim());
                }
            }
            logger.log(Level.INFO, "Classrooms loaded from file");
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error reading classroom file: {0}", e.getMessage());
        }
    }

    private void saveClassroomToFile(String classroomName, String timestamp) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(classroomName + "," + timestamp);
            writer.newLine();
            logger.log(Level.INFO, "Classroom saved to file: {0} at {1}", new Object[]{classroomName, timestamp});
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error writing to classroom file: {0}", e.getMessage());
        }
    }
}
