package com.Uni.Controller;

import com.Uni.Model.Database.DatabaseStruct;
import com.Uni.View.CourseView;
import com.Uni.View.CreateUser_View;
import com.Uni.View.Hub;
import com.Uni.View.Login_View;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Controller_J {

    public Login_View Lview;

    public CreateUser_View Uview;
    public Hub Hview;

    public CourseView Cview;

    public Controller_J(CourseView v3) {

        //Lview = v1;
        //Uview = v2;
        //Hview = v2;
        Cview = v3;
    }


    //has all button methods
    public void initController() {
        Lview.getLoginButton().addActionListener(e -> login());
        Lview.getCreateAccountButton().addActionListener(e -> createUserView());
        Uview.getCreateAccountButtonU().addActionListener(e -> createAccount());

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
            if(com.Uni.Model.Database.DatabaseStruct.checkCredentials(connection,email,password)){
                System.out.println("Found in the database!");


                //JOptionPane.showMessageDialog(this, "Email: " + email + "\nPassword: " + password);
            } else{
                System.out.println("Not found in database");
            }


        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }

    //shows the createuser view
    public void createUserView(){




        Lview.dispose();
        Uview.setVisible(true);
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
            } else if(DatabaseStruct.checkCredentials(connection,email,password)){

                JOptionPane.showMessageDialog(null, "Credentials already exist!");
                validEntry = false;
            }



            //if its a valid entry
            if(validEntry) {



                //insert the student data


                com.Uni.Model.Database.DatabaseStruct.insertStudentData(connection, email, password);

            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        //JOptionPane.showMessageDialog(this, "Account Created!\nEmail: " + email + "\nPassword: " + password);

    }


}
