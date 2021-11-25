package com.company;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class NursePortalController {

    private int userID;
    private FXMLLoader loader;
    private connection con;

    private Nurse nurse;

    //The Patient Info Tab
    @FXML
    private ListView<Patient> PatientList;
    @FXML
    private ListView<Record> PatientRecordList; //Shows a list of health records' date and who entered them.
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
    private Label DoctorLabel;
    @FXML
    private Label NurseLabel;
    @FXML
    private TextArea PatientRecordTextArea; //Shows the selected record's text.

    //The Add Record tab
    @FXML
    private ListView<Patient> AddRecordList;
    @FXML
    private TextField HeightField;
    @FXML
    private TextField WeightField;
    @FXML
    private TextField HeartRateField;
    @FXML
    private TextField BloodField;
    @FXML
    private TextField TemperatureField;
    @FXML
    private TextField BreathField;
    @FXML
    private TextArea ObservationField;
    @FXML
    private Label AddRecordStatusLabel;

    //The Add/Remove Doctors tab
    @FXML
    private ListView<Doctor> AvailableDoctorList;
    @FXML
    private ListView<Doctor> YourDoctorList;
    @FXML
    private Label ChangeDoctorStatusLabel;
    @FXML
    private PasswordField verifyDoctorChangeField;

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

    //The Message Tab objects:
    @FXML
    private ListView<Patient> PatientMessageList;
    @FXML
    private Label MessageStatusLabel;
    @FXML
    private ListView<Message> MessagesList;
    @FXML
    private TextArea ReadMessageField;
    @FXML
    private TextArea SentMessageField;

    public NursePortalController(int userID, FXMLLoader loader, connection con) {
        this.userID = userID;
        this.loader = loader;
        this.con = con;
        //Set username and password from database.
    }

    //This function runs when the pane is loaded.
    @FXML
    public void initialize() {
        System.gc();
        System.out.println("Nurse Pane loaded and initialization begun!");
        System.out.println("\tLoader: " + loader);
        System.out.println("\tuserID: " + userID);

        try {
            Connection connection = con.getdbconnection();
            Statement s1 = connection.createStatement();

            //Get this nurse's info to create an object
            ResultSet nurseInfo = s1.executeQuery("SELECT " +
                    "FirstName, " +
                    "LastName, " +
                    "Username, " +
                    "Password " +
                    "FROM employee WHERE userID=" + userID + ";");

            nurseInfo.next();
            nurse = new Nurse(nurseInfo.getString("FirstName"),
                    nurseInfo.getString("LastName"),
                    userID,
                    nurseInfo.getString("Username"),
                    nurseInfo.getString("Password")
            );

            System.out.println(userID);

            //Get Patient Info where the doctorID equals the doctors who have the nurse's id for nurseID.
            ResultSet patientResults = s1.executeQuery("SELECT " +
                    "FirstName, " +
                    "LastName, " +
                    "PatientID, " +
                    "DOB, " +
                    "Pharmacy, " +
                    "PhoneNo, " +
                    "Address, " +
                    "Insurance, " +
                    "DoctorID " +
                    "FROM PatientData WHERE DoctorID=(" +
                    "SELECT userID FROM employee WHERE nurseID=" + userID + ");"
            );

            //While there is a patient belonging to a doctor of this nurse
            while (patientResults.next()) {
                System.out.println(patientResults.getString("FirstName"));
                Statement s2 = connection.createStatement();
                //Get the doctor's name for the current patient from the result. There has to be a doctor since
                //that is how we found the patient.
                ResultSet doctorInfo = s2.executeQuery("SELECT " +
                        "FirstName, " +
                        "LastName, " +
                        "userID " +
                        "FROM employee WHERE EmployeeType=0 AND userID=" + patientResults.getInt("DoctorID") + ";"
                );
                doctorInfo.next();
                Doctor tempDoc = new Doctor(
                        doctorInfo.getString("FirstName"),
                        doctorInfo.getString("LastName"),
                        doctorInfo.getInt("userID")
                );
                Patient newPat = new Patient(
                        patientResults.getString("FirstName"),
                        patientResults.getString("LastName"),
                        patientResults.getInt("PatientID"),
                        patientResults.getString("DOB"),
                        patientResults.getString("Pharmacy"),
                        patientResults.getString("PhoneNo"),
                        patientResults.getString("Address"),
                        patientResults.getString("Insurance"),
                        tempDoc.toString(),
                        nurse.toString()
                );
                PatientList.getItems().add(newPat);
                AddRecordList.getItems().add(newPat);
                PatientMessageList.getItems().add(newPat);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void onAddRecordClick(ActionEvent event) {

    }

    @FXML
    protected void onAddDoctorClick(ActionEvent event) {

    }

    @FXML
    protected void onRemoveDoctorClick(ActionEvent event) {

    }

    @FXML
    protected void onChangeDoctorClick(ActionEvent event) {

    }

    @FXML
    protected void onUpdateAccountClick(ActionEvent event) {
        if (OldUsernameField.getText().equals("") ||
                OldPasswordField.getText().equals("") ||
                NewUsernameField.getText().equals("") ||
                NewPasswordField.getText().equals("")) {
            UpdateAccountStatusLabel.setTextFill(Color.RED);
            UpdateAccountStatusLabel.setText("All fields must be completed");
        } else if (!OldUsernameField.getText().equals(nurse.getUsername())) {
            System.out.println("Entered Username: " + nurse.getUsername());
            UpdateAccountStatusLabel.setTextFill(Color.RED);
            UpdateAccountStatusLabel.setText("Incorrect Username");
        } else if (!OldPasswordField.getText().equals(nurse.getPassword())) {
            System.out.println("Entered Password: " + nurse.getPassword());
            UpdateAccountStatusLabel.setTextFill(Color.RED);
            UpdateAccountStatusLabel.setText("Incorrect Password");
            //This check will be replaced by checking if the new username equals any current username
            //and **not  the current user's**. This is just a temporary test.
        } else if (NewPasswordField.getText().length() < 8) {
            UpdateAccountStatusLabel.setTextFill(Color.RED);
            UpdateAccountStatusLabel.setText("Password must be 8 or more characters");
        } else {

            try {
                boolean unique = true;
                Connection connect = con.getdbconnection();
                Statement s = connect.createStatement();
                ResultSet rs = s.executeQuery("SELECT Username from employee WHERE " +
                        "Username='" + NewUsernameField.getText() + "' " +
                        "AND userID!=" + userID + ";");
                if (rs.next()) {
                    unique = false;
                }

                if (unique) {

                    s.executeUpdate("UPDATE employee " +
                            "SET Username='" + NewUsernameField.getText() +
                            "',Password='" + NewPasswordField.getText() +
                            "' WHERE userID = " + userID + ";"
                    );

                    nurse.setUsername(NewUsernameField.getText());
                    nurse.setPassword(NewPasswordField.getText());

                    OldUsernameField.setText("");
                    OldPasswordField.setText("");
                    NewUsernameField.setText("");
                    NewPasswordField.setText("");

                    UpdateAccountStatusLabel.setTextFill(Color.LIMEGREEN);
                    UpdateAccountStatusLabel.setText("Account updated");

                } else {
                    UpdateAccountStatusLabel.setTextFill(Color.RED);
                    UpdateAccountStatusLabel.setText("That username is already in use!");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //Handles status messages for the Change username/password tab fields
    @FXML
    protected void onOldUsernameFieldType(Event event) {
        if (!OldUsernameField.getText().equals(nurse.getUsername())) {
            OldUsernameStatusLabel.setText("Incorrect Username");
            OldUsernameStatusLabel.setTextFill(Color.RED);
        } else {
            OldUsernameStatusLabel.setTextFill(Color.LIMEGREEN);
            OldUsernameStatusLabel.setText("Username is correct");
        }
    }

    @FXML
    protected void onOldPasswordFieldType(Event event) {
        if (!OldPasswordField.getText().equals(nurse.getPassword())) {
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
        } else if (NewUsernameField.getText().equals(nurse.getUsername())) {
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
