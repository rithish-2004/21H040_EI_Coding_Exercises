package Student;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import Classroom.AddClassroom; // Import AddClassroom to check classroom existence

// Manages student information and interactions with the student file
public class AddStudent {
    private Map<String, String> students;
    private static final String FILE_NAME = "./Exersise2_Virtual_Classroom_Manager/Student/students.txt";
    private static final Logger logger = Logger.getLogger(AddStudent.class.getName());
    private AddClassroom addClassroom; // Reference to AddClassroom to check classroom existence

    public AddStudent(AddClassroom addClassroom) {
        this.students = new HashMap<>();
        this.addClassroom = addClassroom;
        loadStudentsFromFile();
    }

    // Adds a new student if the classroom exists
    public void addStudent(String studentId, String className) {
        if (!addClassroom.doesClassroomExist(className)) { // Check if classroom exists
            System.out.println("Error: Classroom " + className + " does not exist.");
            logger.log(Level.WARNING, "Attempt to add student to a non-existent classroom: {0}", className);
            return;
        }
        
        if (students.containsKey(studentId)) {
            System.out.println("Warning: Student with ID " + studentId + " already exists.");
            logger.log(Level.WARNING, "Attempt to add an existing student ID: {0}", studentId);
            return;
        }
        
        students.put(studentId, className);
        saveStudentToFile(studentId, className);
        System.out.println("Student Addition: Student " + studentId + " has been enrolled in " + className + ".");
        logger.log(Level.INFO, "Student added: {0} enrolled in {1}", new Object[]{studentId, className});
    }

    // Load students from file
    private void loadStudentsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", 2);
                if (parts.length == 2) {
                    students.put(parts[0].trim(), parts[1].trim());
                }
            }
            logger.log(Level.INFO, "Students loaded from file");
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error reading student file: {0}", e.getMessage());
        }
    }

    // Save student to file
    private void saveStudentToFile(String studentId, String className) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(studentId + "," + className);
            writer.newLine();
            logger.log(Level.INFO, "Student saved to file: {0} enrolled in {1}", new Object[]{studentId, className});
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error writing to student file: {0}", e.getMessage());
        }
    }
}
