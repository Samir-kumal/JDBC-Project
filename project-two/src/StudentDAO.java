

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    private Connection connection;

    // Constructor
    public StudentDAO() throws SQLException, ClassNotFoundException {
        String url = "jdbc:mysql://localhost:3306/Class";
        String username = "root";
        String password = "";
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection(url, username, password);
    }

    // CREATE operation
    public int addStudent(Student student) throws SQLException {
        String query = "INSERT INTO Student(Name, Email, PhoneNo,faculty) VALUES (?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, student.getName());
        preparedStatement.setString(2, student.getEmail());
        preparedStatement.setString(3, student.getPhoneNo());
        preparedStatement.setString(4, student.getFaculty());

        preparedStatement.executeUpdate();
        ResultSet rs = preparedStatement.getGeneratedKeys();
        if (rs.next()) {
            int id = rs.getInt(1);
            student.setId(id);
            return id;
        } else {
            return -1; // return -1 if no ID was generated
        }
    }


    // READ operation
    public List<Student> getAllStudents() throws SQLException {
        List<Student> students = new ArrayList<>();
        String query = "SELECT * FROM Student";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String email = resultSet.getString("email");
            String phone = resultSet.getString("phoneNo");
            String faculty = resultSet.getString("faculty");

            Student student = new Student(id,name, email, phone,faculty);
            students.add(student);
        }
        return students;
    }

    // UPDATE operation
    public boolean updateStudent(Student student) throws SQLException {
        boolean result = false;
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE Student SET name=?, email=?, phoneNo=? faculty =? WHERE id=?");
            statement.setString(1, student.getName());
            statement.setString(2, student.getEmail());
            statement.setString(3, student.getPhoneNo());
            statement.setString(4, student.getFaculty());
            statement.setInt(5, student.getId());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                result = true;
            }
            //connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;

    }

    public Student getStudentById(int id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Student WHERE id = ?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            String name = resultSet.getString("name");
            String email = resultSet.getString("email");
            String phone = resultSet.getString("phoneNo");
            String faculty = resultSet.getString("faculty");

            Student student = new Student(id, name, email, phone,faculty);
            return student;
        } else {
            return null;
        }
    }



    public boolean deleteStudent(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM Student WHERE id=?");
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
}