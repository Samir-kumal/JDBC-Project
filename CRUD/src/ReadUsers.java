import java.sql.*;
public class ReadUsers {
    public static void main(String[] args) {
        // Database connection details
        String url = "jdbc:mysql://localhost:3306/Class";
        String username = "root";
        String password = "";

        try {
            // Establishing a connection to the database
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);

            // Reading all students
            readStudents(connection);

            // Closing the connection
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to read all students from the database
    protected static void readStudents(Connection connection) throws SQLException {
        String sql = "SELECT * FROM Student";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            int id = resultSet.getInt("ID");

            String name = resultSet.getString("Name");
            int age = resultSet.getInt("Age");
            String email = resultSet.getString("Email");
            String faculty = resultSet.getString("Faculty");

            System.out.println("ID: " + id + "Name: " + name + ", Age: " + age + " Email: " + email + " Faculty: " + faculty);
        }
        resultSet.close();
        statement.close();
    }

}