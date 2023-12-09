package com.Uni.View;

import com.Uni.Unibridges.Controller.Controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
public class ChatClient {

    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;
    private MessageHandler messageHandler;


    public ChatClient(String serverAddress, int serverPort, MessageHandler messageHandler) {
        this.messageHandler = messageHandler;

        try {
            Socket socket = new Socket(serverAddress, serverPort);
            this.outputStream = new ObjectOutputStream(socket.getOutputStream());
            this.inputStream = new ObjectInputStream(socket.getInputStream());

            Thread thread = new Thread(this::listenForMessages);
            thread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void listenForMessages() {
        try {
            while (true) {
                Object message = inputStream.readObject();
                String[] parts = message.toString().split(": ");
                if (parts.length == 2) {
                    // Extract username and message from the received string
                    String username = parts[0];
                    System.out.println("this is username in listen for messages" + username);

                    String messageText = parts[1];
                    System.out.println("this is message in listen for messages" + messageText);
                    // Delegate the handling of the received message to the MessageHandler
                    messageHandler.handleMessage(username, messageText);
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void sendMessage(Object message) {
        try {
            outputStream.writeObject(message);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
