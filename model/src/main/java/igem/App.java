package igem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private Stage primarystage;

    @SuppressWarnings("exports")
    @Override
    public void start(Stage stage) throws IOException {
        this.primarystage = stage;
        Parent root = FXMLLoader.load(getClass().getResource("GUI_model_1.fxml"));
    
        Scene scene = new Scene(root, 600, 400);
        
        primarystage.setTitle("file_control");
        primarystage.setScene(scene);
        primarystage.getIcons().add(new Image(getClass().getResourceAsStream("1.jpg")));
        primarystage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}