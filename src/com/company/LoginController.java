package com.company;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    @FXML
    private Parent LoginPane;

    @FXML
    public void initialize() {
        System.gc();
        System.out.println("Login Pane loaded and initialization begun!");
    }

    @FXML
    protected void onEmployeeLoginClick(ActionEvent event) throws IOException {

    }

    @FXML
    protected void onPatientLoginClick(ActionEvent event) throws IOException {
        //@TO-DO: Add logic for logging in, authentication, etc. Currently, it just lets you in
        //Might need to construct a PatientPortalController obj that holds the current user
        //Basically we can construct a PatientPortalController obj and assign it to the loader,
        //that way we can pass the user data through. Might have to do it for all screens.
        Parent patientPortal = FXMLLoader.load(getClass().getResource("PatientPortal.fxml"));
        Scene patientPortalScene = new Scene(patientPortal);

        //Get the stage
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(patientPortalScene);
        window.show();
    }

    @FXML
    protected void onCreateAccountClick(ActionEvent event) throws IOException {
        Parent createAccount = FXMLLoader.load(getClass().getResource("CreateAccount.fxml"));
        Scene createAccountScene = new Scene(createAccount);

        //Get the stage
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(createAccountScene);
        window.show();
    }
}