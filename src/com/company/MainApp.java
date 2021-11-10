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

        //By the way, the way that the controller classes swap the scene in the window causes
        //a memory leak. It gets worse if you change the scene a lot, as it keeps making new scenes.
        //If one of you can make a fix for that, that would be great, but it is good enough for now.
    }

    public static void main(String[] args) {
        Application.launch();
    }
}