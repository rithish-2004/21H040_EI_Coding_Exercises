package Assignments;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SubmitAssignment {
    private static final Logger logger = Logger.getLogger(SubmitAssignment.class.getName());
    private static final String FILE_NAME = "./Exersise2_Virtual_Classroom_Manager/Assignments/assignments.txt";
    @SuppressWarnings("unused")
    private Scanner scanner;

    public SubmitAssignment(Scanner scanner) {
        this.scanner = scanner;
    }

    // Method to submit an assignment
    public void submitAssignment(String classroomName, String studentId) {
        List<String> lines = new ArrayList<>();
        boolean assignmentFound = false;

        // Read the file and update the status
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                logger.log(Level.FINE, "Checking line - {0}", line); // Debug logging
                
                // Log the extracted parts for debugging
                logger.log(Level.FINE, "Extracted parts: {0}", (Object) parts);
                
                // Check if the classroom name and student ID match
                if (parts.length >= 7) {
                    logger.log(Level.FINE, "Classroom name in file: {0}, Student ID in file: {1}, Status: {2}", new Object[]{parts[0], parts[4], parts[5]});
                    if (parts[0].equalsIgnoreCase(classroomName) && parts[4].equalsIgnoreCase(studentId) && parts[6].equalsIgnoreCase("not completed")) {
                        parts[6] = "completed";
                        assignmentFound = true;
                        logger.log(Level.INFO, "Assignment marked as completed for student: {0} in classroom: {1}", new Object[]{studentId, classroomName});
                    }
                }
                lines.add(String.join(",", parts));
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error reading from assignments file: {0}", e.getMessage());
            System.out.println("Error: Unable to submit the assignment.");
            return;
        }

        // Write the updated status back to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (String updatedLine : lines) {
                writer.write(updatedLine);
                writer.newLine();
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error writing to assignments file: {0}", e.getMessage());
            System.out.println("Error: Unable to submit the assignment.");
            return;
        }

        if (assignmentFound) {
            System.out.println("Assignment submitted "+studentId+" in " +classroomName+" Classroom.");
        } else {
            System.out.println("Error: Assignment not found or already completed for student: " + studentId + " in classroom: " + classroomName);
        }
    }
}
