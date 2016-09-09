package com.danielstone;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.*;

/**
 * Created by user on 08/09/2016.
 */
public class CustomerListWindow {

    File file;

    SaveFile state;

    public CustomerListWindow(File file) {
        this.file = file;
    }

    public void display() {
        Stage window = new Stage();

        window.initModality(Modality.NONE);
        window.setTitle("Customers");
        window.setMinWidth(250);

        getSaveFile();

        javafx.scene.control.Label label = new javafx.scene.control.Label();

        if (file != null) label.setText(file.getPath());

        javafx.scene.control.Button button = new javafx.scene.control.Button("Log stuff");
        button.setOnAction(event -> updateSaveFile());

        //https://www.mkyong.com/java/jackson-2-convert-java-object-to-from-json/



        ListView<String> list = new ListView<>();

        ObservableList<String> items = FXCollections.observableArrayList ();
        for (Customer customer :
                state.getCustomers()) {
            items.add(customer.getFirstName());
        }
        list.setItems(items);

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, button,list);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.show();
    }

    public void updateSaveFile() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            if (br.readLine() == null) {
                System.out.println("No errors, and file empty");
                state = new SaveFile();
                mapper.writeValue(file, state);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            SaveFile loadedFile = mapper.readValue(file, SaveFile.class);
            if (mapper.writeValueAsString(state).equals(mapper.writeValueAsString(loadedFile))) {
                System.out.println("No changes");
            } else {
                System.out.println("updating");
                mapper.writeValue(file, state);
            }
            System.out.println(loadedFile.getCustomers().get(0).getFirstName());

        } catch (IOException e) {
            e.printStackTrace();
            ErrorAlert.display();
        }
    }

    public void getSaveFile() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            state = mapper.readValue(file, SaveFile.class);
        } catch (IOException e) {
            System.out.println("Failed to read file");
            BufferedReader br = null;
            try {
                br = new BufferedReader(new FileReader(file));
                if (br.readLine() == null) {
                    System.out.println("No errors, and file empty");
                    state = new SaveFile();
                    updateSaveFile();
                }
            } catch (Exception e1) {
                e1.printStackTrace();
                ErrorAlert.display();
                state = new SaveFile();
                updateSaveFile();
            }
        }
    }

}
