import java.sql.*;
import java.util.Scanner;

public class CreateUser {
    public static void main(String[] args) {
        // Database connection details
        String url = "jdbc:mysql://localhost:3306/Class";
        String username = "root";
        String password = "";

        try {
            // Establishing a connection to the database
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);

            // Taking User input to Create a new student
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter the name of the student");
            String name = sc.next().trim();
            System.out.println("Enter Age");
            int age = sc.nextInt();
            System.out.println("Enter Email");
            String email = sc.next().trim();
            System.out.println("Enter the faculty name");
            String faculty = sc.next().trim();
            CreateUser(connection, name, age, email, faculty);

            // Closing the connection
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to create a new student in the database
    private static void CreateUser(Connection connection, String name, int age, String email, String faculty) throws SQLException {
        String sql = "INSERT INTO Student (name, age, email, faculty) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, name);
        statement.setInt(2, age);
        statement.setString(3,email);
        statement.setString(4,faculty);
        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("A new student was created successfully!");
        }
        statement.close();
    }
}
