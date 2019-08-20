module no.ntnu.imt3281.sudoku {
    requires javafx.controls;
    requires javafx.fxml;

    opens no.ntnu.imt3281.sudoku to javafx.fxml;
    exports no.ntnu.imt3281.sudoku;
}
