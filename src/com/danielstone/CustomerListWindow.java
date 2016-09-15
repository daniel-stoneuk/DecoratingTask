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
import java.util.Random;

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


        ListView<String> list = new ListView<>();

        ObservableList<String> items = FXCollections.observableArrayList ();
        for (Customer customer :
                state.getCustomers()) {
            items.add(customer.getFirstName() + " " + customer.getSurname());
        }
        list.setItems(items);
        
        javafx.scene.control.Button button = new javafx.scene.control.Button("New Customer");
        button.setOnAction(event -> {
            Customer newCustomer = null;
            newCustomer = NewCustomerWindow.display(getNewCustomerId());
            if (newCustomer != null) {
                state.getCustomers().add(newCustomer);
                updateSaveFile();
                ObservableList<String> itemsUpdated = FXCollections.observableArrayList ();
                for (Customer customer :
                        state.getCustomers()) {
                    itemsUpdated.add(customer.getFirstName() + " " + customer.getSurname());
                }
                list.setItems(itemsUpdated);
            }
            else ErrorAlert.display("Error", "Error creating new customer");

        } );

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, button,list);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.show();
    }

    public int getNewCustomerId() {
        int customerArraySize = state.getCustomers().size();
        int[] customerIds = new int[customerArraySize];
        for (int i = 0; i < customerArraySize; i++) {
            customerIds[i] = state.getCustomers().get(i).getCustomerId();
        }
        boolean valid = false;
        int result = -1;
        Random random = new Random();
        do {
            result = random.nextInt(999999);
            if (!contains(customerIds, result)) {
                valid = true;
            }
        } while(!valid);
        return result;
    }

    public static boolean contains(final int[] array, final int valueToFind) {
        if (array == null) {
            return false;
        }
        int startIndex = 0;
        for (int i = startIndex; i < array.length; i++) {
            if (valueToFind == array[i]) {
                return true;
            }
        }
        return false;
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

        } catch (IOException e) {
            e.printStackTrace();
            ErrorAlert.display("Error", "Error while updating file. Please restart the application and try again.");
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
                ErrorAlert.display("Error", "Error while opening file");
                state = new SaveFile();
                updateSaveFile();
            }
        }
    }

}
