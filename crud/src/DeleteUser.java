import java.sql.*;
import java.util.Scanner;

public class DeleteUser {
    public static void main(String[] args) {
        // Database connection details
        String url = "jdbc:mysql://localhost:3306/Class";
        String username = "root";
        String password = "";

        try {
            Class.forName("com.mysql.jdbc.Driver");

            // Establishing a connection to the database
            Connection connection = DriverManager.getConnection(url, username, password);

            // Deleting a student
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter the ID of the Student you want to delete");
            int id = sc.nextInt();

            deleteStudent(connection, id);

            // Closing the connection
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to delete a student from the database
    private static void deleteStudent(Connection connection, int id) throws SQLException {
        String sql = "DELETE FROM Student WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        int rowsDeleted = statement.executeUpdate();
        if (rowsDeleted > 0) {
            System.out.println("The student was deleted successfully!");
        } else {
            System.out.println("No student found with the given name.");
        }
        statement.close();
    }
}