package com.company;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

//import com.mysql.cj.xdevapi.Statement;

/**
 * Author: Charishma
 */

public class LoginController {
    @FXML
    private Parent LoginPane;
    private int userID;
    private FXMLLoader loader;
    @FXML
    private TextField patientUsername;
    @FXML
    
    private PasswordField patientPassword;
    

    public LoginController () {
        this.loader = new FXMLLoader();
    }

    //It is preferable that all LoginController objects be instantiated with
    //new LoginController(0, loader); but the initialize function will fix it.
    public LoginController (int userID, FXMLLoader loader) {
        this.loader = loader;
    }

    @FXML
    public void initialize() {
        System.gc();
        //When you go to the login screen, the userID is zeroed out to exit the account.
        this.userID = 0;
        System.out.println("Login Pane loaded and initialization begun!");
        System.out.println("\tLoader: " + loader);
        System.out.println("\tuserID: " + userID);
    }

    public Parent getParent() {
        return LoginPane;
    }

    @FXML
    protected void onEmployeeLoginClick(ActionEvent event) throws IOException {

    }

    @FXML
    protected void onPatientLoginClick(ActionEvent event) throws IOException {
        //@TO-DO: Add logic for logging in, authentication, etc. Currently, it just lets you in
        String username = patientUsername.getText();
        String password = patientPassword.getText();
        try {
            connection conn = new connection();
            Connection connect = conn.getdbconnection();
            Statement st = connect.createStatement();
            ResultSet rs = st.executeQuery("SELECT PatientID,Username,Password FROM PatientData WHERE Username='" + username + "' AND Password='" + password + "'");
            if (rs.next() == false) {
                System.out.println("Invalid credentials");
            } else {
                userID=rs.getInt("PatientID");
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
        }
        catch(Exception e){
            System.err.println(e.getMessage());
        }
    }


    @FXML
    protected void onCreateAccountClick(ActionEvent event) throws IOException {
        loader.setController(new CreateAccountController(userID, loader));
        loader.setLocation(getClass().getResource("CreateAccount.fxml"));
        loader.setRoot(null);
        Parent createAccount = loader.load();
        Scene createAccountScene = new Scene(createAccount);

        //Get the stage
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(createAccountScene);
        window.show();
    }
}