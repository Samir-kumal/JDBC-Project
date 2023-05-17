import java.util.Scanner;
import java.sql.*;
public class Main {

    public static void main(String[] args) throws SQLException,ClassNotFoundException   {
        Scanner sc = new Scanner(System.in);
        StudentManagement studentManagement = new StudentManagement();
        int option ;
        while(true){
            System.out.printf("--------------------------------------%n");
            System.out.printf("    Please select any option    %n");
            System.out.printf("--------------------------------------%n");
            System.out.println("| 1.Add a new user to the database   |");
            System.out.println("| 2.Update  user from the database   |");
            System.out.println("| 3.Delete  user from the database   |");
            System.out.println("| 4.Select a  user from the database |");
            System.out.println("| 5.View all students                |");
            System.out.println("| 6.Quit                             |");
            System.out.printf("--------------------------------------%n");

            option = sc.nextInt();
            switch (option) {
                case 1:
                    studentManagement.addStudent();
                    break;
                case 2:
                    studentManagement.updateStudent();
                    break;
                case 3:
                    studentManagement.deleteStudent();
                    break;
                case 4:
                    studentManagement.viewStudent();
                    break;
                case 5:
                    studentManagement.viewAllStudents();
                    break;
                case 6:
                    System.out.println("Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option");
            }
        }
    }
}