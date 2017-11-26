package app.Controller;


import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class PanelController {
	

 
    @FXML
    private MenuItem m_send;

    @FXML
    private MenuItem m_send_custom;

    @FXML
    private MenuItem m_clear;

    @FXML
    private MenuItem m_exit;

    @FXML
    private MenuItem m_sendDB;

    @FXML
    private MenuItem m_help;

 



    @FXML
    void clearAction(ActionEvent event) {

    }

    @FXML
    void exitAction(ActionEvent event) {

    }


    @FXML
    void sendAction(ActionEvent event) {

    }

    @FXML
    void sendCustomAction(ActionEvent event) {

    }

    @FXML
    void sendDBAction(ActionEvent event) {

    }


	

    @FXML
    private MenuBar mb_panel;

    @FXML
    private Button btn_pyt;

    @FXML
    private Button btn_kurs;

    @FXML
    void KursAction(MouseEvent event) throws IOException {
    	Stage stage = new Stage();
    	Parent parent = (Parent) FXMLLoader.load(getClass().getResource("/app/View/KursanciView.fxml"));
		Scene scene = new Scene(parent);
		stage.setScene(scene);
		stage.setTitle("Kursanci");
		stage.show();
		((Node)(event.getSource())).getScene().getWindow().hide();

    }

    @FXML
    void pytAction(MouseEvent event) throws IOException {
    	Stage stage = new Stage();
    	Parent parent = (Parent) FXMLLoader.load(getClass().getResource("/app/View/PytaniaView.fxml"));
		Scene scene = new Scene(parent);
		stage.setScene(scene);
		stage.setTitle("Pytania");
		stage.show();
		((Node)(event.getSource())).getScene().getWindow().hide();
    	

    }

}
