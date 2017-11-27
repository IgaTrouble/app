package app.Controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class TestController {

    @FXML
    private CheckBox cb_sql;

    @FXML
    private CheckBox cb_git;

    @FXML
    private CheckBox cb_python;

    @FXML
    private CheckBox cb_java;

    @FXML
    private CheckBox cb_all;

    @FXML
    private Button btn_log;

    @FXML
    private Button btn_start;

    @FXML
    void actionStart(MouseEvent event) throws IOException {
     	Stage stage = new Stage();
    	Parent parent = (Parent) FXMLLoader.load(getClass().getResource("/app/View/LoginView.fxml"));
    	Scene scene = new Scene(parent);
    	stage.setScene(scene);
    	stage.setTitle("Logowanie");
    	stage.show();
    	((Node)(event.getSource())).getScene().getWindow().hide();
    }

    
     
    @FXML
    void actionLog(MouseEvent event) throws IOException {
    	Stage stage = new Stage();
    	Parent parent = (Parent) FXMLLoader.load(getClass().getResource("/app/View/LoginView.fxml"));
    	Scene scene = new Scene(parent);
    	stage.setScene(scene);
    	stage.setTitle("Logowanie");
    	stage.show();
    	((Node)(event.getSource())).getScene().getWindow().hide();
    }

}
