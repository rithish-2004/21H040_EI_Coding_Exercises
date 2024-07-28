package Classroom;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

// Handles the removal of classrooms
public class RemoveClassroom {
    private AddClassroom addClassroom;
    private static final Logger logger = Logger.getLogger(RemoveClassroom.class.getName());
    private static final String FILE_NAME = "./Exersise2_Virtual_Classroom_Manager/Classroom/classrooms.txt";

    public RemoveClassroom(AddClassroom addClassroom) {
        this.addClassroom = addClassroom;
    }

    // Remove a classroom if it exists
    public boolean removeClassroom(String classroomName) {
        String lowerCaseName = classroomName.toLowerCase();

        if (!addClassroom.getClassrooms().containsKey(lowerCaseName)) {
            System.out.println("Error: Classroom does not exist: " + classroomName);
            logger.log(Level.WARNING, "Attempt to remove non-existing classroom: {0}", classroomName);
            return false;
        }

        addClassroom.getClassrooms().remove(lowerCaseName);
        saveUpdatedClassroomsToFile();
        System.out.println("Classroom removed: " + classroomName);
        logger.log(Level.INFO, "Classroom removed: {0}", classroomName);
        return true;
    }

    // Save the updated classroom list to the file
    private void saveUpdatedClassroomsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Map.Entry<String, String> entry : addClassroom.getClassrooms().entrySet()) {
                writer.write(entry.getKey() + "," + entry.getValue());
                writer.newLine();
            }
            logger.log(Level.INFO, "Updated classroom list saved to file");
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error writing to classroom file: {0}", e.getMessage());
        }
    }
}
