//TEST FOR JAVAFX


/*
Would need

        <dependency>
			<groupId>org.openjfx</groupId>
			<artifactId>javafx-controls</artifactId>
			<version>12.0.2</version>
		</dependency>


and

            <plugin>
				<groupId>org.openjfx</groupId>
				<artifactId>javafx-maven-plugin</artifactId>
				<version>0.0.8</version>
				<configuration>
					<mainClass>hellofx/org.openjfx.App</mainClass>
				</configuration>
			</plugin>


in pom.xml file
 */

/*
package com.Uni.View;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseChat2 extends Application {
    private TextArea chatArea;
    private TextField studentTB;
    private TextField usernameTB;
    private Connection connection;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Course Chat");

        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));

        HBox topPanel = new HBox(10);

        usernameTB = new TextField("Username");
        usernameTB.setPrefWidth(100);

        studentTB = new TextField();
        studentTB.setPrefWidth(400);
        studentTB.setOnAction(e -> sendMessage());

        Button sendMessageButton = new Button("Send Message");
        sendMessageButton.setOnAction(e -> sendMessage());

        topPanel.getChildren().addAll(usernameTB, studentTB, sendMessageButton);

        chatArea = new TextArea();
        chatArea.setEditable(false);
        chatArea.setWrapText(true);

        ScrollPane scrollPane = new ScrollPane(chatArea);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);

        root.setCenter(scrollPane);
        root.setBottom(topPanel);

        Scene scene = new Scene(root, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();

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
            studentTB.clear(); // Clear the text field after sending
        }
    }

    private void addMessageToChatArea(String username, String message) {
        chatArea.appendText(username + ": " + message + "\n");
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
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String username = resultSet.getString("username");
                String message = resultSet.getString("message");
                addMessageToChatArea(username, message);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
*/

