package com.danielstone;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.codehaus.jackson.map.ObjectMapper;

import java.awt.*;
import java.awt.Button;
import java.awt.Label;
import java.io.File;

/**
 * Created by user on 08/09/2016.
 */
public class CustomerListWindow {

    File saveFile;

    public CustomerListWindow(File saveFile) {
        this.saveFile = saveFile;
    }

    public void display() {
        Stage window = new Stage();

        window.initModality(Modality.NONE);
        window.setTitle("Customers");
        window.setMinWidth(250);

        javafx.scene.control.Label label = new javafx.scene.control.Label();

        if (saveFile != null) label.setText(saveFile.getPath());

        javafx.scene.control.Button closeButton = new javafx.scene.control.Button("Close");
        closeButton.setOnAction(event -> window.close());

        //https://www.mkyong.com/java/jackson-2-convert-java-object-to-from-json/

        ObjectMapper mapper = new ObjectMapper();


        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, closeButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.show();
    }

}
