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
    public static void main(String[] args)
    {
        launch(args);
    }

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
