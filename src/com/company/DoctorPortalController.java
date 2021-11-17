package com.company;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class DoctorPortalController {

    private int userID;
    private FXMLLoader loader;
    private connection con;

    //Placeholders until actual Doctor objects are added
    private String username = "username";
    private String password = "password";

    //The Edit Account (change username/pass) Tab objects:
    @FXML
    private Label UpdateAccountStatusLabel;
    @FXML
    private TextField OldUsernameField;
    @FXML
    private PasswordField OldPasswordField;
    @FXML
    private TextField NewUsernameField;
    @FXML
    private PasswordField NewPasswordField;
    @FXML
    private Label OldUsernameStatusLabel;
    @FXML
    private Label OldPasswordStatusLabel;
    @FXML
    private Label NewUsernameStatusLabel;
    @FXML
    private Label NewPasswordStatusLabel;

    public DoctorPortalController(int userID, FXMLLoader loader, connection con) {
        this.userID = userID;
        this.loader = loader;
        this.con = con;
        //Set username and password from database.
    }

    //This function runs when the pane is loaded.
    @FXML
    public void initialize() {
        System.gc();
        System.out.println("Doctor Pane loaded and initialization begun!");
        System.out.println("\tLoader: " + loader);
        System.out.println("\tuserID: " + userID);
    }

    @FXML
    protected void onAddDoctorClick(ActionEvent event) {

    }

    @FXML
    protected void onAddNurseClick(ActionEvent event) {

    }

    @FXML
    protected void onUpdateAccountClick(ActionEvent event) {
        if (OldUsernameField.getText().equals("") ||
                OldPasswordField.getText().equals("") ||
                NewUsernameField.getText().equals("") ||
                NewPasswordField.getText().equals("")) {
            UpdateAccountStatusLabel.setTextFill(Color.RED);
            UpdateAccountStatusLabel.setText("All fields must be completed");
        } else if (!OldUsernameField.getText().equals(username)) {
            UpdateAccountStatusLabel.setTextFill(Color.RED);
            UpdateAccountStatusLabel.setText("Incorrect Username");
        } else if (!OldPasswordField.getText().equals(password)) {
            UpdateAccountStatusLabel.setTextFill(Color.RED);
            UpdateAccountStatusLabel.setText("Incorrect Password");
            //This check will be replaced by checking if the new username equals any current username
            //and **not  the current user's**. This is just a temporary test.
        } else if (NewUsernameField.getText().equals(username)) {
            UpdateAccountStatusLabel.setTextFill(Color.RED);
            UpdateAccountStatusLabel.setText("New Username must be unique");
        } else if (NewPasswordField.getText().length() < 8) {
            UpdateAccountStatusLabel.setTextFill(Color.RED);
            UpdateAccountStatusLabel.setText("New Username must be unique");
        } else {
            //Replace with database query and a change to the Patient object (when we add it)
            this.username = NewUsernameField.getText();
            this.password = NewPasswordField.getText();
            OldUsernameField.setText("");
            OldPasswordField.setText("");
            NewUsernameField.setText("");
            NewPasswordField.setText("");
            UpdateAccountStatusLabel.setTextFill(Color.LIMEGREEN);
            UpdateAccountStatusLabel.setText("Account updated");
        }
    }

    //Handles status messages for the Change username/password tab fields
    @FXML
    protected void onOldUsernameFieldType(Event event) {
        if (!OldUsernameField.getText().equals(username)) {
            OldUsernameStatusLabel.setText("Incorrect Username");
            OldUsernameStatusLabel.setTextFill(Color.RED);
        } else {
            OldUsernameStatusLabel.setTextFill(Color.LIMEGREEN);
            OldUsernameStatusLabel.setText("Username is correct");
        }
    }

    @FXML
    protected void onOldPasswordFieldType(Event event) {
        if (!OldPasswordField.getText().equals(password)) {
            OldPasswordStatusLabel.setText("Incorrect Password");
            OldPasswordStatusLabel.setTextFill(Color.RED);
        } else {
            OldPasswordStatusLabel.setTextFill(Color.LIMEGREEN);
            OldPasswordStatusLabel.setText("Password is correct");
        }
    }

    @FXML
    protected void onNewUsernameFieldType(Event event) {
        //We would replace this condition to be a check for if the username is used
        //by any other patient account and not by the current patient account.
        if (NewUsernameField.getText().equals("")) {
            NewUsernameStatusLabel.setTextFill(Color.RED);
            NewUsernameStatusLabel.setText("Must supply a username");
        } else if (NewUsernameField.getText().equals(username)) {
            NewUsernameStatusLabel.setTextFill(Color.RED);
            NewUsernameStatusLabel.setText("Username already in use");
        } else {
            NewUsernameStatusLabel.setTextFill(Color.LIMEGREEN);
            NewUsernameStatusLabel.setText("Username is unique");
        }
        //This runs for every character typed. Hopefully that isn't too inefficient. It is good for a live update
    }

    @FXML
    protected void onNewPasswordFieldType(Event event) {
        if (NewPasswordField.getText().equals("")) {
            NewPasswordStatusLabel.setTextFill(Color.RED);
            NewPasswordStatusLabel.setText("Must supply a valid password");
        } else if (NewPasswordField.getText().length() < 8) {
            NewPasswordStatusLabel.setTextFill(Color.RED);
            NewPasswordStatusLabel.setText("Password too short. Must be 8 characters or more");
        } else if (NewPasswordField.getText().length() < 12) {
            NewPasswordStatusLabel.setTextFill(Color.ORANGE);
            NewPasswordStatusLabel.setText("Password is valid, but a longer one is suggested");
        } else {
            NewPasswordStatusLabel.setTextFill(Color.LIMEGREEN);
            NewPasswordStatusLabel.setText("Strong, valid Password");
        }
    }

    @FXML
    protected void onSendToPatientClick(ActionEvent event) {

    }

    @FXML
    protected void onLogoutButtonClick(ActionEvent event) throws IOException {
        loader.setController(new LoginController(0, loader, con));
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
