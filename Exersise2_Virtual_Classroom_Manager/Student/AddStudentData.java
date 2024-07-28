package Student;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import Classroom.AddClassroom;

public class AddStudentData {
    private static final String FILE_NAME = "./Exersise2_Virtual_Classroom_Manager/Student/students.txt"; // File to store student data
    private static final Logger logger = Logger.getLogger(AddStudentData.class.getName());
    private static AddStudentData instance; // Singleton instance
    private AddClassroom addClassroom;
    private Scanner scanner;
    private Map<String, Student> students;
    private Map<String, List<Student>> studentsByClassroom;
  
    // Constructor
    private AddStudentData(AddClassroom addClassroom, Scanner scanner) {
        this.addClassroom = addClassroom;
        this.scanner = scanner;
        this.students = new HashMap<>();
        this.studentsByClassroom = new HashMap<>();
        loadStudentDataFromFile();
    }
    // Singleton instance getter
    public static synchronized AddStudentData getInstance(AddClassroom addClassroom, Scanner scanner) {
        if (instance == null) {
            instance = new AddStudentData(addClassroom, scanner);
        }
        return instance;
    }

    public List<Student> getStudentsByClassroom(String classroomName) {
        return studentsByClassroom.getOrDefault(classroomName.toLowerCase(), new ArrayList<>());
    }

    public void addStudent(String studentId, String[] classNames) {
        // Check if the student ID is valid and the classrooms exist
        if (!isValidStudentId(studentId)) {
            System.out.println("Error: Invalid student ID format.");
            logger.log(Level.WARNING, "Invalid student ID format: {0}", studentId);
            return;
        }

        if (students.containsKey(studentId)) {
            System.out.println("Error: Student ID already exists.");
            logger.log(Level.WARNING, "Attempt to add existing student ID: {0}", studentId);
            return;
        }

        if (!addClassroom.areClassroomsValid(classNames)) {
            System.out.println("Error: One or more class names are invalid.");
            logger.log(Level.WARNING, "Invalid class names provided: {0}", String.join(", ", classNames));
            return;
        }

        // Prompt for additional student details
        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine().trim();

        System.out.print("Enter Student Email: ");
        String email = scanner.nextLine().trim();

        System.out.print("Enter Student Phone Number: ");
        String phone = scanner.nextLine().trim();

        // Basic validation for email and phone number
        if (!isValidEmail(email) || phone.length() != 10) {
            System.out.println("Error: Invalid email or phone number format.");
            logger.log(Level.WARNING, "Invalid email or phone number format: {0}, {1}", new Object[]{email, phone});
            return;
        }

        // Check for duplicates and save student data
        if (isDuplicateStudent(name, email, phone)) {
            System.out.println("Error: Duplicate student data detected.");
            logger.log(Level.WARNING, "Duplicate student data detected for ID: {0}", studentId);
            return;
        }

        // Store student data
        saveStudentToFile(studentId, name, email, phone, classNames);
        System.out.println("Student Addition: Student " + studentId + " has been enrolled in " + String.join(", ", classNames) + ".");
        logger.log(Level.INFO, "Student added: {0} with ID {1}", new Object[]{name, studentId});
    }

    private boolean isValidStudentId(String studentId) {
        return studentId != null && studentId.matches("\\d+");
    }

    private boolean isDuplicateStudent(String name, String email, String phone) {
        return students.values().stream().anyMatch(student -> 
            student.name.equals(name) && 
            student.email.equals(email) && 
            student.phone.equals(phone));
    }

    private void saveStudentToFile(String studentId, String name, String email, String phone, String[] classNames) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            String classList = String.join(",", classNames);
            writer.write(studentId + "," + name + "," + email + "," + phone + "," + classList);
            writer.newLine();
            Student student = new Student(studentId, name, email, phone, classNames);
            students.put(studentId, student);
            for (String className : classNames) {
                studentsByClassroom.computeIfAbsent(className.toLowerCase(), k -> new ArrayList<>()).add(student);
            }
            logger.log(Level.INFO, "Student saved to file: {0} with ID {1}", new Object[]{name, studentId});
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error writing to student file: {0}", e.getMessage());
        }
    }

    private void loadStudentDataFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", 5);
                if (parts.length == 5) {
                    String studentId = parts[0].trim();
                    String name = parts[1].trim();
                    String email = parts[2].trim();
                    String phone = parts[3].trim();
                    String[] classes = parts[4].trim().split(",");

                    Student student = new Student(studentId, name, email, phone, classes);
                    students.put(studentId, student);
                    for (String className : classes) {
                        studentsByClassroom.computeIfAbsent(className.toLowerCase(), k -> new ArrayList<>()).add(student);
                    }
                }
            }
            logger.log(Level.INFO, "Student data loaded from file");
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error reading student file: {0}", e.getMessage());
        }
    }

    private boolean isValidEmail(String email) {
        return email != null && email.contains("@") && email.indexOf('@') < email.length() - 1;
    }

    // Nested class to represent a Student
    public static class Student {
        public String id;
        public String name;
        public String email;
        public String phone;
        public Set<String> classes;

        public Student(String id, String name, String email, String phone, String[] classNames) {
            this.id = id;
            this.name = name;
            this.email = email;
            this.phone = phone;
            this.classes = new HashSet<>();
            for (String className : classNames) {
                this.classes.add(className.trim());
            }
        }

        public String getName() { 
            return name; 
        }
        public String getId() { 
            return id; 
        }
    }

    // Display students for a particular classroom
    public void displayStudentsByClassroom(String className) {
        List<Student> studentsInClass = studentsByClassroom.get(className.toLowerCase());
        if (studentsInClass == null || studentsInClass.isEmpty()) {
            System.out.println("No students found for classroom: " + className);
            return;
        }

        System.out.println("Students in classroom: " + className);
        System.out.printf("%-5s %-10s %-20s %-30s %-15s%n", "S.No", "ID", "Name", "Email", "Phone Number");
        int count = 0;
        for (Student student : studentsInClass) {
            System.out.printf("%-5d %-10s %-20s %-30s %-15s%n", ++count, student.id, student.name, student.email, student.phone);
        }
        System.out.println("Total Enrollments in " + className + ": " + count);
    }

    // Display all classrooms and students in them
    public void displayAllClassrooms() {
        int totalClassrooms = 0;
        int totalStudents = 0;

        for (Map.Entry<String, List<Student>> entry : studentsByClassroom.entrySet()) {
            String className = entry.getKey();
            List<Student> studentsInClass = entry.getValue();

            System.out.println("Students in classroom: " + className);
            System.out.printf("%-5s %-10s %-20s %-30s %-15s%n", "S.No", "ID", "Name", "Email", "Phone Number");
            int count = 0;
            for (Student student : studentsInClass) {
                System.out.printf("%-5d %-10s %-20s %-30s %-15s%n", ++count, student.id, student.name, student.email, student.phone);
            }
            System.out.println("Total students in " + className + ": " + count);
            totalClassrooms++;
            totalStudents += count;
        }

        System.out.println("Total classrooms: " + totalClassrooms);
        System.out.println("Total Enrollment in subjects: " + totalStudents);
    }
}
