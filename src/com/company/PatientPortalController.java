package com.company;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;

public class PatientPortalController {
    @FXML
    private Parent PatientPortalPane;
    //Meant to hold patient records from the database
    @FXML
    private ListView <String> PatientRecordList;
    @FXML
    private TextArea PatientRecordTextArea;

    //This function runs when the pane is loaded.
    @FXML
    public void initialize() {
        System.gc();
        System.out.println("Patient Pane loaded and initialization begun!");

        //This code is used to create a Listener that listens to the selected value
        //of the ListView object. We are going to use this for showing text, etc.
        ObservableList<String> data = FXCollections.observableArrayList("This is just a temporary data set",
                "We are gonna get this data from the database when it gets added", "Third Option");
        PatientRecordList.setItems(data);

        PatientRecordList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                // Your action here
                System.out.println("Selected item: " + observable.getValue());
                PatientRecordTextArea.setText(observable.getValue());
            }
        });
    }

    @FXML
    protected void onLogoutButtonClick(ActionEvent event) throws IOException {
        //@TO-DO: Add logic for ending the database connection. There is currently no database code
        //at all, but when that is added the connection needs to be stopped here.
        Parent loginScreen = FXMLLoader.load(getClass().getResource("LoginScreen.fxml"));
        Scene loginScreenScene = new Scene(loginScreen);

        //Get the stage
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(loginScreenScene);
        window.show();
    }

}