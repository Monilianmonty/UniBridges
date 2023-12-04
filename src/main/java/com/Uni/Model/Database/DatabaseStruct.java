
package com.Uni.Model.Database;

import com.Uni.Model.Entity.Course;
import com.Uni.Model.Entity.Message;

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

                //String name = "Physics";
                //int level = 200;

                //insertClass(connection,name, level );

                //creating course_history table
                //createChatLogTable(connection);

                //int studentid = 2;

                //String message = "another day another victory for the og's";

                //saveMessage(connection, studentid, message);

            } catch(SQLException e){
                e.printStackTrace();
            }





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

    //gets courseID by name
    public static int getCourseIdByName(Connection connection, String courseName) {
        try {
            String sql = "SELECT ClassID FROM courses WHERE name = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, courseName);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("ClassID");
            } else {
                // Return a value indicating that the course with the given name was not found
                return -1;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Return a value indicating an error
            return -1;
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

    public static String getStudentEmailByID(Connection connection, int studentID) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT email FROM students WHERE studentID = ?")) {
            preparedStatement.setInt(1, studentID);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString("StudentID");
            } else {
                // Return a value indicating that the student with the given studentid was not found
                return "student id not found";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Return a value indicating an error
            return "Error";
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

    //creates the coursechat table where we reference the studentid and course id since the student writes the chats and the chat logs are based on the course that the student is taking
    public static void createChatLogTable(Connection connection) {
        try (Statement statement = connection.createStatement()) {
            String createChatTableSQL = "CREATE TABLE chat_log (\n" +
                    "    id INT AUTO_INCREMENT PRIMARY KEY,\n" +
                    "    student_id INT,\n" +
                    "    course_id INT,\n" +  // Add course_id column
                    "    message TEXT,\n" +
                    "    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,\n" +
                    "    FOREIGN KEY (student_id) REFERENCES students(StudentID),\n" +  //chat has many students
                    "    FOREIGN KEY (course_id) REFERENCES courses(ClassID)\n" +       //chat is based on a single course
                    ");";
            statement.executeUpdate(createChatTableSQL);
            System.out.println("Table 'chat_log' created (or already exists).");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    //loads chat history for a specific course
    public static List<Message> loadChatLogForCourse(Connection connection, int courseId) {
        List<Message> messages = new ArrayList<>();

        try {
            String sql = "SELECT chat_log.id, chat_log.message, chat_log.timestamp, students.email " +
                    "FROM chat_log " +
                    "JOIN students ON chat_log.student_id = students.StudentID " +
                    "WHERE chat_log.course_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, courseId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int messageId = resultSet.getInt("id");
                String messageText = resultSet.getString("message");
                Timestamp timestamp = resultSet.getTimestamp("timestamp");
                String email = resultSet.getString("email");

                Message message = new Message(messageId, email, messageText, timestamp);
                messages.add(message);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return messages;
    }


    //for when a user hits send
    public static void saveMessage(Connection connection, int studentId, int courseId, String message) {
        try {
            String sql = "INSERT INTO chat_log (student_id, course_id, message) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, studentId);
            statement.setInt(2, courseId);
            statement.setString(3, message);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
















}