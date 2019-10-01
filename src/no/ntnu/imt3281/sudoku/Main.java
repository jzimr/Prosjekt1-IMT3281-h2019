package no.ntnu.imt3281.sudoku;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class Main extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("sudoku.fxml"));
        scene = new Scene(root);
        stage.setTitle("Det beste sudoku ever!");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Sudoku game = new Sudoku();
        try{
           // game.createNewBoard();
            //controller.readFromJson(controller.readFromFile("resources/JSON/Board.json"));

            //System.out.println(Arrays.toString(controller.boardNums));
            //controller.changeNumbersRandom();
            //System.out.println(Arrays.toString(controller.boardNums));

            //String json = controller.readFromFile();
            //System.out.println(json);
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        launch();

    }

}