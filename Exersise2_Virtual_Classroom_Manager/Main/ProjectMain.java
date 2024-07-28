package Main;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import Assignments.ScheduleAssignment;
import Assignments.SubmitAssignment;
import Classroom.AddClassroom;
import Classroom.ClassroomStorage;
import Classroom.RemoveClassroom;
import Classroom.ViewClassroom;
import OptionDisplay.optionDisplay;
import Student.AddStudentData;
public class ProjectMain {
    private static final Logger logger = Logger.getLogger(ProjectMain.class.getName());

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ClassroomStorage storage = new ClassroomStorage();
        AddClassroom addClassroom = new AddClassroom(storage);
        ViewClassroom viewClassroom = new ViewClassroom(addClassroom);
        RemoveClassroom removeClassroom = new RemoveClassroom(addClassroom);
        AddStudentData addStudentData = AddStudentData.getInstance(addClassroom, scanner);
        ScheduleAssignment scheduleAssignment = new ScheduleAssignment(addClassroom, addStudentData, scanner);
        SubmitAssignment submitAssignment = new SubmitAssignment(scanner);
    
        optionDisplay optionDisplay = new optionDisplay(addClassroom, viewClassroom, removeClassroom, addStudentData, scheduleAssignment,submitAssignment);

        System.out.println("WELCOME TO EI CLASSROOM MANAGEMENT SYSTEM!");
        printMenu();
        boolean continueLoop = true;
        while (continueLoop) {
            try {
                System.out.println("\nEnter command (type 'exit' to quit):");
                String input = scanner.nextLine();
                if ("exit".equalsIgnoreCase(input.trim())) {
                    System.out.println("DEVOLOPED BY RITHISH KANNA S");
                    System.out.println("THANK YOU");
                    continueLoop = false;
                    continue;
                }
                else if ("display_menu".equalsIgnoreCase(input.trim())) {
                    printMenu();
                }
                else{
                String[] parts = input.split(" ", 2);
                String command = parts[0].trim();
                String data = (parts.length > 1) ? parts[1].trim() : "";

                optionDisplay.executeCommand(command, data);
                }
            } catch (Exception e) {
                logger.log(Level.SEVERE, "An error occurred: " + e.getMessage(), e);
                System.out.println("An error occurred. Please try again.");
            }
        }

        scanner.close();
    }

    private static void printMenu() {
        System.out.println("---------------------------------------------------------------");
        System.out.println("Main Menu:");
        System.out.println("---------------------------------------------------------------");
        System.out.println("1. Add Classroom - add_classroom [classroom_name]");
        System.out.println("2. View Classrooms - view_classroom");
        System.out.println("3. View Recent Classrooms - view_recent");
        System.out.println("4. Remove Classroom - remove_classroom [classroom_name]");
        System.out.println("5. Add Student - add_student [student_id] [class1 class2 ...]");
        System.out.println("6. Display Students by Classroom - students_at [classroom_name]");
        System.out.println("7. Display All Classrooms - display_all_classrooms");
        System.out.println("8. Schedule Assignment - schedule_assignment [classroom_name]");
        System.out.println("9. Submit Assignment - submit_assignment [classroom] [studentId]");
        System.out.println("10. Display Assignment Details - assignment_det");
        System.out.println("11. Display Assignment Details by Classroom- status_view [classroom]");
        System.out.println("12. Display Assignment Menu - display_menu");
        System.out.println("13. Exit - exit");
        System.out.println("---------------------------------------------------------------");
    }
}
