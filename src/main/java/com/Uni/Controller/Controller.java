package com.Uni.Controller;

import com.Uni.Model.Database.DatabaseStruct;
import com.Uni.Model.Entity.Course;
import com.Uni.Model.Entity.Student;
import com.Uni.View.*;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Controller extends Component {


    private Student currentStudent;


    //sets the current student
    public void setCurrentStudent(Student student) {
        this.currentStudent = student;
    }
    //gets the current student
    public Student getCurrentStudent() {
        return currentStudent;
    }


    public Login_View Lview;

    public CreateUser_View Uview;
    public Hub_M Hview;

    public CourseView_M Cview;

    public CourseChat1_M CCview;




    public Controller(Login_View v1, CreateUser_View v2, CourseView_M v3, CourseChat1_M v4 , Hub_M v5) {

        Lview = v1;
        Uview = v2;
        Cview = v3;
        CCview = v4;
        Hview =  v5;
    }

    public Controller(){

    }

    //for user to navigate back to hub from coursechat
    private Hub_M hubview;


    public void setHubview(Hub_M hubview) {
        this.hubview = hubview;
    }

    public Hub_M getHubview(){
        return this.hubview;
    }



    //in order to use different the same view within different methods
    private CourseChat1_M courseChatView;


    public void setCourseChat(CourseChat1_M courseChatView){
        this.courseChatView = courseChatView;
    }

    public CourseChat1_M getCourseChatView(){
        return this.courseChatView;
    }


    private CourseView_M courseview;

    public void setCourseView(CourseView_M courseView){
        this.courseview = courseView;
    }

    public CourseView_M getCourseView(){
        return this.courseview;
    }

    List <Course> coursesF;

    public void setCoursesList(List<Course> courses){
        this.coursesF = courses;
    }

    public List<Course> getCourses(){
        return coursesF;
    }





    //has all button methods
    public void initController() throws SQLException {
        Lview.getLoginButton().addActionListener(e -> login());
        Uview.getCreateAccountButtonU().addActionListener(e -> createAccount());

        String url = "jdbc:mysql://unibridges.ctbdc2rlbdxp.us-east-2.rds.amazonaws.com/unibridges";
        String user = "admin";
        String pass = "staples123";

        Connection connection = null;

        connection = DriverManager.getConnection(url, user, pass);
        CCview.setConnection(connection);









    }



    public void sendMessage(){

        String url = "jdbc:mysql://unibridges.ctbdc2rlbdxp.us-east-2.rds.amazonaws.com/unibridges";
        String user = "admin";
        String pass = "staples123";


        Connection connection = null;

        try {




                String message = courseChatView.getStudentTB().getText();



            //connect to data
            connection = DriverManager.getConnection(url, user, pass);

            int studentID = 2;
            int courseID = DatabaseStruct.getCourseIdByName(connection, "Computer Architecture");
            //send message to database
            DatabaseStruct.saveMessage(connection, studentID,courseID,message);

            //send the user to the hub with all the respective courses that they are taking



        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }


    }



    public void login() {


        // add sql check here
        //String email = emailField.getText();

        //String password = new String(passwordField.getPassword());

        //connection info
        String url = "jdbc:mysql://unibridges.ctbdc2rlbdxp.us-east-2.rds.amazonaws.com/unibridges";
        String user = "admin";
        String pass = "staples123";


        Connection connection = null;

        try {

            String email = Lview.getEmailField().getText();
            JPasswordField tempField = Lview.getPasswordField();
            String password = String.valueOf(tempField.getPassword());

            //testing email and password getters
            System.out.println(email);
            System.out.println(password);

            //connect to data
            connection = DriverManager.getConnection(url, user, pass);
            System.out.println("Connected to database");
            if(DatabaseStruct.checkCredentials(connection,email,password)){
                System.out.println("Found in the database!");
                currentStudent = new Student(DatabaseStruct.getStudentIdByEmail(connection,email), email);
                setCurrentStudent(currentStudent);



                //int courseId = DatabaseStruct.getCourseIdByName(connection, "Computer Architecture");

                //testing courseID: value passed
                //System.out.println(courseId);



                //create instance of ccview to pass current student
                //CourseChat1_M courseChatView = new CourseChat1_M(this, currentStudent, courseId);



                //must get id from database
                int sid = DatabaseStruct.getStudentIdByEmail(connection, email);

                //getting courses
                List<Course> courses = DatabaseStruct.getClassesForStudent(connection, sid);

                //testing local student email: value passed
                //System.out.println("this is email!"+ currentStudent.getEmail());

                //create instance of hView to pass current student
                Hub_M hubview = new Hub_M(this, currentStudent, courses);
                setHubview(hubview);
                hubview.setUsernameTB(email);


                // Capture the current hubview in a final variable
                final Hub_M currentHubView = hubview;

                // Add action listener with the captured hubview
                currentHubView.getCourseButton().addActionListener(e -> openHub(currentHubView));


                //hubview.getCourseButton().addActionListener(e -> openHub());


                //set the coursechat to what it is currently
                /*
                setCourseChat(courseChatView);
                courseChatView.setVisible(true);
                courseChatView.getSendMessageButton().addActionListener(e -> sendMessage());
                */






            } else{
                System.out.println("Not found in database");
            }


            //send the user to the hub with all the respective courses that they are taking



        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }


    public void openHub(Hub_M hubview){

        //make hub pop up
        Lview.dispose();
        hubview.setVisible(true);
        System.out.println("eurika!");

    }


    public void createAccount(){

        String email = Uview.getEmailFieldU().getText();
        JPasswordField tempField = Uview.getPasswordFieldU();
        String password = String.valueOf(tempField.getPassword());

        //
        boolean validEntry = true;

        //to see email and password output
        System.out.println(email + " " + password);

        Connection connection = null;




        try {
            //connect to data
            String url = "jdbc:mysql://unibridges.ctbdc2rlbdxp.us-east-2.rds.amazonaws.com/unibridges";
            String user = "admin";
            String pass = "staples123";

            //connect user to database
            connection = DriverManager.getConnection(url, user, pass);
            System.out.println("Connected to database");


            //check if database is empty
            if(email.equals("") || password.equals("")){

                JOptionPane.showMessageDialog(null, "Please enter valid credentials");
                validEntry = false;

                //if the database already has this specific student
            } else if(DatabaseStruct.checkIfStudentExists(connection,email)){

                JOptionPane.showMessageDialog(null, "Credentials already exist!");
                validEntry = false;
            }



            //if its a valid entry insert into database and move them to add the courses they are taking
            if(validEntry) {



                //insert the student data
                com.Uni.Model.Database.DatabaseStruct.insertStudentData(connection, email, password);

                //send them to the courseView
                currentStudent = new Student(DatabaseStruct.getStudentIdByEmail(connection,email), email);
                setCurrentStudent(currentStudent);

                //create instance of courseview with current student that signed up and set the current view
                CourseView_M courseview = new CourseView_M(this, currentStudent);
                setCourseView(courseview);
                courseview.setVisible(true);
                courseview.getAddCourse().addActionListener(e -> {
                    try {
                        addCourse();
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                });


            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        //JOptionPane.showMessageDialog(this, "Account Created!\nEmail: " + email + "\nPassword: " + password);

    }

    //for add course button
    public void addCourse() throws SQLException {

        //connect to data
        String url = "jdbc:mysql://unibridges.ctbdc2rlbdxp.us-east-2.rds.amazonaws.com/unibridges";
        String user = "admin";
        String pass = "staples123";

        Connection connection = null;
        //connect user to database
        connection = DriverManager.getConnection(url, user, pass);
        System.out.println("Connected to database");

        // Retrieve selected course and level from CourseView_M
        String selectedCourseAndLevel = courseview.getSelectedCourseAndLevel();
        String selectedCourse = (String) courseview.getCourseBox().getSelectedItem();
        Integer selectedLevel = (Integer) courseview.getLevelBox().getSelectedItem();

        // if a course is selected
        if (selectedCourseAndLevel != null) {
            // Append the selected course and level to the JTextArea in CourseView_M
            courseview.getCourseTextArea().append(selectedCourseAndLevel + "\n");

            //create course object to pass values to append course
            Course c1 = new Course(selectedCourse,selectedLevel, DatabaseStruct.getCourseIdByName(connection,selectedCourse));

            //append course to list of courses
            List <Course> courses = courseview.appendCourse(c1);



            Course firstCourse = courses.get(0);

            //checking to see if list contains courses at proper index: values are showing properly
            System.out.println(firstCourse.getCourseName());
            System.out.println(courses.get(2));


        }

        //will set the list of courses within controller
        setCoursesList(coursesF);



        //make it so that when they press finish the list of courses is appended to that specific student
        courseview.getFinishB().addActionListener(e -> {
            try {
                Finish();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

    }

    public void Finish() throws SQLException {

        //create other instance of courseview to pass List values
        CourseView_M c2 = courseview.getController().getCourseView();

        //will set the list of courses within the view
        List<Course> courses = c2.getCourses();

        System.out.println("these are the courses"+ courses);

        //connect to data
        String url = "jdbc:mysql://unibridges.ctbdc2rlbdxp.us-east-2.rds.amazonaws.com/unibridges";
        String user = "admin";
        String pass = "staples123";

        Connection connection = null;
        //connect user to database
        connection = DriverManager.getConnection(url, user, pass);
        System.out.println("Connected to database");

        //creating an instance of a student
        Student s1 = courseview.getCurrentStudent();


        //get email of current student
        String email = s1.getEmail();

        //testing if current users email is passed through: value is passed through
        //System.out.println("This is the email within the coursechat view " + email);


        //enroll student in list of courses *KEYNOTE: Cannot put student in same named course all names must be different
        DatabaseStruct.enrollStudentInCourses(connection,DatabaseStruct.getStudentIdByEmail(connection,email),courses);

    }



}
