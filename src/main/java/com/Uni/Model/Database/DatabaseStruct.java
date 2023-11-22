
package com.Uni.Model.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;


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

                String u = "test@example.com";
                String p = "testpassword";

                //check if the student is in database
                if(checkCredentials(connection,u,p)) {
                    System.out.println("found in database!");
                } else{
                    System.out.println("not found in database");
                }



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





}