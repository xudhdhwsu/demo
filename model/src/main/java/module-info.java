module igem {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens igem to javafx.fxml;
    exports igem;
}
