package com.company;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    @FXML
    private Pane loginPane;

    @FXML
    protected void onEmployeeLoginClick(ActionEvent event) throws IOException {

    }

    @FXML
    protected void onPatientLoginClick(ActionEvent event) throws IOException {
        //@TO-DO: Add logic for logging in, authentication, etc. Currently, it just lets you in
        Parent patientPortal = FXMLLoader.load(getClass().getResource("PatientPortal.fxml"));
        Scene patientPortalScene = new Scene(patientPortal);

        //I am assuming that we are going to need to do all the initialization that we can't do in the
        //FXML in these button classes. Oh well, if it works, it works.

        //Get the stage
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(patientPortalScene);
        window.show();
    }

    @FXML
    protected void onCreateAccountClick(ActionEvent event) throws IOException {

    }
}