package app.Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import app.Database.DBConnector;
import app.Model.App;
import app.Model.Loginy;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class StatystykiController {
	public DBConnector db;
	private ObservableList grupy;
	private ObservableList osoby;
    @FXML
    private Button btn_show;

    @FXML
    protected ComboBox<String> cb_grupa;

    @FXML
	protected ComboBox<Loginy> cb_osoba;

    @FXML
    void actionShowStat(MouseEvent event) throws IOException {
    	if (!(cb_osoba.getValue() == null)) {
    		System.out.println(cb_osoba.getValue());
    	 	Stage stage = new Stage();
        	Parent parent = (Parent) FXMLLoader.load(getClass().getResource("/app/View/StatystykiKursantaView.fxml"));
        	Scene scene = new Scene(parent);
        	stage.setScene(scene);
        	stage.setResizable(false);
        	stage.setTitle("Statystyki");
        	stage.show();
        	((Node)(event.getSource())).getScene().getWindow().hide();}
    	else if (!(cb_grupa.getValue() == null)) {
    		App.grupa=cb_grupa.getValue();
    		Stage stage = new Stage();
        	Parent parent = (Parent) FXMLLoader.load(getClass().getResource("/app/View/StatystykiGrupyView.fxml"));
        	Scene scene = new Scene(parent);
        	stage.setScene(scene);
        	stage.setResizable(false);
        	stage.setTitle("Statystyki");
        	stage.show();
        	((Node)(event.getSource())).getScene().getWindow().hide();}
    	else {
    		Alert e = new Alert(AlertType.ERROR);
        	e.setContentText("Bląd");
        	e.setHeaderText("Błąd, nie wybrano grupy ani osoby");
        	e.setTitle("Błąd");
        	e.showAndWait(); 
    	}		
    }
    
    public void initialize() throws SQLException {
    	db = new DBConnector();
    	this.pobierz_grupy();
    	cb_grupa.setItems(grupy);
    	this.pobierz_osoby();
    	cb_osoba.setItems(osoby);
    }
    
    
	public  void pobierz_grupy()  {
		try {
			grupy = FXCollections.observableArrayList();
			Connection conn = db.Connection();
			ResultSet rs = conn.createStatement().executeQuery("select count(*), grupa from loginy where typ = \"KURSANT\" group by grupa;");
			while(rs.next()){
				grupy.addAll(rs.getString(2));
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
    
		public  void pobierz_osoby()  {
			try {
				osoby = FXCollections.observableArrayList();
				Connection conn = db.Connection();
				ResultSet rs = conn.createStatement().executeQuery("select count(*), imie, nazwisko from loginy where typ = \"KURSANT\" group by grupa;");
				while(rs.next()){
					osoby.addAll(rs.getString(2) + " " + rs.getString(3));
				}
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			    
    
    
	}
		
	   @FXML
	    private Button btn_zamknij;
	   
		@FXML
	    void actionZamknij(MouseEvent event) throws IOException {
			Stage stage = new Stage();
	    	Parent parent = (Parent) FXMLLoader.load(getClass().getResource("/app/View/PanelView.fxml"));
	    	Scene scene = new Scene(parent);
	    	stage.setScene(scene);
	    	stage.setResizable(false);
	    	stage.setTitle("Logowanie");
	    	stage.show();
	    	((Node)(event.getSource())).getScene().getWindow().hide();
	    }
}
