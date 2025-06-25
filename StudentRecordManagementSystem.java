import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class StudentRecordManagementSystem {

    private final ArrayList<Student> studentList;
    private final Scanner sc;
    private int nextId = 1;

    public StudentRecordManagementSystem() {
        studentList = new ArrayList<>();
        sc = new Scanner(System.in);
    }

    public static void main(String[] args) {
        StudentRecordManagementSystem srm = new StudentRecordManagementSystem();
        srm.run();
    }

    public void run() {
        int choice;
        do {
            System.out.println("\n--- Student Record Management System ---");
            System.out.println("1. Add New Student");
            System.out.println("2. View All Students");
            System.out.println("3. Update Student Record");
            System.out.println("4. Delete Student Record");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            try {
                choice = sc.nextInt();
                sc.nextLine(); // consume leftover newline

                switch (choice) {
                    case 1:
                        addStudent();
                        break;
                    case 2:
                        viewAllStudents();
                        break;
                    case 3:
                        updateStudent();
                        break;
                    case 4:
                        deleteStudent();
                        break;
                    case 5:
                        System.out.println("Exiting from the student records. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                sc.nextLine(); // consume invalid input
                choice = 0; // reset choice to loop again
            }
        } while (choice != 5);
        sc.close();
    }

    private void addStudent() {
        System.out.println("\n--- Add New Student ---");

        System.out.print("Enter Student Name: ");
        String name = sc.nextLine();

        int marks = -1;
        while (marks < 0 || marks > 100) {
            System.out.print("Enter Student Marks (0-100): ");
            try {
                marks = sc.nextInt();
                sc.nextLine();
                if (marks < 0 || marks > 100) {
                    System.out.println("Marks must be between 0 and 100.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid marks. Please enter a number.");
                sc.nextLine();
                marks = -1;
            }
        }

        Student newStudent = new Student(nextId, name, marks);
        studentList.add(newStudent);
        System.out.println("Student added successfully with ID: " + nextId);
        nextId++;
    }

    private void viewAllStudents() {
        System.out.println("\n--- View All Students ---");
        if (studentList.isEmpty()) {
            System.out.println("No student records found.");
            return;
        }
        for (Student student : studentList) {
            System.out.println(student);
        }
    }

    private Student findStudentById(int id) {
        for (Student student : studentList) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    private void updateStudent() {
        System.out.println("\n--- Update Student Record ---");
        if (studentList.isEmpty()) {
            System.out.println("No student records to update.");
            return;
        }

        System.out.print("Enter ID of student to update: ");
        int idToUpdate;
        try {
            idToUpdate = sc.nextInt();
            sc.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Invalid ID format.");
            sc.nextLine();
            return;
        }

        Student studentToUpdate = findStudentById(idToUpdate);
        if (studentToUpdate == null) {
            System.out.println("Student with ID " + idToUpdate + " not found.");
            return;
        }

        System.out.println("Found student: " + studentToUpdate);
        System.out.print("Enter new name (or press Enter to keep current: '" + studentToUpdate.getName() + "'): ");
        String newName = sc.nextLine();
        if (!newName.isEmpty()) {
            studentToUpdate.setName(newName);
        }

        System.out.print("Enter new marks (0-100) (or -1 to keep current: " + studentToUpdate.getMarks() + "): ");
        String marksInput = sc.nextLine();
        if (!marksInput.isEmpty()) {
            try {
                int newMarks = Integer.parseInt(marksInput);
                if (newMarks >= 0 && newMarks <= 100) {
                    studentToUpdate.setMarks(newMarks);
                } else if (newMarks != -1) {
                    System.out.println("Invalid marks. Keeping current marks.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid marks format. Keeping current marks.");
            }
        }

        System.out.println("Student record updated successfully.");
    }

    private void deleteStudent() {
        System.out.println("\n--- Delete Student Record ---");
        if (studentList.isEmpty()) {
            System.out.println("No student records to delete.");
            return;
        }

        System.out.print("Enter ID of student to delete: ");
        int idToDelete;
        try {
            idToDelete = sc.nextInt();
            sc.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Invalid ID format.");
            sc.nextLine();
            return;
        }

        Student studentToDelete = findStudentById(idToDelete);
        if (studentToDelete == null) {
            System.out.println("Student with ID " + idToDelete + " not found.");
            return;
        }

        studentList.remove(studentToDelete);
        System.out.println("Student with ID " + idToDelete + " deleted successfully.");
    }
}
