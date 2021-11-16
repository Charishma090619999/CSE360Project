package application;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * 
 * This class allows for movement between user interface panels
 */
public class NewPharmacyPage
{
    public NewPharmacyPage(String fxml, javafx.event.ActionEvent event, String title) throws IOException
    {
        changeScene(fxml,event,title);
    }
    
    public void changeScene(String fxml, javafx.event.ActionEvent event, String title) throws IOException
    {
        Node node=(Node) event.getSource();
        Stage s=(Stage) node.getScene().getWindow();
        s.setTitle(title);
        Parent root = FXMLLoader.load(getClass().getResource(fxml));
        Scene scene = new Scene(root);
        s.setScene(scene);
        s.show();
    }
}
