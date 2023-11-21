import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class DatabaseStruct{



    public static void main(String[] args) {
        String url = "jdbc:mysql://unibridges.ctbdc2rlbdxp.us-east-2.rds.amazonaws.com/unibridges";
        String user = "admin";
        String password = "staples123";

            try {
                //establish database connection

                Connection connection = DriverManager.getConnection(url, user, password);
                System.out.println("Connected to database");
                createStudentTable(connection);
                createClassTable(connection);

            } catch(SQLException e){
                e.printStackTrace();
            }



        /*
        //create a student class table to establish many-to-many relationship
        //try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement()) {
            String createStudentCourseTableSQL = "CREATE TABLE IF NOT EXISTS studentcourses ("
                    + "StudentID INT,"
                    + "ClassID INT,"
                    + "FOREIGN KEY (StudentID) REFERENCES Students(StudentID),"
                    + "FOREIGN KEY (ClassID) REFERENCES Classes(ClassID) )";
            statement.executeUpdate(createStudentCourseTableSQL);
            System.out.println("Table 'courses' created (or already exists).");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        */


    }


    //create a student table
    public static void createStudentTable(Connection connection) {
        try (Statement statement = connection.createStatement()) {
            String createStudentTableSQL = "CREATE TABLE IF NOT EXISTS students ("
                    + "StudentID INT PRIMARY KEY AUTO_INCREMENT,"
                    + "email VARCHAR(255),"
                    + "password VARCHAR(255))";
            statement.executeUpdate(createStudentTableSQL);
            System.out.println("Table 'students' created (or already exists).");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //create a class table
    public static void createClassTable(Connection connection) {
        try (Statement statement = connection.createStatement()) {
            String createCourseTableSQL = "CREATE TABLE IF NOT EXISTS courses ("
                    + "ClassID INT PRIMARY KEY AUTO_INCREMENT,"
                    + "name VARCHAR(255),"
                    + "level INT)";
            statement.executeUpdate(createCourseTableSQL);
            System.out.println("Table 'courses' created (or already exists).");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }





}