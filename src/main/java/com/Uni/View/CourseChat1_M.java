package com.Uni.View;


import com.Uni.Unibridges.Controller.Controller;
import com.Uni.Model.Database.DatabaseStruct;
import com.Uni.Model.Entity.Message;
import com.Uni.Model.Entity.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.util.List;  // Add this import statement


public class CourseChat1_M extends JFrame{

    private Controller controller;
    private Student currentStudent;

    private int courseID;
    public CourseChat1_M(Controller controller, Student currentStudent, int courseID) {
        this.controller = controller;
        this.currentStudent = currentStudent;
        this.courseID = courseID;


        //print courseID: value passed
        //System.out.println(courseID);




        //load chatUI
        initChatUI();

        //load course chat
        loadchatlogforcourse(courseID);


    }

    public CourseChat1_M(){

    }

    public CourseChat1_M(Controller controller, Student currentStudent){
        this.controller = controller;
        this.currentStudent = currentStudent;
    }

    public CourseChat1_M(Controller controller) {
        this.controller = controller;

        // Other initialization...
    }


    private JTextArea chatArea;
    private JTextField studentTB;
    private JTextField usernameTB;
    private Connection connection;

    public JButton sendMessageButton;

    public void initChatUI() {
        setTitle("Course Chat");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        usernameTB = new JTextField("Username", 10);
        studentTB = new JTextField(30);

        sendMessageButton = new JButton("Send Message");

        topPanel.add(usernameTB);
        topPanel.add(studentTB);
        topPanel.add(sendMessageButton);

        // Assign the new JTextArea instance to chatArea
        chatArea = new JTextArea();
        chatArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(chatArea);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        add(scrollPane, BorderLayout.CENTER);
        add(topPanel, BorderLayout.SOUTH);

        // You can remove this line: setVisible(true);
        //initializeDatabase();

        setVisible(true);

    }

    // Handle button clicks
    public void actionPerformed(ActionEvent e) {


    }


    public void loadchatlogforcourse(int courseID){


        List<Message> messages = DatabaseStruct.loadChatLogForCourse(controller.CCview.getConnection(), courseID);

        //testing to see if the correct connection is printed: value is passed
        //System.out.println(controller.CCview.getConnection());

        //testing courseid being passed: value is passed
        //System.out.println(courseID);





            //testing chatline: value is passed
            //System.out.println(chatLine);



        //loops through list of messages

        for (Message message : messages) {
            String chatLine = message.getEmail() + ": " + message.getMessage() +
                    " (Sent at: " + message.getTimestamp() + ")";
            chatArea.append(chatLine + "\n");
        }



    }




    public JTextArea getChatArea() {
        return chatArea;
    }

    public void setChatArea(JTextArea chatArea) {
        this.chatArea = chatArea;
    }

    public JTextField getStudentTB() {
        return studentTB;
    }

    public void setStudentTB(JTextField studentTB) {
        this.studentTB = studentTB;
    }

    public JTextField getUsernameTB() {
        return usernameTB;
    }

    public void setUsernameTB(String usernameTB) {
        this.usernameTB.setText(usernameTB);
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public JButton getSendMessageButton() {
        return sendMessageButton;
    }

    public void setSendMessageButton(JButton sendMessageButton) {
        this.sendMessageButton = sendMessageButton;
    }
}
