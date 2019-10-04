package no.ntnu.imt3281.sudoku;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * JavaFX App
 */
public class Main extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        Locale locale = new Locale("NO", "no");
        //Locale locale = Locale.getDefault();
        ResourceBundle bundle = ResourceBundle.getBundle("i18n.Game", locale);
        Parent root = FXMLLoader.load(getClass().getResource("sudoku.fxml"), bundle);
        scene = new Scene(root);
        stage.setTitle(bundle.getString("title"));
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}