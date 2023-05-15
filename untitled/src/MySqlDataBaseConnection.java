import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import  java.sql.*;
public class MySqlDataBaseConnection {
    public static void main(String[] args) {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/Class","samir","wasd"
            );
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from Student");
            System.out.println("ID " + " Name "  + " Address " + " Email ");
            while (rs.next())
                System.out.println(rs.getInt(1)+ " "+ rs.getString(2) + " "+ rs.getString(3)+ " "+ rs.getString(4));
            con.close();
        } catch (Exception e){
            System.out.println(e);
        }
    }

}
