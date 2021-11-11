package com.company;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CreateAccountController {
    @FXML
    private Parent CreateAccountPane;

    @FXML
    public void initialize() {
        System.gc();
        System.out.println("Create Account Pane loaded and initialization begun!");
    }

    @FXML
    protected void onCreateAccountButtonClick(ActionEvent event) throws IOException {
        //@TO-DO: Check each field to see if it is complete, then send the info to the database
        //Also need to pass through the new database ID to the portal screen.
        Parent patientPortal = FXMLLoader.load(getClass().getResource("PatientPortal.fxml"));
        Scene patientPortalScene = new Scene(patientPortal);

        //Get the stage
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(patientPortalScene);
        window.show();
    }
}