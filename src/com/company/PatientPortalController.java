package com.company;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class PatientPortalController {
    @FXML
    private Parent PatientPortalPane;
    private int userID;
    private FXMLLoader loader;

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

    //The Edit Account Tab objects:
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
    }

    //This function runs when the pane is loaded.
    @FXML
    public void initialize() {
        System.gc();
        System.out.println("Patient Pane loaded and initialization begun!");
        System.out.println("\tLoader: " + loader);
        System.out.println("\tuserID: " + userID);

        ObservableList<String> data = FXCollections.observableArrayList("This is just a temporary data set",
                "We are gonna get this data from the database when it gets added", "Third Option");
        PatientRecordList.setItems(data);

        //This code is used to create a Listener that listens to the selected value
        //of the ListView object. We are going to use this for showing text, etc.
        //Here we are setting a listener to listen to the selected property, which is a value
        //that changes whenever an item is selected.
        PatientRecordList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("Selected item: " + observable.getValue());
            PatientRecordTextArea.setText(observable.getValue());
            //System.out.println("Old value: " + oldValue + "\nNew Value: " + newValue);
        });
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
        } else if (!Objects.equals(verifyInfoEditField.getText(), "password")) { //temp password
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

        //We also need to update the TextArea on the other tab to be correct.
    }

    @FXML
    protected void onUpdateAccountClick(ActionEvent event) {
        UpdateAccountStatusLabel.setText("Your Account information was updated.");
        UpdateAccountStatusLabel.setTextFill(Color.LIMEGREEN);
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