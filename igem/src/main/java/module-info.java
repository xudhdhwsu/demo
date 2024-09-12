module model {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.base;
    requires jfreechart;
    requires jcommon;
    requires java.desktop;
    

    opens model to javafx.fxml;
    exports model;
}
