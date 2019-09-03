module no.ntnu.imt3281.sudoku {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.logging;
    requires org.json;

    opens no.ntnu.imt3281.sudoku to javafx.fxml;
    exports no.ntnu.imt3281.sudoku;
}
