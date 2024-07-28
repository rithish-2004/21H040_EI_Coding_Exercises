package Classroom;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

// Handles viewing of classrooms
public class ViewClassroom {
    private AddClassroom addClassroom;
    private static final Logger logger = Logger.getLogger(ViewClassroom.class.getName());

    // Initialize the AddClassroom instance
    public ViewClassroom(AddClassroom addClassroom) {
        this.addClassroom = addClassroom;
    }

    // Display all classrooms sorted by creation date
    public void displayClassrooms() {
        Map<String, String> classrooms = addClassroom.getClassrooms();

        List<Map.Entry<String, String>> classroomList = new ArrayList<>(classrooms.entrySet());
        classroomList.sort(Map.Entry.comparingByValue());

        System.out.println("Total number of classrooms: " + classrooms.size());
        System.out.println("Classrooms (sorted by creation date):");
        for (Map.Entry<String, String> entry : classroomList) {
            System.out.println("Classroom: " + entry.getKey() + ", Created at: " + entry.getValue());
        }

        logger.log(Level.INFO, "Displayed all classrooms sorted by creation date");
    }

    // Display the most recently added classrooms (up to 3)
    public void displayRecentClassrooms() {
        Map<String, String> classrooms = addClassroom.getClassrooms();

        List<Map.Entry<String, String>> classroomList = new ArrayList<>(classrooms.entrySet());
        classroomList.sort(Map.Entry.comparingByValue());

        int totalClassrooms = classroomList.size();
        int start = Math.max(0, totalClassrooms - 3);

        System.out.println("Recently added classrooms:");
        for (int i = start; i < totalClassrooms; i++) {
            Map.Entry<String, String> entry = classroomList.get(i);
            System.out.println("Classroom: " + entry.getKey() + ", Created at: " + entry.getValue());
        }

        logger.log(Level.INFO, "Displayed the most recently added classrooms (up to 3)");
    }
}
