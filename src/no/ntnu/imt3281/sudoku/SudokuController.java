package no.ntnu.imt3281.sudoku;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.w3c.dom.Text;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;

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

    @FXML
    private Button lockcustom_btn;

    @FXML
    private ImageView winning_image;

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
        lockcustom_btn.setVisible(false);
        winning_image.setVisible(false);

        // load the winning image gif
        try{
            winning_image.setImage(new Image(new FileInputStream("resources/Images/tenor.gif")));
        } catch (FileNotFoundException ex){
            System.out.println("The winning gif was not found :( " + ex.getMessage());
        }

        //Get parent of parent of cell 0
        Parent parent = cell_0.getParent().getParent();

        //Fill the Array with references to the game.
        for (int i = 0; i < textFields.length; i++) {
            textFields[i] = (TextField) parent.lookup("#cell_" + i);
            textFields[i].setOnKeyTyped(keyEvent);
            lockCell(textFields[i]);

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
        game.resetGame();
        loadjson_btn.setVisible(true);
        manualgame_btn.setVisible(true);
        startgame_btn.setDisable(true);
        winning_image.setVisible(false);
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
        clearBoard();
        populateBoard();

        loadjson_btn.setVisible(false);
        manualgame_btn.setVisible(false);
        startgame_btn.setDisable(false);
        lockcustom_btn.setVisible(false);
    }

    /**
     * Button event handler for creating a manual game
     * @param event
     */
    @FXML
    void manualGamePressed(ActionEvent event) {
        clearBoard();

        loadjson_btn.setVisible(false);
        manualgame_btn.setVisible(false);
        startgame_btn.setDisable(false);
        lockcustom_btn.setVisible(true);
    }

    /**
     * Lock all cells that have digits inside of them
     * <p>
     *     Used for letting user create custom boards
     * </p>
     * @param event
     */
    @FXML
    void lockCustomBoard(ActionEvent event){
        int[] gameBoard = new int[81];
        lockcustom_btn.setVisible(false);
        for (int i = 0; i < textFields.length;i++) {
            // if cell has a digit inside it
            if (!textFields[i].getText().isEmpty()) {
                gameBoard[i] = Integer.parseInt(textFields[i].getText());
                lockCell(textFields[i]);
            } else {
                gameBoard[i] = -1;
            }

            // create the game board inside logic
            game.createNewBoard(gameBoard);
        }
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
                lockCell(textFields[i]);
            } else {
                textFields[i].setText("");
            }
        }
    }

    /**
     * clearBoard
     * <p>
     *     Clears the game board
     * </p>
     */
    void clearBoard(){
        for (int i = 0; i < textFields.length; i++) {
            textFields[i].setEditable(true);
            textFields[i].setStyle("-fx-control-inner-background: #ffffff;");
            textFields[i].setText("");
        }
    }

    /**
     * Lock the particular cell (textfield) and make grey so user
     * can't edit it anymore.
     * @param textfield The TextField to lock
     */
    void lockCell(TextField textfield){
        textfield.setEditable(false);
        textfield.setStyle("-fx-control-inner-background: #ababab;");
    }

    /**
     * Called whenever the user enters a character into a cell
     */
    @FXML
    EventHandler<KeyEvent> keyEvent = new EventHandler<KeyEvent>(){
        @Override
        public void handle(KeyEvent keyEvent) {
            TextField temp = (TextField) keyEvent.getSource();
            int textId = returnTextFieldId(temp);

            //Ignore tabs
            if(keyEvent.getCharacter().contains("\t")){
                return;
            }

            // check if character is numerical
            if (validInputCell(temp)) {
                int value = Integer.parseInt(temp.getText());

                try {
                    game.valueExists(value, textId);
                    game.insertNumber(value, textId);

                    // if finished sudoku game
                    if(game.isBoardCompleted()){
                        System.out.println("You won :)");
                        showWinningPrize();
                    }
                } catch(BadNumberException ex) {
                    temp.setStyle("-fx-control-inner-background: red;");
                    System.out.println(ex.getMessage());
                }
            } else {
                // prevents a bug where a character typed into a cell would make it invalid
                if(!game.isCellLocked(textId)) {
                    game.insertNumber(-1, textId);
                } else {
                    temp.setStyle("-fx-control-inner-background: #ababab;");
                }
            }
        }
    };

    /**
     * Lock cells, show a message and a special .gif to the user when he completes
     * the Sudoku puzzle.
     */
    void showWinningPrize(){
        winning_image.setVisible(true);
        // lock all cells
        game.lockCurrentCells();

        // lock all cells so user can't edit them anymore
        for (int i = 0; i < textFields.length;i++) {
            lockCell(textFields[i]);
        }

        //Send alert to user indicating that game is done.
        //Locale locale = new Locale("NO", "no");
        Locale locale = Locale.getDefault();
        ResourceBundle game = ResourceBundle.getBundle("i18n.Game", locale);
        sendAlert(game.getString("winning_title"), game.getString("winning_msg"), Alert.AlertType.INFORMATION);
    }

    /**
     * If the user entered a valid character (digits 1-9)
     * <p>
     *     If user writes an illegal character, the character will be removed from the cell
     * </p>
     * @param input the Textfield which we want to check user input
     * @return if user entered valid character or not
     */
    boolean validInputCell(TextField input) {
        input.setStyle("-fx-control-inner-background: #ffffff;");
        String inputString = input.getText().toLowerCase();
        int textId = returnTextFieldId(input);

        // if user has entered a character and cell is not locked
        if (inputString.length() > 0 && !game.isCellLocked(textId)) {
            char[] letter = inputString.toCharArray();

            // if cahracter is not numerical or nothing has been typed
            if (!Character.isDigit(letter[0]) || input.getText().isEmpty()) {
                input.setText("");
                return false;
            } else if (letter[0] <= '0') {
                input.setText("");
                return false;
            } else if (inputString.length() > 1) {
                input.setText("");
                return false;
            }
            return true;
        }

        return false;
    }

    /**
     * Returns the ID of a TextField
     * @param textfield The TextField to get the ID from
     * @return the numerical ID (position) of the textfield
     */
    int returnTextFieldId(TextField textfield) {
        String s = textfield.getId();
        String[] s2 = s.split("_");
        int ret = Integer.parseInt(s2[1]);
        return ret;
    }

    /**
     * Sends an alert to user if something went wrong
     * @param title The title of the alert
     * @param message The message to show to the user
     */
    void sendAlert(String title, String message, Alert.AlertType type){
        Alert alert = new Alert((type));
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        // show alert to user
        alert.showAndWait();
    }

}
