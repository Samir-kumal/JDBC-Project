import java.sql.*;
import java.util.Scanner;

public class UpdateUser {
    public static void main(String[] args) {
        // Database connection details
        String url = "jdbc:mysql://localhost:3306/Class";
        String username = "root";
        String password = "";

        try {
            Class.forName("com.mysql.jdbc.Driver");

            // Establishing a connection to the database
            Connection connection = DriverManager.getConnection(url, username, password);

            // Updating a student's age
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter the Id of the student you want to update");
            int id = sc.nextInt();
            System.out.println("Enter the new name of the student (Leave Blank if not needed)");
            String newName = sc.next().trim();
            System.out.println("Enter new Age (Leave Blank if not needed)");
            int newAge = sc.nextInt();
            System.out.println("Enter new Email (Leave Blank if not needed)");
            String newEmail = sc.next().trim();
            System.out.println("Enter the new faculty name (Leave Blank if not needed)");
            String newFaculty = sc.next().trim();

            updateStudent(connection,id, newName, newAge, newEmail, newFaculty);

            // Closing the connection
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to update a student's age in the database
    private static void updateStudent(Connection connection, int id, String name, int age, String email, String faculty) throws SQLException {
        // Reading the user initially
        ReadUsers.readStudents(connection);
        String sql = "UPDATE Student SET name = ?,age = ?,email = ?, faculty = ? WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, name);
        statement.setInt(2, age);
        statement.setString(3, email);
        statement.setString(4, faculty);
        statement.setInt(5,id);


        int rowsUpdated = statement.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("The student's age was updated successfully!");
        } else {
            System.out.println("No student found with the given ID.");
        }
        statement.close();
    }
}
