package com.tokioschool.controller;

import com.tokioschool.model.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class BattleGUIController extends Application
{


    // Game State

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)
    {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/tokioschool/controller/BattleGUIApp.fxml")));
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
        /*
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/tokioschool/controller/BattleGUIApp.fxml"));
        Scene scene;
        try {
            scene = new Scene(fxmlLoader.load(), 500, 700);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Image icon;

        try
        {
            icon = new Image(new FileInputStream("./src/main/resources/images/one_ring.png"));
        } catch (FileNotFoundException e)
        {
            throw new RuntimeException(e);
        }
        */

    }



    /*
    void addHero(ActionEvent event) {

    }
    */
        /*
    private void addCharacter(String name, ComboBox<String> typeCmb, String health, String armor) {
        if (name != null && !name.isEmpty() &&
                typeCmb != null &&
                health != null && !health.isEmpty() &&
                armor != null && !armor.isEmpty()) {
            try {
                int healthValue = Integer.parseInt(health);
                int armorValue = Integer.parseInt(armor);

                GameUnit gameCharacter = CharacterFactory.getCharacter(CharacterType.get(typeCmb.getValue()));
            } catch (NumberFormatException e) {
                // Handle the case where health or armor is not a valid number
                // Display a warning message in the corresponding text field

                // Set the error message and color
                TextField healthTxt = getHealthTextField(); // Replace with your actual TextField variable
                TextField armorTxt = getArmorTextField(); // Replace with your actual TextField variable
                healthTxt.setText("Invalid input");
                armorTxt.setText("Invalid input");
                healthTxt.setStyle("-fx-text-fill: red;");
                armorTxt.setStyle("-fx-text-fill: red;");

                // Create a Timeline animation to revert the text color to the original after a delay
                Timeline timeline = new Timeline(
                        new KeyFrame(Duration.seconds(3), new KeyValue(healthTxt.styleProperty(), "")),
                        new KeyFrame(Duration.seconds(3), new KeyValue(armorTxt.styleProperty(), ""))
                );
                timeline.play();

                return; // Exit the method to prevent further processing
            }
        } else {
            // Display an error message or handle the case where one or more values are missing
        }
    } */
}
