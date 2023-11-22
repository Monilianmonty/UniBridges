
package com.Uni.Model.Database;

import com.Uni.Model.Entity.Course;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DatabaseStruct{
    String url = "jdbc:mysql://unibridges.ctbdc2rlbdxp.us-east-2.rds.amazonaws.com/unibridges";
    String user = "admin";
    String password = "staples123";
    public DatabaseStruct(String url,String user,String password){

        this.url = url;
        this.user = user;
        this.password = password;

    }

    public static void main(String[] args) {
        String url = "jdbc:mysql://unibridges.ctbdc2rlbdxp.us-east-2.rds.amazonaws.com/unibridges";
        String user = "admin";
        String password = "staples123";

            try {
                //establish database connection

                Connection connection = DriverManager.getConnection(url, user, password);
                System.out.println("Connected to database");

                //create student table
                //createStudentTable(connection);

                //create class table
                //createClassTable(connection);

                //create a test student
                //insertStudentData(connection);

                //String u = "test@example.com";
                //String p = "testpassword";

                //check if the student is in database
                /*
                if(checkCredentials(connection,u,p)) {
                    System.out.println("found in database!");
                } else{
                    System.out.println("not found in database");
                }
                */

                //createStudentCoursesTable(connection);

                String name = "Operating Systems";
                int level = 400;

                insertClass(connection,name, level );





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

    //method to create a test student
    public static void insertStudentData(Connection connection, String email, String password) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO students (email, password) VALUES (?, ?)")) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Data inserted into 'students' table.");
            } else {
                System.out.println("Failed to insert data into 'students' table.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //insert a single class into the table
    public static void insertClass(Connection connection, String name, int level) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO courses (name, level) VALUES (?, ?)")) {
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, level);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Class " + name + " level " + level + " inserted into 'courses' table.");
            } else {
                System.out.println("Failed to insert class into 'courses' table.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    // Check if given email and password match any entries in the "students" table
    public static boolean checkCredentials(Connection connection, String email, String password) {
        try (Statement statement = connection.createStatement()) {
            String checkCredentialsSQL = "SELECT * FROM students WHERE email = '" + email + "' AND password = '" + password + "'";
            ResultSet resultSet = statement.executeQuery(checkCredentialsSQL);

            // If the result set has any rows, the credentials are valid
            return resultSet.next();

        } catch (SQLException e) {
            e.printStackTrace();
            return false; // An error occurred, credentials cannot be verified
        }
    }


    // Check if a student with the given email already exists in the "students" table
    public static boolean checkIfStudentExists(Connection connection, String email) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM students WHERE email = ?")) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();

            // If the result set has any rows, a student with the given email already exists
            return resultSet.next();

        } catch (SQLException e) {
            e.printStackTrace();
            return false; // An error occurred, cannot determine if the student exists
        }
    }

    // Create a table to represent the relationship between students and courses
    public static void createStudentCoursesTable(Connection connection) {
        try (Statement statement = connection.createStatement()) {
            String createStudentCoursesTableSQL = "CREATE TABLE IF NOT EXISTS studentcourses ("
                    + "StudentID INT,"
                    + "ClassID INT,"
                    + "PRIMARY KEY (StudentID, ClassID),"
                    + "FOREIGN KEY (StudentID) REFERENCES students(StudentID),"
                    + "FOREIGN KEY (ClassID) REFERENCES courses(ClassID) )";
            statement.executeUpdate(createStudentCoursesTableSQL);
            System.out.println("Table 'studentcourses' created (or already exists).");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // Associate a student with a course by enrolling a student into a single course
    public static void enrollStudentInCourse(Connection connection, int studentID, int courseID) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO studentcourses (StudentID, ClassID) VALUES (?, ?)")) {
            preparedStatement.setInt(1, studentID);
            preparedStatement.setInt(2, courseID);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Student enrolled in the course.");
            } else {
                System.out.println("Failed to enroll student in the course.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Retrieve the student ID based on the email
    public static int getStudentIdByEmail(Connection connection, String email) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT StudentID FROM students WHERE email = ?")) {
            preparedStatement.setString(1, email);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("StudentID");
            } else {
                // Return a value indicating that the student with the given email was not found
                return -1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Return a value indicating an error
            return -1;
        }
    }

    // Delete a student by ID from the "students" table
    public static void deleteStudentById(Connection connection, int studentId) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM students WHERE StudentID = ?")) {
            preparedStatement.setInt(1, studentId);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Student with ID " + studentId + " deleted from 'students' table.");
            } else {
                System.out.println("No student found with ID " + studentId + ". Nothing deleted.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    // Retrieve all classes for a specific student which will be used in the hub
    public static List<Course> getClassesForStudent(Connection connection, int studentId) {
        List<Course> classes = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT courses.* " +
                        "FROM courses " +
                        "JOIN studentcourses ON courses.ClassID = studentcourses.ClassID " +
                        "JOIN students ON students.StudentID = studentcourses.StudentID " +
                        "WHERE students.StudentID = ?")) {

            preparedStatement.setInt(1, studentId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int classId = resultSet.getInt("ClassID");
                    String className = resultSet.getString("name");
                    int classLevel = resultSet.getInt("level");

                    // Assuming you have a Course class to represent the result
                    Course course = new Course(className, classLevel, classId);
                    classes.add(course);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return classes;
    }



    // Enroll student into multiple courses
    public static void enrollStudentInCourses(Connection connection, int studentId, List<Course> courses) {
        try {
            // Start a transaction
            connection.setAutoCommit(false);

            // Clear existing course associations for the student
            clearCoursesForStudent(connection, studentId);

            // Enroll the student in the new courses
            for (Course course : courses) {
                enrollStudentInCourse(connection, studentId, course.getCourseID());
            }

            // Commit the transaction
            connection.commit();
        } catch (SQLException e) {
            // Handle exceptions and roll back the transaction in case of an error
            try {
                connection.rollback();
            } catch (SQLException rollbackException) {
                rollbackException.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                // Restore auto-commit to true
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    // Method to clear existing course associations for a student
    private static void clearCoursesForStudent(Connection connection, int studentId) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM studentcourses WHERE StudentID = ?")) {
            preparedStatement.setInt(1, studentId);
            preparedStatement.executeUpdate();
        }
    }


    // Retrieve all courses from the "courses" table
    public List<Course> getAllCourses() {
        List<Course> courses = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement()) {

            String getAllCoursesSQL = "SELECT * FROM courses";
            ResultSet resultSet = statement.executeQuery(getAllCoursesSQL);

            while (resultSet.next()) {
                int courseId = resultSet.getInt("ClassID");
                String courseName = resultSet.getString("name");
                int courseLevel = resultSet.getInt("level");

                // Assuming you have a Course class to represent the result
                Course course = new Course(courseName, courseLevel, courseId);
                courses.add(course);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return courses;
    }













}