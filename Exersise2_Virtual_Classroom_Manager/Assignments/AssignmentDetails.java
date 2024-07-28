package Assignments;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AssignmentDetails {
    private static final Logger logger = Logger.getLogger(AssignmentDetails.class.getName());
    private static final String FILE_NAME = "./Exersise2_Virtual_Classroom_Manager/Assignments/assignments.txt";
    @SuppressWarnings("unused")
    private Scanner scanner;

    // Constructor to initialize Scanner instance
    public AssignmentDetails(Scanner scanner) {
        this.scanner = scanner;
    }

    // Method to display all assignment details
    public void displayAssignmentDetails() {
        Map<String, AssignmentInfo> assignmentsByClassroom = readAssignmentsFromFile();

        // Display the data
        if (assignmentsByClassroom.isEmpty()) {
            System.out.println("No assignments found.");
        } else {
            displayAssignmentData(assignmentsByClassroom);
        }
    }

    // Method to display assignment details for a specific classroom
    public void displayAssignmentDetailsForClassroom(String classroomName) {
        Map<String, AssignmentInfo> assignmentsByClassroom = readAssignmentsFromFile();

        if (assignmentsByClassroom.containsKey(classroomName)) {
            Map<String, AssignmentInfo> filteredAssignments = new LinkedHashMap<>();
            filteredAssignments.put(classroomName, assignmentsByClassroom.get(classroomName));
            displayAssignmentData(filteredAssignments);
        } else {
            System.out.println("No assignments found for the classroom: " + classroomName);
        }
    }

    // Method to read assignments from file
    private Map<String, AssignmentInfo> readAssignmentsFromFile() {
        Map<String, AssignmentInfo> assignmentsByClassroom = new LinkedHashMap<>();

        // Read the file and parse the data
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 7) {
                    String classroomName = parts[0];
                    String assignmentName = parts[1];
                    String deadline = parts[2];
                    String rubrics = parts[3];
                    String studentId = parts[4];
                    String studentName = parts[5];
                    String status = parts[6];

                    assignmentsByClassroom
                        .computeIfAbsent(classroomName, k -> new AssignmentInfo(assignmentName, deadline, rubrics, new LinkedHashMap<>()))
                        .getStudentDetails()
                        .put(studentId + " - " + studentName, status);
                }
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error reading from assignments file: {0}", e.getMessage());
            System.out.println("Error: Unable to display assignment details.");
        }

        return assignmentsByClassroom;
    }

    // Method to display assignment data
    private void displayAssignmentData(Map<String, AssignmentInfo> assignmentsByClassroom) {
        System.out.println("\nAssignment Details:");
        for (Map.Entry<String, AssignmentInfo> classroomEntry : assignmentsByClassroom.entrySet()) {
            String classroomName = classroomEntry.getKey();
            AssignmentInfo assignmentInfo = classroomEntry.getValue();

            System.out.println("\nClassroom: " + classroomName);
            System.out.println("Assignment Name: " + assignmentInfo.getAssignmentName());
            System.out.println("Deadline: " + assignmentInfo.getDeadline());
            System.out.println("Rubrics: " + assignmentInfo.getRubrics());
            System.out.println("-------------------------------------------------");
            System.out.printf("%-5s%-10s%-15s%-10s\n", "No.", "ID", "Name", "Status");
            System.out.println("-------------------------------------------------");

            int count = 1;
            for (Map.Entry<String, String> studentEntry : assignmentInfo.getStudentDetails().entrySet()) {
                String[] studentInfo = studentEntry.getKey().split(" - ");
                System.out.printf("%-5d%-10s%-15s%-10s\n", count++, studentInfo[0], studentInfo[1], studentEntry.getValue());
            }
        }
    }

    // Inner class to store assignment info and student details
    private static class AssignmentInfo {
        private String assignmentName;
        private String deadline;
        private String rubrics;
        private Map<String, String> studentDetails;

        public AssignmentInfo(String assignmentName, String deadline, String rubrics, Map<String, String> studentDetails) {
            this.assignmentName = assignmentName;
            this.deadline = deadline;
            this.rubrics = rubrics;
            this.studentDetails = studentDetails;
        }

        public String getAssignmentName() {
            return assignmentName;
        }

        public String getDeadline() {
            return deadline;
        }

        public String getRubrics() {
            return rubrics;
        }

        public Map<String, String> getStudentDetails() {
            return studentDetails;
        }
    }
}
