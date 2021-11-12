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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class PatientPortalController {
    @FXML
    private Parent PatientPortalPane;
    private int userID;
    private FXMLLoader loader;
    @FXML
    private Label UpdateStatusLabel;
    @FXML
    private Label MessageStatusLabel;

    //Meant to hold patient records from the database
    @FXML
    private ListView <String> PatientRecordList;
    @FXML
    private TextArea PatientRecordTextArea;
    //This function runs when the pane is loaded.

    public PatientPortalController (int userID, FXMLLoader loader) {
        this.userID = userID;
        this.loader = loader;
    }

    @FXML
    public void initialize() {
        System.gc();
        System.out.println("Patient Pane loaded and initialization begun!");
        System.out.println("\tLoader: " + loader);
        System.out.println("\tuserID: " + userID);

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

    public Parent getParent() {
        return PatientPortalPane;
    }

    @FXML
    protected void onUpdateInfoClick(ActionEvent event) throws IOException {
        UpdateStatusLabel.setText("Your information was updated.");
        UpdateStatusLabel.setTextFill(Color.LIMEGREEN);
        //We also need to update the TextArea on the other tab to be correct.
    }

    @FXML
    protected void onSendToDoctorClick(ActionEvent event) {
        MessageStatusLabel.setText("Message Sent to Doctor.");
        MessageStatusLabel.setTextFill(Color.LIMEGREEN);
    }

    @FXML
    protected void onSendToNurseClick(ActionEvent event) {
        MessageStatusLabel.setText("Message Sent to Nurse.");
        MessageStatusLabel.setTextFill(Color.LIMEGREEN);
    }

    @FXML
    protected void onLogoutButtonClick(ActionEvent event) throws IOException {
        //@TO-DO: Add logic for ending the database connection. There is currently no database code
        //at all, but when that is added the connection needs to be stopped here.
        loader.setController(new LoginController(0, loader));
        loader.setLocation(getClass().getResource("LoginScreen.fxml"));
        loader.setRoot(null);
        Parent loginScreen = loader.load();
        Scene loginScreenScene = new Scene(loginScreen);

        //Get the stage
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(loginScreenScene);
        window.show();
    }

}