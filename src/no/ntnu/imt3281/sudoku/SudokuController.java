package no.ntnu.imt3281.sudoku;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import org.w3c.dom.Text;

public class SudokuController {

    @FXML
    private TextField cell_0;

    @FXML
    private TextField[] textFields = new TextField[81];

    @FXML
    public void initialize() {
        System.out.println("second");



    }

    public void loadAfter(Scene scene){

        if(scene != null) {
            for (int i = 0; i < textFields.length; i++){
                textFields[i] = (TextField) scene.lookup("cell_" + i);
            }

            textFields[0].setText("tets");
        }

        cell_0.setText("Test");

    }





}
