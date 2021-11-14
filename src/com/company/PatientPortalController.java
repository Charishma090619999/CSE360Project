package com.company;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class PatientPortalController {
    @FXML
    private Parent PatientPortalPane;
    private int userID;
    private FXMLLoader loader;
    //The current values are a placeholder. They should be obtained from the database using the userID
    //in the constructor. Might replace with a Patient object constructed using the userID.
    private String username = "username";
    private String password = "password";

    //The Patient Info Tab objects:
    //Might end up changing this to hold a Record object type so that we can display date and name in the
    //list and use a .getText() method to return the text itself. That way the text isn't so cramped in the list.
    @FXML
    private ListView<String> PatientRecordList; //Shows a list of health records' date and who entered them.
    @FXML
    private Label PatientNameLabel;
    @FXML
    private Label PatientDOBLabel;
    @FXML
    private Label PatientPharmacyLabel;
    @FXML
    private Label PatientPhoneLabel;
    @FXML
    private Label PatientAddressLabel;
    @FXML
    private Label PatientInsuranceLabel;
    @FXML
    private TextArea PatientRecordTextArea; //Shows the selected record's text.

    //The Edit Info Tab objects:
    @FXML
    private Label UpdateInfoStatusLabel;
    @FXML
    private TextField FirstNameField;
    @FXML
    private TextField LastNameField;
    @FXML
    private DatePicker DOBField;
    @FXML
    private TextField PharmacyField;
    @FXML
    private TextField PhoneField;
    @FXML
    private TextField AddressField;
    @FXML
    private TextField InsuranceField;
    @FXML
    private PasswordField verifyInfoEditField;

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

    //The Change Doctor Tab
    @FXML
    private Label ChangeDoctorStatusLabel;
    @FXML
    private ListView<String> ChangeDoctorList;
    @FXML
    private Label CurrentDoctorLabel;
    @FXML
    private Label SelectedDoctorLabel;
    @FXML
    private PasswordField verifyDoctorChangeField;

    //The Message Tab objects:
    @FXML
    private Label MessageStatusLabel;
    @FXML
    private ListView<String> MessagesList;
    @FXML
    private TextArea ReadMessagesField;
    @FXML
    private TextArea SentMessageField;

    public PatientPortalController(int userID, FXMLLoader loader) {
        this.userID = userID;
        this.loader = loader;
        //Set username and password from database.
    }

    //This function runs when the pane is loaded.
    @FXML
    public void initialize() {
        System.gc();
        System.out.println("Patient Pane loaded and initialization begun!");
        System.out.println("\tLoader: " + loader);
        System.out.println("\tuserID: " + userID);

        //Set up Patient Record List
        ObservableList<String> PatientRecordData = FXCollections.observableArrayList(
                "This is just a temporary data set",
                "We are gonna get this data from the database when it gets added",
                "Third Option");
        PatientRecordList.setItems(PatientRecordData);

        /*
         * This code is used to create a Listener that listens to the selected value
         * of the ListView object. We are going to use this for showing text, etc.
         * Here we are setting a listener to listen to the selected property, which is a value
         * that changes whenever an item is selected.
         */
        PatientRecordList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("Record List selected item: " + observable.getValue());
            PatientRecordTextArea.setText(observable.getValue());
            //System.out.println("Old value: " + oldValue + "\nNew Value: " + newValue);
        });

        //Set up Doctor List
        ObservableList<String> PatientDoctorList = FXCollections.observableArrayList(
                "Dr. Temp Name",
                "Dr. Other Temp Name",
                "Dr. Third Temp Name");
        ChangeDoctorList.setItems(PatientDoctorList);
        ChangeDoctorList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("Doctor List selected item: " + observable.getValue());
            SelectedDoctorLabel.setText(observable.getValue());
            //System.out.println("Old value: " + oldValue + "\nNew Value: " + newValue);
        });

        //Set up Messaging list
        //...

        //Set up Labels using database info.
    }

    public Parent getParent() {
        return PatientPortalPane;
    }

    //Behaviour for the button in the Change Username/Password Tab
    @FXML
    protected void onUpdateInfoClick(ActionEvent event) throws IOException {
        UpdateInfoStatusLabel.setText("Your information was updated.");
        UpdateInfoStatusLabel.setTextFill(Color.LIMEGREEN);

        if (Objects.equals(FirstNameField.getText(), "") ||
                Objects.equals(LastNameField.getText(), "") ||
                DOBField.getValue() == null ||
                Objects.equals(PharmacyField.getText(), "") ||
                Objects.equals(PhoneField.getText(), "") ||
                Objects.equals(AddressField.getText(), "") ||
                Objects.equals(InsuranceField.getText(), "")) {
            UpdateInfoStatusLabel.setTextFill(Color.RED);
            UpdateInfoStatusLabel.setText("A field is empty.");
        } else if (!Objects.equals(verifyInfoEditField.getText(), password)) {
            UpdateInfoStatusLabel.setTextFill(Color.RED);
            UpdateInfoStatusLabel.setText("Incorrect Password");
            //System.out.println("Password: " + verifyInfoEditField.getText());
        } else {
            UpdateInfoStatusLabel.setTextFill(Color.LIMEGREEN);
            UpdateInfoStatusLabel.setText("Information updated.");

            //Update the labels. After this we should also query the database.
            PatientNameLabel.setText(FirstNameField.getText() + " " + LastNameField.getText());
            PatientDOBLabel.setText(DOBField.getValue().toString());
            PatientPharmacyLabel.setText(PharmacyField.getText());
            PatientPhoneLabel.setText(PhoneField.getText());
            PatientAddressLabel.setText(AddressField.getText());
            PatientInsuranceLabel.setText(InsuranceField.getText());
        }
        verifyInfoEditField.setText("");
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
    protected void onChangeDoctorClick(ActionEvent event) {
        if (verifyDoctorChangeField.getText().equals(password)) {
            if (ChangeDoctorList.getSelectionModel().getSelectedItem() == null) {
                ChangeDoctorStatusLabel.setTextFill(Color.RED);
                ChangeDoctorStatusLabel.setText("Nothing selected.");
            } else {
                ChangeDoctorStatusLabel.setTextFill(Color.LIMEGREEN);
                ChangeDoctorStatusLabel.setText("Your doctor has been updated");
                CurrentDoctorLabel.setText(ChangeDoctorList.getSelectionModel().getSelectedItem());
            }
        } else {
            ChangeDoctorStatusLabel.setTextFill(Color.RED);
            ChangeDoctorStatusLabel.setText("Incorrect Password");
        }
        verifyDoctorChangeField.setText("");
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
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(loginScreenScene);
        window.show();
    }

}