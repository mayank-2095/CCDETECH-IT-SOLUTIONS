import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class StudentGradeManagementSystem {
    private static final Map<String, Student> studentDatabase = new HashMap<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            displayMenu();
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    updateStudent();
                    break;
                case 3:
                    deleteStudent();
                    break;
                case 4:
                    viewAllStudents();
                    break;
                case 5:
                    System.out.println("Exiting the program.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);

        // Close the Scanner using try-with-resources
        try (Scanner closeScanner = scanner) {
            // Scanner will be closed automatically
        }
    }

    private static void displayMenu() {
        System.out.println("Student Grade Management System");
        System.out.println("1. Add Student");
        System.out.println("2. Update Student");
        System.out.println("3. Delete Student");
        System.out.println("4. View All Students");
        System.out.println("5. Exit");
    }

    private static void addStudent() {
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        System.out.print("Enter student roll number: ");
        String rollNumber = scanner.nextLine();

        // Validate if the roll number already exists
        if (studentDatabase.containsKey(rollNumber)) {
            System.out.println("Student with the roll number already exists. Use update option.");
            return;
        }

        System.out.print("Enter marks for the subject: ");
        double marks = scanner.nextDouble();

        Student student = new Student(name, rollNumber, marks);
        studentDatabase.put(rollNumber, student);
        System.out.println("Student added successfully.");
    }

    private static void updateStudent() {
        System.out.print("Enter student roll number to update: ");
        String rollNumber = scanner.nextLine();

        if (studentDatabase.containsKey(rollNumber)) {
            System.out.print("Enter new marks for the subject: ");
            double newMarks = scanner.nextDouble();

            Student student = studentDatabase.get(rollNumber);
            student.setMarks(newMarks);
            System.out.println("Student information updated successfully.");
        } else {
            System.out.println("Student with roll number " + rollNumber + " not found.");
        }
    }

    private static void deleteStudent() {
        System.out.print("Enter student roll number to delete: ");
        String rollNumber = scanner.nextLine();

        if (studentDatabase.containsKey(rollNumber)) {
            studentDatabase.remove(rollNumber);
            System.out.println("Student deleted successfully.");
        } else {
            System.out.println("Student with roll number " + rollNumber + " not found.");
        }
    }

    private static void viewAllStudents() {
        System.out.println("Student Information:");
        if (studentDatabase.isEmpty()) {
            System.out.println("No students found.");
        } else {
            studentDatabase.values().forEach(System.out::println);
        }
    }
}

class Student {
    private final String name;
    private final String rollNumber;
    private double marks;

    public Student(String name, String rollNumber, double marks) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.marks = marks;
    }

    public void setMarks(double marks) {
        this.marks = marks;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Roll Number: " + rollNumber + ", Marks: " + marks;
    }
}
