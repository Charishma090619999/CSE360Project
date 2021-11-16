package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Mark Karlsrud
 */
public class LoginPage implements Initializable {
    
    ProgressBar bar;
    Stage stage;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField usernameField;
    @FXML
    private Button loginButton;
    @FXML
    private Label loginTitle;
    @FXML
    private Label invalidLoginLabel;
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        Person.checkTables();
        // TODO
    }

    //method run when login button is pressed
    //checks database to ensure that the user has an actual account
    @FXML
    private void login(javafx.event.ActionEvent event) throws IOException 
    {
        try
        {
            if(PermissionsController.getInstance().logon(usernameField.getText(),passwordField.getText()))
            {
                // successful login
                invalidLoginLabel.setVisible(false);
                if(PermissionsController.getInstance().currentUserPermissions()==Permissions.ADMIN)
                {
                    //changeScene("NewPatient.fxml",event,"Doctor Accessibilities");
                    new NewPharmacyPage("adminPage.fxml",event,"Admin Accessibilities");
                }
                else if(PermissionsController.getInstance().currentUserPermissions()==Permissions.DOCTOR)
                {
                    new NewPharmacyPage("doctorPage.fxml",event,"Doctor Accessibilities");
                }
                else if(PermissionsController.getInstance().currentUserPermissions()==Permissions.PATIENT)
                {
                    new NewPharmacyPage("patientOptions.fxml",event,"Welcome");
                }
                else //nurse
                {
                    new NewPharmacyPage("nursePage.fxml",event,"Nurse Accessibilities");
                }
            }
            else
            {
                //login failed
                invalidLoginLabel.setVisible(true);
                System.out.println("failed login");
            }
        }
        catch(NonexistentRecordException e)
        {
            //login failed
            invalidLoginLabel.setVisible(true);
            System.out.println("failed login error");
        }
    }
}
