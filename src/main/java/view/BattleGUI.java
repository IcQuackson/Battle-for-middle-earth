package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class BattleGUI extends Application
{

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("battleGUI.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 500, 700);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Image icon;

        try
        {
            icon = new Image(new FileInputStream("./src/one_ring.png"));
        } catch (FileNotFoundException e)
        {
            throw new RuntimeException(e);
        }
        primaryStage.getIcons().add(icon);
        primaryStage.setTitle("Battle For Middle Earth");
        primaryStage.setWidth(500);
        primaryStage.setHeight(600);
        primaryStage.setResizable(false);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
