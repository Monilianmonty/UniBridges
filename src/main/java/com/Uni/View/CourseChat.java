package com.Uni.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseChat {
    private JFrame courseFrame;
    private JTextField studentTB;
    private JTextField usernameTB;
    private JPanel chatArea;

    public CourseChat() {
        courseFrame = new JFrame("Course Chat");
        courseFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        courseFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        studentTB = new JTextField();
        studentTB.setPreferredSize(new Dimension(400, 30));
        studentTB.addKeyListener(new SendMessageListener());

        usernameTB = new JTextField("Username");
        usernameTB.setPreferredSize(new Dimension(100, 30));

        JButton sendMessageButton = new JButton("Send Message");
        sendMessageButton.addActionListener(e -> sendMessage());

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(usernameTB);
        topPanel.add(sendMessageButton);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bottomPanel.add(studentTB);

        chatArea = new JPanel();
        chatArea.setLayout(new BoxLayout(chatArea, BoxLayout.Y_AXIS));
        chatArea.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Visible border

        JScrollPane scrollPane = new JScrollPane(chatArea);

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel controlPanel = new JPanel(new BorderLayout());
        controlPanel.add(topPanel, BorderLayout.NORTH);
        controlPanel.add(bottomPanel, BorderLayout.SOUTH);

        courseFrame.add(centerPanel);
        courseFrame.add(controlPanel, BorderLayout.SOUTH);

        courseFrame.setVisible(true);
    }


    // Fetch messages from the database (dummy implementation)
    private List<String> fetchMessagesFromDatabase() {
        // INTEGRATE DATABASE MESSAGES HERE
        List<String> messages = new ArrayList<>();
        // Example
        messages.add("Welcome to the chat!");

        return messages;
    }

    // Action listener for sending messages
    private class SendMessageListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                sendMessage();
            }
        }
    }

    private void sendMessage() {
        String username = usernameTB.getText();
        String message = studentTB.getText();
        if (!message.isEmpty()) {
            addMessageToChatArea(username, message);
            studentTB.setText("");
            // Save message to the database


            saveMessageToDatabase(message);
        }
    }

    // Save message to the database
    private void saveMessageToDatabase(String message) {
        // Replace  with database saving logic
        // Implement inserting the message into your database
        System.out.println("Saving message to the database: " + message);
    }

    // Method to add messages to the chat area
    private void addMessageToChatArea(String username, String message) {
        JPanel messagePanel = new JPanel(new BorderLayout());

        JLabel usernameLabel = new JLabel(username + ": ");
        usernameLabel.setFont(usernameLabel.getFont().deriveFont(Font.BOLD));

        JLabel messageLabel = new JLabel(message);
        messageLabel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));

        messagePanel.add(usernameLabel);
        messagePanel.add(messageLabel);
       //messagePanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        chatArea.add(messagePanel);
        chatArea.revalidate();
        chatArea.repaint();
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CourseChat());
    }
}