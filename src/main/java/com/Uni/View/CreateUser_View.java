package com.Uni.View;

import javax.swing.*;

public class CreateUser_View extends JFrame {

    // GUI Components

    //Creates the label for login, password and email
    private JLabel loginLabelU, passwordLabelU, emailLabelU;

    //Textfield for the email
    private JTextField emailFieldU;

    //Textfield for the password
    private JPasswordField passwordFieldU;

    //Login Button, Create Account/Back to Login, and CreateAccountConfirm Button
    private JButton loginButtonU, createAccountButtonU, createAccountConfirmButtonU;
        public CreateUser_View(){

            setTitle("Create User");
            this.setExtendedState(JFrame.MAXIMIZED_BOTH);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLayout(null); // Using null layout for simplicity (not recommended for complex layouts)

            // Creates and position for email label
            emailLabelU = new JLabel("Email:");
            emailLabelU.setBounds(50, 50, 80, 30);
            add(emailLabelU);

            // Creates and position for email text field
            emailFieldU = new JTextField();
            emailFieldU.setBounds(150, 50, 200, 30);
            add(emailFieldU);

            // Creates and position for password label
            passwordLabelU = new JLabel("Password:");
            passwordLabelU.setBounds(50, 100, 80, 30);
            add(passwordLabelU);

            // Create and position for password field
            passwordFieldU = new JPasswordField();
            passwordFieldU.setBounds(150, 100, 200, 30);
            add(passwordFieldU);

            // Creates "Login" button
            loginButtonU = new JButton("Login");
            loginButtonU.setBounds(100, 150, 100, 30);
            //loginButtonU.addActionListener(this);
            add(loginButtonU);

            // Creates "Create Account" button
            createAccountButtonU = new JButton("Create Account");
            createAccountButtonU.setBounds(210, 150, 140, 30);
            //createAccountButtonU.addActionListener(this);
            add(createAccountButtonU);

            // Creates "Back to Login" button
            createAccountConfirmButtonU = new JButton("Create Account");
            createAccountConfirmButtonU.setBounds(210, 150, 140, 30);
            //createAccountConfirmButtonU.addActionListener(this);
            createAccountConfirmButtonU.setVisible(false);
            add(createAccountConfirmButtonU);

            setVisible(true);



        }


    public JLabel getLoginLabelU() {
        return loginLabelU;
    }

    public void setLoginLabelU(JLabel loginLabelU) {
        this.loginLabelU = loginLabelU;
    }

    public JLabel getPasswordLabelU() {
        return passwordLabelU;
    }

    public void setPasswordLabelU(JLabel passwordLabelU) {
        this.passwordLabelU = passwordLabelU;
    }

    public JLabel getEmailLabelU() {
        return emailLabelU;
    }

    public void setEmailLabelU(JLabel emailLabelU) {
        this.emailLabelU = emailLabelU;
    }

    public JTextField getEmailFieldU() {
        return emailFieldU;
    }

    public void setEmailFieldU(JTextField emailFieldU) {
        this.emailFieldU = emailFieldU;
    }

    public JPasswordField getPasswordFieldU() {
        return passwordFieldU;
    }

    public void setPasswordFieldU(JPasswordField passwordFieldU) {
        this.passwordFieldU = passwordFieldU;
    }

    public JButton getLoginButtonU() {
        return loginButtonU;
    }

    public void setLoginButtonU(JButton loginButtonU) {
        this.loginButtonU = loginButtonU;
    }

    public JButton getCreateAccountButtonU() {
        return createAccountButtonU;
    }

    public void setCreateAccountButtonU(JButton createAccountButtonU) {
        this.createAccountButtonU = createAccountButtonU;
    }

    public JButton getCreateAccountConfirmButtonU() {
        return createAccountConfirmButtonU;
    }

    public void setCreateAccountConfirmButtonU(JButton createAccountConfirmButtonU) {
        this.createAccountConfirmButtonU = createAccountConfirmButtonU;
    }
}
