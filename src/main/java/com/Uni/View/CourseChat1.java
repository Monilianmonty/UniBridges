//Temporary


package com.Uni.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class CourseChat1 extends JFrame {
    private JTextArea chatArea;
    private JTextField studentTB;
    private JTextField usernameTB;
    private Connection connection;

    public CourseChat1() {
        setTitle("Course Chat");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        usernameTB = new JTextField("Username", 10);
        studentTB = new JTextField(30);
        studentTB.addActionListener(e -> sendMessage());

        JButton sendMessageButton = new JButton("Send Message");
        sendMessageButton.addActionListener(e -> sendMessage());

        topPanel.add(usernameTB);
        topPanel.add(studentTB);
        topPanel.add(sendMessageButton);

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(chatArea);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        add(scrollPane, BorderLayout.CENTER);
        add(topPanel, BorderLayout.SOUTH);

        setVisible(true);

        initializeDatabase();
        loadChatHistory();
    }

    private void initializeDatabase() {
        try {
            String jdbcURL = "jdbc:your_database_url_here";
            String username = "your_username";
            String password = "your_password";
            connection = DriverManager.getConnection(jdbcURL, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void sendMessage() {
        String username = usernameTB.getText();
        String message = studentTB.getText().trim();
        if (!message.isEmpty()) {
            addMessageToChatArea(username, message);
            saveMessage(username, message);
            studentTB.setText(""); // Clear the text field after sending
        }
    }

    private void addMessageToChatArea(String username, String message) {
        chatArea.append(username + ": " + message + "\n");
    }

    private void saveMessage(String username, String message) {
        try {
            String sql = "INSERT INTO chat_history (username, message) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, message);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadChatHistory() {
        try {
            String sql = "SELECT username, message FROM chat_history";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String username = resultSet.getString("username");
                String message = resultSet.getString("message");
                addMessageToChatArea(username, message);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CourseChat1());
    }
}
