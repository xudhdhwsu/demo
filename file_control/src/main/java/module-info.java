module xujiejie {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.poi.ooxml;
    requires javafx.graphics;
    requires javafx.base;
    

    opens xujiejie to javafx.fxml;
    exports xujiejie;
}
