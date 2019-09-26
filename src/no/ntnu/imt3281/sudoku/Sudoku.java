package no.ntnu.imt3281.sudoku;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class Sudoku extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("sudoku.fxml"));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        SudokuController controller = new SudokuController();

        try{
            controller.readFromJson(controller.readFromFile());


            //String json = controller.readFromFile();
            //System.out.println(json);
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        launch();

    }

}