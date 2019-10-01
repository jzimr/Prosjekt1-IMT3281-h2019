package no.ntnu.imt3281.sudoku;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.TextField;

public class SudokuController {

    @FXML
    private TextField cell_0;

    @FXML
    private TextField[] textFields = new TextField[81];

    @FXML
    public void initialize() {
        System.out.println("second");

        Parent parent = cell_0.getParent().getParent();

        for (int i = 0; i < textFields.length; i++) {
            System.out.println(i);
            textFields[i] = (TextField) parent.lookup("#cell_" + i);
            textFields[i].setText("" + i);
        }
    }


}
