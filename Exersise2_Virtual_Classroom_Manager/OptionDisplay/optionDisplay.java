package OptionDisplay;

import java.util.Scanner;

import Assignments.AssignmentDetails;
import Assignments.ScheduleAssignment;
import Assignments.SubmitAssignment;
import Classroom.AddClassroom;
import Classroom.RemoveClassroom;
import Classroom.ViewClassroom;
import Student.AddStudentData;
public class optionDisplay {
    private AddClassroom addClassroom;
    private ViewClassroom viewClassroom;
    private RemoveClassroom removeClassroom;
    private AddStudentData addStudentData;
    private Scanner scanner;
    private ScheduleAssignment scheduleAssignment;
    private SubmitAssignment submitAssignment;

    // Initialize AddClassroom, ViewClassroom, RemoveClassroom, AddStudentData, ScheduleAssignment, and SubmitAssignment instances
    public optionDisplay(AddClassroom addClassroom, ViewClassroom viewClassroom, RemoveClassroom removeClassroom, AddStudentData addStudentData, ScheduleAssignment scheduleAssignment,SubmitAssignment submitAssignment) {
        this.addClassroom = addClassroom;
        this.viewClassroom = viewClassroom;
        this.removeClassroom = removeClassroom;
        this.addStudentData = addStudentData;
        this.scheduleAssignment = scheduleAssignment;
        this.submitAssignment = submitAssignment;
        this.scanner = new Scanner(System.in);
    }

    // Execute the given command
    public void executeCommand(String command, String data) {
        String[] parts = command.split(" ", 2);
        switch (parts[0].toLowerCase()) {
            case "add_classroom":
                if (!data.isEmpty()) {
                    addClassroom.addClassroom(data);
                } else {
                    System.out.println("Error: Classroom name is required for add_classroom command");
                }
                break;
            case "view_classroom":
                viewClassroom.displayClassrooms();
                break;
            case "view_recent":
                viewClassroom.displayRecentClassrooms();
                break;
            case "remove_classroom":
                if (!data.isEmpty()) {
                    confirmAndRemoveClassroom(data);
                } else {
                    System.out.println("Error: Classroom name is required for remove_classroom command");
                }
                break;
            case "add_student":
                String[] studentParts = data.split(" ");
                if (studentParts.length < 2) {
                    System.out.println("Error: Invalid command format. Usage: add_student [ID] [class1 class2 ...]");
                    break;
                }
                String studentId = studentParts[0];
                String[] classNames = new String[studentParts.length - 1];
                System.arraycopy(studentParts, 1, classNames, 0, classNames.length);
                addStudentData.addStudent(studentId, classNames);
                break;
                case "students_at":
                if (!data.isEmpty()) {
                    addStudentData.displayStudentsByClassroom(data);
                } else {
                    System.out.println("Error: Classroom name is required for students_at command");
                }
                break;
            case "display_all_classrooms":
                addStudentData.displayAllClassrooms();
                break;
            case "schedule_assignment":
                handleScheduleAssignment(data);
                break;
            case "submit_assignment":
                String[] submitParts = data.split(" ");
                if (submitParts.length != 2) {
                    System.out.println("Error: Invalid command format. Usage: submit_assignment [studentId] [classroom]");
                    break;
                }
                submitAssignment.submitAssignment(submitParts[0], submitParts[1]);
                break;
            case "assignment_det":
                AssignmentDetails assignmentDetails = new AssignmentDetails(scanner);
                assignmentDetails.displayAssignmentDetails();
                break;
            case "status_view":
                if (!data.isEmpty()) {
                    AssignmentDetails assignmentDetails1 = new AssignmentDetails(scanner);
                    assignmentDetails1.displayAssignmentDetailsForClassroom(data);
                } else {
                    System.out.println("Error: Classroom name is required for status_view command");
                }
                break;
            default:
                System.out.println("Invalid command: " + parts[0]);
                break;
        }
    }

    private void handleScheduleAssignment(String data) {
        if (data.isEmpty()) {
            System.out.println("Error: Classroom name is required for schedule_assignment command");
            return;
        }
        String classroomName = data.trim();
        scheduleAssignment.scheduleAssignment(classroomName);
    }

    // Prompt the user for confirmation before removing a classroom
    private void confirmAndRemoveClassroom(String classroomName) {
        System.out.println("Are you sure you want to remove the classroom: " + classroomName + "? (yes/no)");
        String confirmation = scanner.nextLine().trim().toLowerCase();
        if ("yes".equals(confirmation)) {
            removeClassroom.removeClassroom(classroomName);
        } else {
            System.out.println("Classroom removal cancelled.");
        }
    }
}
