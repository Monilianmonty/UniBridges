package com.Uni.View;

import com.Uni.Model.Database.DatabaseStruct;
import org.hibernate.dialect.Database;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Login_View extends JFrame implements ActionListener {

    // GUI Components

    //Creates the label for login, password and email
    private JLabel loginLabel, passwordLabel, emailLabel;

    //Textfield for the email
    private JTextField emailField;

    //Textfield for the password
    private JPasswordField passwordField;

    //Login Button, Create Account/Back to Login, and CreateAccountConfirm Button
    private JButton loginButton, createAccountButton, createAccountConfirmButton;

    public Login_View() {

        //Sets the name of the window, the size and the exit on close.
        setTitle("Login");
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null); // Using null layout for simplicity (not recommended for complex layouts)

        // Creates and position for email label
        emailLabel = new JLabel("Email:");
        emailLabel.setBounds(50, 50, 80, 30);
        add(emailLabel);

        // Creates and position for email text field
        emailField = new JTextField();
        emailField.setBounds(150, 50, 200, 30);
        add(emailField);

        // Creates and position for password label
        passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 100, 80, 30);
        add(passwordLabel);

        // Create and position for password field
        passwordField = new JPasswordField();
        passwordField.setBounds(150, 100, 200, 30);
        add(passwordField);

        // Creates "Login" button
        loginButton = new JButton("Login");
        loginButton.setBounds(100, 150, 100, 30);
        loginButton.addActionListener(this);
        add(loginButton);

        // Creates "Create Account" button
        createAccountButton = new JButton("Create Account");
        createAccountButton.setBounds(210, 150, 140, 30);
        createAccountButton.addActionListener(this);
        add(createAccountButton);

        // Creates "Back to Login" button
        createAccountConfirmButton = new JButton("Create Account");
        createAccountConfirmButton.setBounds(210, 150, 140, 30);
        createAccountConfirmButton.addActionListener(this);
        createAccountConfirmButton.setVisible(false);
        add(createAccountConfirmButton);

        setVisible(true);
    }

    // Handle button clicks
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == createAccountButton) {
            if (createAccountButton.getText().equals("Create Account")) {
                // Transition to account creation mode
                createAccountButton.setText("Back to Login");
                createAccountButton.setBounds(50, 150, 140, 30);
                createAccountConfirmButton.setVisible(true);
                loginButton.setVisible(false);


            } else {
                // Transition back to login mode
                createAccountButton.setText("Create Account");
                createAccountButton.setBounds(210, 150, 140, 30);
                createAccountConfirmButton.setVisible(false);
                loginButton.setVisible(true);


            }
        } else if (e.getSource() == createAccountConfirmButton) {
            // Account creation

            String email = emailField.getText();
            String password = new String(passwordField.getPassword());
            Connection connection = null;

            //


            try {
                //connect to data
                String url = "jdbc:mysql://unibridges.ctbdc2rlbdxp.us-east-2.rds.amazonaws.com/unibridges";
                String user = "admin";
                String pass = "staples123";
                connection = DriverManager.getConnection(url, user, pass);
                System.out.println("Connected to database");


                //insert the student data
                com.Uni.Model.Database.DatabaseStruct.insertStudentData(connection, email, password);

            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

            JOptionPane.showMessageDialog(this, "Account Created!\nEmail: " + email + "\nPassword: " + password);
        } else if (e.getSource() == loginButton) {
            // add sql check here
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());

            //connection info
            String url = "jdbc:mysql://unibridges.ctbdc2rlbdxp.us-east-2.rds.amazonaws.com/unibridges";
            String user = "admin";
            String pass = "staples123";


            Connection connection = null;

            try {
                //connect to data
                connection = DriverManager.getConnection(url, user, pass);
                System.out.println("Connected to database");
                if(com.Uni.Model.Database.DatabaseStruct.checkCredentials(connection,email,password)){
                    System.out.println("Found in the database!");
                } else{
                    System.out.println("Not found in database");
                }


            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }



            // add sql check here
            JOptionPane.showMessageDialog(this, "Email: " + email + "\nPassword: " + password);
        }



    }

    public static void main(String[] args) {
        new Login_View(); // Creates an instance of Login
    }




}
