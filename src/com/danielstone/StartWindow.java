package com.danielstone;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class StartWindow extends Application {

    // Window to draw on
    private Stage window;

    // Strings
    private String strWindowTitle = "DecoratorHelper 5000";
    private String strAppName = "Decorator Helper 5000";
    private String strOpenFileButton = "Open saved state";
    private String strCreateFileButton = "New";

    private final String preferredFont = "Segoe UI";

    public static void main(String[] args) {
	    launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle(strWindowTitle);

        // create root view
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20, 20, 20, 20));
        root.setSpacing(20);

        Label titleLabel = new Label(strAppName);
        titleLabel.setFont(new Font(preferredFont, 30));

        HBox buttons = new HBox();
        buttons.setSpacing(20);

        Button openFileButton = new Button(strOpenFileButton);
        openFileButton.setOnAction(event -> {
            try {
                openFileButtonClicked(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        Button createFileButton = new Button(strCreateFileButton);
        createFileButton.setOnAction(event -> {
            try {
                createFileButtonClicked(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        buttons.getChildren().addAll(openFileButton,createFileButton);

        root.getChildren().addAll(titleLabel,buttons);

        Scene scene = new Scene(root);
        window.setScene(scene);
        window.show();
    }

    private void openFileButtonClicked(ActionEvent actionEvent) throws IOException {
        FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle(strOpenFileButton);
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("decorator save file", "*.decorate")
        );
        File f = fileChooser.showOpenDialog(window);

        CustomerListWindow clw = new CustomerListWindow(f);
        clw.display();
        window.close();
    }

    private void createFileButtonClicked(ActionEvent actionEvent) throws IOException {
        FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle(strOpenFileButton);
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("decorator save file", "*.decorate")
        );
        File f = fileChooser.showSaveDialog(window);
        try {
            f.createNewFile();
            if (f.exists()) {
                CustomerListWindow clw = new CustomerListWindow(f);
                clw.display();
                window.close();
            } else System.out.println("WTH WHY AM I FAILING");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
