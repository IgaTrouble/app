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
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
	import javafx.stage.Stage;

	public class ZakresController {
		
		DBConnector db;
		int ilepytan=0;
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
	    
	    @FXML
	    private Button btn_wyniki;
	    
	    private ObservableList data;
	    
	    @FXML
	    private CheckBox cb_frontend;

	    @FXML
	    private CheckBox cb_spring;
	    
	    @FXML
	    private Slider sIle;

	    @FXML
	    private Label lblIle;
	    
	    @FXML
	    private Label lblMIN;

	    @FXML
	    private Label lblMAX;

	    @FXML
	    void actionStart(MouseEvent event) throws IOException {
	    	if (cb_sql.isSelected()||
		    	cb_git.isSelected() ||
		    	cb_python.isSelected() ||
		    	cb_frontend.isSelected() ||
		    	cb_spring.isSelected() ||
		    	cb_java.isSelected()) {
			    	App.liczba_pytan=(int)sIle.getValue();
			    	App.zakres_git=cb_git.isSelected();
			    	App.zakres_sql=cb_sql.isSelected();
			    	App.zakres_java=cb_java.isSelected();
			    	App.zakres_python=cb_python.isSelected();
			    	App.zakres_frontend=cb_frontend.isSelected();
			    	App.zakres_spring=cb_spring.isSelected();
			    	
			    	if (App.liczba_pytan>0) {
				     	Stage stage = new Stage();
				    	Parent parent = (Parent) FXMLLoader.load(getClass().getResource("/app/View/TestView.fxml"));
				    	Scene scene = new Scene(parent);
				    	stage.setScene(scene);
				    	stage.setTitle("Test");
				    	stage.show();
				    	((Node)(event.getSource())).getScene().getWindow().hide();
			    	} else {
			    		System.out.println("Określ liczbę pytań w teście");
				    	Alert e = new Alert(AlertType.CONFIRMATION);
			        	e.setContentText("Informacja");
			        	e.setHeaderText("Błąd, nie została wybrana liczba pytań");
			        	e.setTitle("Błąd");
			        	e.showAndWait();
			    	}
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
	    void actionWyniiki(MouseEvent event) throws IOException {
	    	System.out.println("Moje wyniki");
	    	Stage stage = new Stage();
	    	Parent parent = (Parent) FXMLLoader.load(getClass().getResource("/app/View/MojeWynikiView.fxml"));
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
	    	pieChart.setVisible(false);
	    	data = FXCollections.observableArrayList();
	        db = new DBConnector();
	        Connection conn = db.Connection();
	        String SQL = "select count(*), zakres from pytania group by zakres;";
	        ResultSet rs = conn.createStatement().executeQuery(SQL);
	        System.out.println(SQL);
	        while(rs.next()){
	                data.add(new PieChart.Data(rs.getString(2),rs.getDouble(1)));
	                if (rs.getString(2).toUpperCase().equals("FE")) App.ilefrontend=(int)rs.getDouble(1);
	                if (rs.getString(2).toUpperCase().equals("GIT")) App.ilegit=(int)rs.getDouble(1);
	                if (rs.getString(2).toUpperCase().equals("JAVA")) App.ilejava=(int)rs.getDouble(1);
	                if (rs.getString(2).toUpperCase().equals("PYTHON")) App.ilepython=(int)rs.getDouble(1);
	                if (rs.getString(2).toUpperCase().equals("SPRING")) App.ilespring=(int)rs.getDouble(1);
	                if (rs.getString(2).toUpperCase().equals("SQL")) App.ilesql=(int)rs.getDouble(1);	              
	        }
	        cb_sql.setText(cb_sql.getText()+" ("+App.ilesql+")");
	        cb_git.setText(cb_git.getText()+" ("+App.ilegit+")");
	        cb_python.setText(cb_python.getText()+" ("+App.ilepython+")");
	        cb_java.setText(cb_java.getText()+" ("+App.ilejava+")");
	        cb_frontend.setText(cb_frontend.getText()+" ("+App.ilefrontend+")");
	        cb_spring.setText(cb_spring.getText()+" ("+App.ilespring+")");
	        
	        this.showPieChart();
	        System.out.println("Czy udało się tu wejść?");
	         
	        policz();
	        
	    	}
	    
	    void policz(){
	    	ilepytan=0;
	    	if (cb_sql.isSelected()) ilepytan+=App.ilesql;	    	
	    	if (cb_git.isSelected()) ilepytan+=App.ilegit;
	    	if (cb_python.isSelected()) ilepytan+=App.ilepython;
	    	if (cb_frontend.isSelected()) ilepytan+=App.ilefrontend;
	    	if (cb_spring.isSelected()) ilepytan+=App.ilespring;
	    	if (cb_java.isSelected()) ilepytan+=App.ilejava; 
	    	sIle.setMax(ilepytan);
	    	
	    	if (ilepytan>0) 
	    		sIle.setMin(1);
	    	else
	    		sIle.setMin(0);
	    	
	    	lblMIN.setText(""+(int)sIle.getMin());
	    	lblMAX.setText(""+(int)sIle.getMax());
	    	
	    	if (sIle.getValue()>ilepytan)
	    			sIle.setValue(ilepytan);
	    	if ((ilepytan>0)&&(sIle.getValue()<1))
	    			sIle.setValue(1);
	    				
	    	lblIle.setText(""+(int)sIle.getValue());
	    }
	    
	    @FXML
	    void actionChange(MouseEvent event) {
	    	lblIle.setText(""+(int)sIle.getValue());
	    }

	    @FXML
	    void actionFrontend(MouseEvent event) {
	    	policz();
	    }

	    @FXML
	    void actionGit(MouseEvent event) {
	    	policz();
	    }

	    @FXML
	    void actionJava(MouseEvent event) {
	    	policz();
	    }

	    @FXML
	    void actionPython(MouseEvent event) {
	    	policz();
	    }

	    @FXML
	    void actionSQL(MouseEvent event) {
	    	policz();
	    }

	    @FXML
	    void actionSpring(MouseEvent event) {
	    	policz();
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
	    		cb_spring.setSelected(true);
	    		cb_frontend.setSelected(true);
	    	} else {
	    		cb_sql.setSelected(false);
	    		cb_git.setSelected(false);
	    		cb_python.setSelected(false);
	    		cb_java.setSelected(false); 
	    		cb_spring.setSelected(false);
	    		cb_frontend.setSelected(false);

	    	}
	    	policz();
 	

	    }
	}
