package igem;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GUI_controller_1 {

    
    @FXML
    private Button Cancel;

    @FXML
    private TextField Competition_coefficient_1;

    @FXML
    private TextField Competition_coefficient_2;

    @FXML
    private TextField Competition_coefficient_3;

    @FXML
    private TextField Competition_coefficient_4;

    @FXML
    private TextField Competition_coefficient_5;

    @FXML
    private TextField Competition_coefficient_6;

    @FXML
    private ImageView image_view;

     @FXML
    private Label label_1;

    @FXML
    private Label label_2;

    @FXML
    private Label label_3;

    @FXML
    private Label label_4;

    @FXML
    private Label label_5;

    @FXML
    private Label label_6;

    private List<TextField> list = new ArrayList<TextField>();

    private List<Label> labellist = new ArrayList<>();

    private int markedlength;

    @FXML
    void Cancel(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    void Next_step(MouseEvent event) throws IOException {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getText());
        }
        if(allFieldsFilled(markedlength)) {
            Stage stage = (Stage) Cancel.getScene().getWindow();

        // 加载另一个 FXML 文件
        Parent newSceneRoot = FXMLLoader.load(getClass().getResource("GUI_model_2.fxml"));

        // 设置新的场景
        Scene newScene = new Scene(newSceneRoot);
        stage.setScene(newScene);
        }
        else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("warning");
            alert.setHeaderText(null);
            alert.setContentText("Please fill in all the fields!");
            alert.showAndWait();
        }
    }

    private String[] ThreeArray = new String[]{"B-->A", "C-->A", "A-->B", "C-->B", "A-->C", "B-->C"};

    @FXML
    void Three_bacteria(MouseEvent event) {
        hide();
        display(6);
        markedlength = 6;
        for (int i = 0; i < ThreeArray.length; i++) {
            labellist.get(i).setText(ThreeArray[i]);
            labellist.get(i).setVisible(true);
        }
        display_image(new Image(getClass().getResourceAsStream("1.jpg")));
    }

    private String[] TwoArray = new String[]{"B-->A", "A-->B"};
    @FXML
    void Two_bacteria(MouseEvent event) {
        hide();
        display(2);
        markedlength = 2;
        for (int i = 0; i < TwoArray.length; i++) {
            labellist.get(i).setText(TwoArray[i]);
            labellist.get(i).setVisible(true);
        }
        display_image(new Image(getClass().getResourceAsStream("1.jpg")));
    }

    @FXML
    private void initialize() {

        list.add(Competition_coefficient_1);
        list.add(Competition_coefficient_2);
        list.add(Competition_coefficient_3);
        list.add(Competition_coefficient_4);
        list.add(Competition_coefficient_5);
        list.add(Competition_coefficient_6);
        
        labellist.add(label_1);
        labellist.add(label_2);
        labellist.add(label_3);
        labellist.add(label_4);
        labellist.add(label_5);
        labellist.add(label_6);

        for (int i = 0; i < list.size(); i++) {
            setTextFieldToNumericWithDecimal(list.get(i));
        }

    }

    private void hide(){
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setVisible(false);
            labellist.get(i).setVisible(false);
        }
    }

    private void display(int length){
        for (int i = 0; i < length; i++) {
            list.get(i).setVisible(true);
        }
    }

    private void display_image(Image image){
        image_view.setImage(image);
    }

    private void setTextFieldToNumericWithDecimal(TextField textField) {
        TextFormatter<Double> formatter = new TextFormatter<>(new DoubleStringConverter(), 0.0, change -> {
            String newText = change.getControlNewText();
            // 允许空字符串或是有效的数字
            if (newText.isEmpty() || newText.matches("\\d*\\.?\\d*")) {
                return change;
            }
            return null;
        });
    textField.setTextFormatter(formatter);
    textField.setText("");
    }

    private boolean allFieldsFilled(int length) {
        boolean allFieldsFilled = true;
        for (int i = 0; i < length; i++) {
            if (list.get(i).getText().trim().isEmpty()) {
                allFieldsFilled = false;
                break;
            }
        }
        return allFieldsFilled;
    }

}
