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
    private int userID = 0;
    private FXMLLoader loader;

    public CreateAccountController(int userID, FXMLLoader loader) {
        //If you were logged in before, now you aren't.
        this.userID = 0;
        this.loader = loader;
    }

    @FXML
    public void initialize() {
        System.gc();
        System.out.println("Create Account Pane loaded and initialization begun!");
        System.out.println("\tLoader: " + loader);
        System.out.println("\tuserID: " + userID);
    }

    public Parent getParent() {
        return CreateAccountPane;
    }

    @FXML
    protected void onCreateAccountButtonClick(ActionEvent event) throws IOException {
        //@TO-DO: Check each field to see if it is complete, then send the info to the database
        //Also need to pass through the new database ID to the portal screen.
        userID = 1000; // temporary value used to demonstrate data passthrough
        loader.setController(new PatientPortalController(userID, loader));
        loader.setLocation(getClass().getResource("PatientPortal.fxml"));
        loader.setRoot(null);
        Parent patientPortal = loader.load();
        Scene patientPortalScene = new Scene(patientPortal);

        //Get the stage
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(patientPortalScene);
        window.show();
    }

    @FXML
    protected void onBackClick(ActionEvent event) throws IOException {
        loader.setController(new LoginController(0, loader));
        loader.setLocation(getClass().getResource("LoginScreen.fxml"));
        loader.setRoot(null);
        Parent loginScreen = loader.load();
        Scene loginScreenScene = new Scene(loginScreen);

        //Get the stage
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(loginScreenScene);
        window.show();
    }
}