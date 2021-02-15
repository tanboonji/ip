package com.tanboonji.duke;

import java.io.IOException;

import com.tanboonji.duke.ui.MainWindow;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * The Main class creates the Duke application and launches the JavaFX UI.
 */
public class Main extends Application {

    private static final String APPLICATION_NAME = "Duke";
    private final Image applicationImage = new Image(this.getClass().getResourceAsStream("/images/LeagueIcon.png"));
    private final Duke duke = new Duke();

    /**
     * Launches MainWindow scene and links the scene to Duke application.
     *
     * @param stage Root JavaFX stage.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.setTitle(APPLICATION_NAME);
            stage.setResizable(false);
            stage.getIcons().add(applicationImage);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {
        System.exit(0);
    }
}
