package Assignments;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import Classroom.AddClassroom;
import Student.AddStudentData;

public class ScheduleAssignment {
    private static final Logger logger = Logger.getLogger(ScheduleAssignment.class.getName());
    private static final String FILE_NAME = "./Exersise2_Virtual_Classroom_Manager/Assignments/assignments.txt";

    private AddClassroom addClassroom;
    private AddStudentData addStudentData;
    private Scanner scanner;

    // Constructor to initialize AddClassroom, AddStudentData, and Scanner instances
    public ScheduleAssignment(AddClassroom addClassroom, AddStudentData addStudentData, Scanner scanner) {
        this.addClassroom = addClassroom;
        this.addStudentData = addStudentData;
        this.scanner = scanner;
    }

    // Method to prompt user for assignment details and schedule it
    public void scheduleAssignment(String classroomName) {
        // Check if the classroom exists
        if (!addClassroom.doesClassroomExist(classroomName)) {
            System.out.println("Error: Classroom does not exist.");
            return;
        }

        // Prompt user for assignment details
        System.out.print("Enter the assignment name: ");
        String name = scanner.nextLine().trim();

        System.out.print("Enter the deadline (yyyy-MM-dd HH:mm:ss): ");
        String deadline = scanner.nextLine().trim();

        System.out.print("Enter the rubrics marks: ");
        String marks = scanner.nextLine().trim();

        // Validate the input
        if (name.isEmpty() || deadline.isEmpty() || marks.isEmpty()) {
            System.out.println("Error: All fields must be filled.");
            return;
        }

        // Retrieve list of students in the specified classroom
        List<AddStudentData.Student> students = addStudentData.getStudentsByClassroom(classroomName);

        // Store the assignment in the file along with student details
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            for (AddStudentData.Student student : students) {
                writer.write(classroomName + "," + name + "," + deadline + "," + marks + "," +student.getId()+","+ student.getName() + "," + "not completed" + "," + getCurrentTime());
                writer.newLine();
            }
            logger.log(Level.INFO, "Scheduled assignment: {0} for classroom: {1}", new Object[]{name, classroomName});
            System.out.println("Assignment for " +classroomName+" has been Scheduled.");
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error writing to assignments file: {0}", e.getMessage());
            System.out.println("Error: Unable to schedule the assignment.");
        }
    }

    // Get the current time in the required format
    private String getCurrentTime() {
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new java.util.Date());
    }
}
