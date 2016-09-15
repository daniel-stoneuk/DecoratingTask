package com.danielstone;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Created by user on 15/09/2016.
 */
public class NewCustomerWindow {

    static Customer result = null;

    public static Customer display(int customerId) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("New customer.");
        window.setMinWidth(250);

        Label firstNameLabel = new Label("First Name: ");
        TextField firstNameTextField = new TextField();
        HBox firstNameHb = new HBox();
        firstNameHb.getChildren().addAll(firstNameLabel, firstNameTextField);
        firstNameHb.setSpacing(10);

        Label surnameLabel = new Label("Last Name: ");
        TextField surnameTextField = new TextField();
        HBox surnameHb = new HBox();
        surnameHb.getChildren().addAll(surnameLabel, surnameTextField);
        surnameHb.setSpacing(10);

        Label telephoneLabel = new Label("Telephone: ");
        TextField telephoneTextField = new TextField();
        HBox telephoneHb = new HBox();
        telephoneHb.getChildren().addAll(telephoneLabel, telephoneTextField);
        telephoneHb.setSpacing(10);

        Button addButton = new Button("Add Customer");
        addButton.setOnAction(event -> {
            result = new Customer(customerId,
                    firstNameTextField.getText(),
                    surnameTextField.getText(),
                    telephoneTextField.getText());
            window.close();
        });

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(firstNameHb, surnameHb, telephoneHb, addButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

        return result;
    }

}
