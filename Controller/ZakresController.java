package app.Controller;


	import java.io.IOException;
import java.sql.SQLException;

import app.Database.DBConnector;
import javafx.fxml.FXML;
	import javafx.fxml.FXMLLoader;
	import javafx.scene.Node;
	import javafx.scene.Parent;
	import javafx.scene.Scene;
	import javafx.scene.control.Button;
	import javafx.scene.control.CheckBox;
	import javafx.scene.input.MouseEvent;
	import javafx.stage.Stage;

	public class ZakresController {
		
		DBConnector db;

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
	    	Parent parent = (Parent) FXMLLoader.load(getClass().getResource("/app/View/TestView.fxml"));
	    	Scene scene = new Scene(parent);
	    	stage.setScene(scene);
	    	stage.setTitle("Test");
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
	    
	    
	    public void initialize() throws SQLException {
	    	db = new DBConnector();
	    
	    }
	    
	
	    
	    @FXML
	    void selectAll(MouseEvent event) {
	    	if(cb_all.isSelected()) {
	    		cb_sql.setSelected(true);
	    		cb_git.setSelected(true);
	    		cb_python.setSelected(true);
	    		cb_java.setSelected(true);
	    		cb_java.setSelected(true);
	    	} else {
	    		cb_sql.setSelected(false);
	    		cb_git.setSelected(false);
	    		cb_python.setSelected(false);
	    		cb_java.setSelected(false); 

	    	}

	    }
	}
