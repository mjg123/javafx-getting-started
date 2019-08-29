package com.example.javafx;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Hello extends Application {

    public static void main(String[] args) {

        Twilio.init(
            System.getenv("ACCOUNT_SID"),
            System.getenv("AUTH_TOKEN"));

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("My First JavaFX App");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(12, 12, 12, 12));
        grid.setMinSize(400, 200);

        Label numberLabel = new Label("Phone Number:");
        numberLabel.setMinWidth(100);
        numberLabel.setAlignment(Pos.CENTER_RIGHT);
        grid.add(numberLabel, 0, 0);

        TextField numberField = new TextField();
        numberLabel.setLabelFor(numberField);
        grid.add(numberField, 1, 0);

        Label messageLabel = new Label("Message:");
        messageLabel.setMinWidth(100);
        messageLabel.setAlignment(Pos.CENTER_RIGHT);
        grid.add(messageLabel, 0, 1);

        TextField messageField = new TextField();
        messageLabel.setLabelFor(messageField);
        grid.add(messageField, 1, 1);

        Button sendButton = new Button("Send SMS");
        grid.add(sendButton, 1, 2);

        sendButton.setOnMouseClicked(mouseEvent -> {
                Message.creator(
                    new PhoneNumber(numberField.getText()),
                    new PhoneNumber("<YOUR TWILIO PHONE NUMBER>"),
                    messageField.getText())
                    .create();
                numberField.setText("");
                messageField.setText("");
            }
        );

        Scene scene = new Scene(grid, 400, 200);
        primaryStage.setScene(scene);

        primaryStage.show();
    }
}
