package com.company;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.control.PasswordField;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class CreateAccountController {
    @FXML
    private Parent CreateAccountPane;
    private int userID = 0;
    private FXMLLoader loader;
    @FXML
    private TextField FirstNameField;
    @FXML
    private TextField LastNameField;
    
    @FXML
    private DatePicker DOBPicker;
    private TextField PharmacyField;
    private TextField PhoneNumField;
    private TextField InsuranceField;
    private TextField UsernameField;
    private PasswordField PasswordField;
    private Label CreateAccountStatusLabel;
    private TextField AddressField;
    
    
    

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
    	if (FirstNameField.getText().equals("") || LastNameField.getText().equals("")) {
            CreateAccountStatusLabel.setTextFill(Color.RED);
            CreateAccountStatusLabel.setText("First and last name is required");
        } else if (DOBPicker.getValue() == null) {
            CreateAccountStatusLabel.setTextFill(Color.RED);
            CreateAccountStatusLabel.setText("You must provide your date of birth");
        } else if (AddressField.getText().equals("")) {
            CreateAccountStatusLabel.setTextFill(Color.RED);
            CreateAccountStatusLabel.setText("Address is required");
        } else if (PhoneNumField.getText().equals("")) {
            CreateAccountStatusLabel.setTextFill(Color.RED);
            CreateAccountStatusLabel.setText("Phone number is required");
        } else if (InsuranceField.getText().equals("")) {
            CreateAccountStatusLabel.setTextFill(Color.RED);
            CreateAccountStatusLabel.setText("Please enter an insurer");
        } else if (PasswordField.getText().length() < 8) {
            CreateAccountStatusLabel.setTextFill(Color.RED);
            CreateAccountStatusLabel.setText("Password must be 8 or more characters long");
        //We need to check the database for if the username is unique or not.
        } else {
            try{
                connection conn=new connection();
                Connection connect=conn.getdbconnection();
                Statement st=connect.createStatement();
                ResultSet rt=st.executeQuery("SELECT * from PatientData where Username='"+UsernameField.getText()+"'");
                if(rt.next())
                {
                    CreateAccountStatusLabel.setText("Username already exists");
                }
                else
                {
                    int ID;
                    ResultSet rt1=st.executeQuery("SELECT Count(*) from PatientData");
                    if(rt1.next())
                    {
                        ID=rt1.getInt("Count");
                        userID=-(ID+1);
                        System.out.println(AddressField.getText());
                        st.executeUpdate("INSERT INTO PatientData VALUES('"+FirstNameField.getText()+"','"+LastNameField.getText()+"','"+DOBPicker.getValue().toString()+"', ' "+PharmacyField.getText()+" ' ,'"+Integer.parseInt(PhoneNumField.getText())+"','"+AddressField.getText()+"','"+InsuranceField.getText()+"','"+UsernameField.getText()+"','"+PasswordField.getText()+"',"+userID+")");
                   
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
            }
            catch(Exception e)
            {
            	System.err.println(e.getMessage());
            }
            }
             
            // temporary value used to demonstrate data passthrough
            
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