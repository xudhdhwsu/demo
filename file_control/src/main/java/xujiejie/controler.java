package xujiejie;

import java.io.File;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class controler {

    @FXML
    private Button Open_File;

    @FXML
    private Button Save_File;

    @FXML
    private TextArea textarea;

    @FXML
    private TextField textfield;

    @FXML
    void Open_File(MouseEvent event) throws IOException {
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new ExtensionFilter("Text Files", "*.txt"));
        File selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile != null) {new File_xujiejie(selectedFile.getAbsolutePath());}
        textarea.setText(File_xujiejie.content.toString());
    }

    @FXML
    void Research(MouseEvent event) throws IOException {
        the_same_event();
    }

    @FXML
    void enter(KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.ENTER && textfield.isFocused()) {
            the_same_event();
        }
    }

    private void the_same_event() throws IOException {
        if(textfield.getText().length()>0){
            FileTool fileTool = new FileTool();
            textarea.setText(fileTool.research(textfield.getText()));
        }
    }
    
}






    /* @FXML
    private void Open_File(ActionEvent event) {
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();

        // 创建一个FileChooser实例
        FileChooser fileChooser = new FileChooser();

        // 设置文件类型过滤器
        fileChooser.getExtensionFilters().add(new ExtensionFilter("Text Files", "*.txt"));

        // 打开文件选择对话框
        File selectedFile = fileChooser.showOpenDialog(stage);

        if (selectedFile != null) {
            try {
                new File_xujiejie(selectedFile.getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void Sava_File(ActionEvent event) {
 */
    
/*  
    @FXML
    void enter(KeyEvent event) throws IOException {
        // if (event.getCode() == KeyCode.ENTER) {research();}
    }

    @FXML
    void research(ActionEvent event) throws IOException {
        // FileTool fileTool = new FileTool();
        // textarea.setText(fileTool.research(textfield.getText()));
*/


