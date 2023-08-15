package com.tokioschool.controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class BattleGUIApplication extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }

    /**
	 * This function sets up the JavaFX stage and scene for a BattleGUIApp, loads the FXML resource,
	 * sets the stage properties, and displays the stage.
	 * 
	 * @param primaryStage The primary stage is the main window of the JavaFX application. It
	 * represents the top-level container for all the JavaFX content.
	 */
	@Override
    public void start(Stage primaryStage)
    {
        try {
            URL resourceUrl = getClass().getResource("/BattleGUIApp.fxml");

            if (resourceUrl == null) {
                throw new RuntimeException("FXML resource not found.");
            }
            Parent root = FXMLLoader.load(Objects.requireNonNull(resourceUrl));
            Scene scene = new Scene(root, 500, 700);
            Image icon = new Image(new FileInputStream("./src/main/resources/images/one_ring.png"));
            primaryStage.getIcons().add(icon);
            primaryStage.setTitle("Battle For Middle Earth");
            primaryStage.setWidth(690);
            primaryStage.setHeight(850);
            primaryStage.setResizable(false);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
