package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * 
 */
public class PatientOptionsPage implements Initializable{
    @FXML
    private Button dataButton;
    @FXML
    private Button viewMetricsButton;
    @FXML
    private Button contactInfoButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }

    @FXML
    private void enterData(javafx.event.ActionEvent event) throws IOException
    {
        new NewPharmacyPage("dataEntry.fxml",event,"Enter Data");
    }
    
    @FXML
    private void viewMetrics(javafx.event.ActionEvent event) throws IOException
    {
        new NewPharmacyPage("patientMetrics.fxml",event,"Patient Metrics");
    }

    @FXML
    private void updateContactInfo(javafx.event.ActionEvent event) throws IOException
    {
        new NewPharmacyPage("patientInfo.fxml",event,"Contact Information");
    }
    
    @FXML
    public void logOut(javafx.event.ActionEvent event) throws IOException
    {
        PermissionsController.getInstance().logout();
        new NewPharmacyPage("loginPage.fxml",event,"Login");
    }
}
