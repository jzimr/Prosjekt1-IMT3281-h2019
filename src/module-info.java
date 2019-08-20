module no.ntnu.imt3281.openjfx {
    requires javafx.controls;
    requires javafx.fxml;

    opens no.ntnu.imt3281.openjfx to javafx.fxml;
    exports no.ntnu.imt3281.openjfx;
}
