package no.ntnu.imt3281.sudoku;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class SudokuController {

    private Sudoku game = new Sudoku();

    @FXML
    private TextField cell_0;

    @FXML
    private TextField[] textFields = new TextField[81];

    @FXML
    private Button startgame_btn;

    @FXML
    private Button loadjson_btn;

    @FXML
    private Button manualgame_btn;

    /**
     * initialize
     * <p>
     *     Initializes the GUI.
     * </p>
     */
    @FXML
    public void initialize() {

        loadjson_btn.setVisible(false);
        manualgame_btn.setVisible(false);

        //Get parent of parent of cell 0
        Parent parent = cell_0.getParent().getParent();

        //Fill in array with references to the game.
        for (int i = 0; i < textFields.length; i++) {
            textFields[i] = (TextField) parent.lookup("#cell_" + i);
        }
    }

    /**
     * startGamePressed
     * <p>
     *      Presents the two available choices to the user.
     * </p>
     * @param event
     */
    @FXML
    void startGamePressed(ActionEvent event) {
        loadjson_btn.setVisible(true);
        manualgame_btn.setVisible(true);
    }

    /**
     * loadFromJsonPressed
     * <p>
     *     Loads the board into the GUI from the JSON file.
     *     Logic is handled by Sudoku.createNewBoard();
     * </p>
     * @param event
     */
    @FXML
    void loadFromJsonPressed(ActionEvent event) {
        game.createNewBoard("resources/JSON/Board.json");

        /*Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Look, an Information Dialog");
        alert.setContentText("I have a great message for you!");

        alert.showAndWait();*/

        populateBoard();

        loadjson_btn.setVisible(false);
        manualgame_btn.setVisible(false);
    }

    @FXML
    void manualGamePressed(ActionEvent event) {
        loadjson_btn.setVisible(false);
        manualgame_btn.setVisible(false);
    }
    /**
     * populateBoard
     * <p>
     *     Fills the board present in the GUI with values from the
     *     logic handled by Sudoku class.
     * </p>
     */
    void populateBoard(){
        for (int i = 0; i < textFields.length; i++) {
            if(game.boardNums[i] != -1) {
                textFields[i].setText(String.valueOf(game.boardNums[i]));
            } else {
                textFields[i].setText("");
            }
        }
    }
}
