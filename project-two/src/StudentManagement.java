
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class StudentManagement {
    private final StudentDAO studentDAO = new StudentDAO();
    private final Scanner scanner = new Scanner(System.in);

    public StudentManagement() throws SQLException, ClassNotFoundException {
    }

    public void addStudent() throws SQLException {
        System.out.println("Enter student name:");
        String name = scanner.nextLine();
        System.out.println("Enter student email:");
        String email = scanner.nextLine();
        System.out.println("Enter student phone number:");
        String phoneNumber = scanner.nextLine();
        System.out.println("Enter student faculty :");
        String faculty = scanner.nextLine();
        Student student = new Student(0,name, email, phoneNumber, faculty);
        int id = studentDAO.addStudent(student);
        if (id != -1) {
            student.setId(id);
            System.out.println("Student added successfully");
        } else {
            System.out.println("Failed to add student");
        }
    }


    public void updateStudent() throws SQLException {
        System.out.println("Enter student ID:");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter new name (leave blank to keep current value):");
        String name = scanner.nextLine().trim();
        System.out.println("Enter new email (leave blank to keep current value):");
        String email = scanner.nextLine().trim();
        System.out.println("Enter new phone number (leave blank to keep current value):");
        String phoneNumber = scanner.nextLine().trim();
        System.out.println("Enter new faculty (leave blank to keep current value):");
        String faculty = scanner.nextLine().trim();
        // Get the current student from the database
        Student currentStudent = studentDAO.getStudentById(id);

        // Check if the user entered a value for each field and update the student object accordingly
        if (!name.isEmpty()) {
            currentStudent.setName(name);
        }
        if (!email.isEmpty()) {
            currentStudent.setEmail(email);
        }
        if (!phoneNumber.isEmpty()) {
            currentStudent.setPhoneNo(phoneNumber);
        }
        if (!faculty.isEmpty()) {
            currentStudent.setFaculty(faculty);
        }

        // Update the student in the database
        if (studentDAO.updateStudent(currentStudent)) {
            System.out.println("Student updated successfully");
        } else {
            System.out.println("Student not found");
        }
    }

    public void deleteStudent() {
        System.out.println("Enter student ID:");
        int id = scanner.nextInt();
        scanner.nextLine();
        if (studentDAO.deleteStudent(id)) {
            System.out.println("Student deleted successfully");
        } else {
            System.out.println("Student not found");
        }
    }

    public void viewAllStudents() throws SQLException {
        List<Student> students = studentDAO.getAllStudents();
        if (students.isEmpty()) {
            System.out.println("No students found");
        } else {
            for (Student student : students) {
                System.out.println(student);
            }
        }
    }
}