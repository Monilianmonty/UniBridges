package com.Uni.View;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CourseChat {
    private JFrame courseFrame;
    private JTextField studentTB;
    private JPanel chatArea;

    public CourseChat() {
        courseFrame = new JFrame("Course Chat");
        courseFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        courseFrame.setLayout(new BorderLayout());
        courseFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        studentTB = new JTextField();
        studentTB.setPreferredSize(new Dimension(400, 30));

        JButton sendMessageButton = new JButton("Send Message");
        sendMessageButton.addActionListener(new SendMessageListener());

        JPanel bottomPanel = new JPanel(new FlowLayout());
        bottomPanel.add(studentTB);
        bottomPanel.add(sendMessageButton);

        chatArea = new JPanel();
        chatArea.setLayout(new BoxLayout(chatArea, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(chatArea);

        courseFrame.add(scrollPane, BorderLayout.CENTER);
        courseFrame.add(bottomPanel, BorderLayout.SOUTH);

        courseFrame.setVisible(true);
    }

    // Action listener for sending messages
    private class SendMessageListener implements ActionListener, KeyListener {
        public void actionPerformed(ActionEvent e) {
            sendMessage();
        }

        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                sendMessage();
            }
        }

        public void keyReleased(KeyEvent e) {}

        public void keyTyped(KeyEvent e) {}

        private void sendMessage() {
            String message = studentTB.getText();
            if (!message.isEmpty()) {
                addMessage(message);
                studentTB.setText(""); // Clear the text field after sending
            }
        }
    }

    // Method to add messages to the chat area
    private void addMessage(String message) {
        JLabel newMessage = new JLabel(message);
        chatArea.add(newMessage);
        chatArea.revalidate(); // Refresh the panel to reflect the new message
        chatArea.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CourseChat());
    }
}


