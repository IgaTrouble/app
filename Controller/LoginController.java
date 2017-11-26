package app.Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import app.Database.DBConnector;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private AnchorPane AP;

    @FXML
    private TextField txt_log;

    @FXML
    private PasswordField pf_pass;

    @FXML
    private TextField tf_pas;

    @FXML
    private Button btn_log;

    @FXML
    private Button btn_show;
    static boolean flag = true;
    static String name;
    DBConnector db;
    
   public void initialize() {
	   db = new DBConnector();}
    	public static String typ;
 
    
    @FXML
    void buttonLogin(MouseEvent event) throws IOException, SQLException {
    	this.logowanie();
    	if(flagLog) {
    		((Node)(event.getSource())).getScene().getWindow().hide(); 
    	}
    }
    	
    	
    	
     boolean flagLog = false;
    void logowanie() throws SQLException, IOException {
    	Connection conn1 = db.Connection();
    	Statement stmt = conn1.createStatement();
    	ResultSet rs = stmt.executeQuery("select typ from loginy where email ='"+ txt_log.getText()+"' and pass='"+pf_pass.getText()+"'");
    	if(rs.next()) {
    		flagLog = true;
    		typ = rs.getString("typ");
    		System.out.println(typ);
    		System.out.println("rs");
    		Stage stage = new Stage();
    		if(typ.equals("EGZAMINATOR")) {
	        	Parent parent = (Parent) FXMLLoader.load(getClass().getResource("/app/View/PanelView.fxml"));
	        	Scene scene = new Scene(parent);
	        	stage.setScene(scene);
	        	stage.setTitle("Logowanie");
	        	stage.show();
	         }
    		else {
    			Parent parent = (Parent) FXMLLoader.load(getClass().getResource("/app/View/TestView.fxml"));
	        	Scene scene = new Scene(parent);
	        	stage.setScene(scene);
	        	stage.setTitle("Logowanie");
	        	stage.show();
	        
    		}
    	} else {
    		Alert e = new Alert(AlertType.ERROR);
        	e.setContentText("Błędny login lub hasło");
        	e.setHeaderText("błąd");
        	e.setTitle("Podaj poprawne");
        	e.showAndWait(); }
    
    }

    @FXML
    void showPass(MouseEvent event) {
    	if(flag == true) {
    		String pf_text = pf_pass.getText();
        	pf_pass.setVisible(false);
        	tf_pas.setVisible(true);
        	tf_pas.setText(pf_text);
    		flag = false;
    		btn_show.setText("Hide");
    	}	 else {
    		String pf_text = pf_pass.getText();
        	pf_pass.setVisible(true);
        	tf_pas.setVisible(false);
        	tf_pas.setText(pf_text);
    		flag = true;
    		btn_show.setText("Show");
    	}
    	
    }
    
    @FXML
    void enterLog(KeyEvent event) throws SQLException, IOException {
    	if(event.getCode() == KeyCode.ENTER)	{
    		this.logowanie();
    		if(flagLog) {
    		((Node)(event.getSource())).getScene().getWindow().hide(); 
    		}
    	}
    
    
    }
}

