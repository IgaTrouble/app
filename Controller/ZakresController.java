package app.Controller;


	import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import app.Database.DBConnector;
import app.Model.App;
import app.Model.Pytania;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
	import javafx.fxml.FXMLLoader;
	import javafx.scene.Node;
	import javafx.scene.Parent;
	import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
	import javafx.scene.control.CheckBox;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
	import javafx.stage.Stage;

	public class ZakresController {
		
		DBConnector db;
		public ObservableList<Pytania> liczba;
//		private int lSql;
		
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
	    private PieChart pieChart;
	    
	    private ObservableList data;

	    @FXML
	    void actionStart(MouseEvent event) throws IOException {
	    	if (cb_sql.isSelected()||
		    	cb_git.isSelected() ||
		    	cb_python.isSelected() ||
		    	cb_java.isSelected()) {
			    	App.liczba_pytan=10;
			    	App.zakres_git=cb_git.isSelected();
			    	App.zakres_sql=cb_sql.isSelected();
			    	App.zakres_java=cb_java.isSelected();
			    	App.zakres_python=cb_python.isSelected();
			    	
			     	Stage stage = new Stage();
			    	Parent parent = (Parent) FXMLLoader.load(getClass().getResource("/app/View/TestView.fxml"));
			    	Scene scene = new Scene(parent);
			    	stage.setScene(scene);
			    	stage.setTitle("Test");
			    	stage.show();
			    	((Node)(event.getSource())).getScene().getWindow().hide();
		    } else {
		    	System.out.println("nie został wybrany zakres pytań");
		    	Alert e = new Alert(AlertType.ERROR);
	        	e.setContentText("Błąd");
	        	e.setHeaderText("Błąd, nie został wybrany żaden zakres pytań");
	        	e.setTitle("Błąd");
	        	e.showAndWait();
		    }
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
	    	pieChart.setVisible(false);
	    	data = FXCollections.observableArrayList();
	        db = new DBConnector();
	        Connection conn = db.Connection();
	        String SQL = "select count(*), zakres from pytania group by zakres;";
	        ResultSet rs = conn.createStatement().executeQuery(SQL);
	        System.out.println(SQL);
	        while(rs.next()){
	                data.add(new PieChart.Data(rs.getString(2),rs.getDouble(1)));
	               
	        }
	        this.showPieChart();
	        System.out.println("Czy udało się tu wejść?");
	         
	        
	    	}

	    
	    public void showPieChart() throws SQLException {
	    	System.out.println("showciasto");
	 	 //  PieChart pieChart = new PieChart();
	 	   pieChart.getData().addAll(data);
	 	   pieChart.setVisible(true);
	 	 
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
