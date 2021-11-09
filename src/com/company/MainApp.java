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
        Parent loginPane = FXMLLoader.load(getClass().getResource("LoginScreen.fxml"));
        Scene scene = new Scene(loginPane, 600, 400);
        stage.setTitle("Team 41 Phase 3 Program");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch();
    }
}