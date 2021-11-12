package com.company;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        //Application starts at the login screen. Each page handles moving to other pages.
        //This is also where we should start the database connection.
        FXMLLoader loader = new FXMLLoader();
        //When loading, set the location to the file you want and the controller to that screen's controller.
        loader.setLocation(getClass().getResource("LoginScreen.fxml"));
        loader.setController(new LoginController(0, loader));

        Parent loginPane = loader.load();
        Scene scene = new Scene(loginPane, 600, 400);
        stage.setTitle("Team 41 Phase 3 Program");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch();
    }
}