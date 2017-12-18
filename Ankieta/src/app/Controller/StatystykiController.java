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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class StatystykiController {
	public DBConnector db;
	private ObservableList<String> grupy;
	private ObservableList<String> osoby;
    @FXML
    private StackedBarChart<Double, String> sbc;
    @FXML
    private Button btn_show;

    @FXML
    protected ComboBox<String> cb_grupa;

    @FXML
	protected ComboBox<String> cb_osoba;
    
    @FXML
    private Button btn_zamknij;
    
    
    
 /*   @FXML
    void cbGroupAction(ActionEvent event) {
    	if (cb_grupa.getSelectionModel().selectedItemProperty().addListener(new ChangeListener())) {
    		try {
    			System.out.println("Wchodzę w akcję comboboxa");
				osoby = FXCollections.observableArrayList();
				Connection conn = db.Connection();
				ResultSet rs = conn.createStatement().executeQuery("select count(*), imie, nazwisko from loginy where grupa "+ cb_grupa.getValue()+ " group by grupa;");
				while(rs.next()){
					osoby.addAll(rs.getString(2) + " " + rs.getString(3));
					cb_osoba.setItems(osoby);
				}
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
    		}

    } */    
    
    

    @FXML
    void actionShowStat(MouseEvent event) throws IOException {
    	if (!(cb_osoba.getValue() == null)) {
    		cb_grupa.setValue(null);
    		System.out.println(cb_osoba.getValue());
    		App.kursant=cb_osoba.getValue();
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
    		Alert e = new Alert(AlertType.INFORMATION);
        	e.setContentText("Informacja");
        	e.setHeaderText("Nie wybrano grupy ani osoby");
        	e.setTitle("Informacja");
        	e.showAndWait(); 
    	}		
    }
    
    public void initialize() throws SQLException {
    	db = new DBConnector();
    	this.pobierz_grupy();
    	cb_grupa.setItems(grupy);
    	this.pobierz_osoby();
    	cb_osoba.setItems(osoby);
    	System.out.println("jestem tu");
    	this.sbc();
    	}
    
    @FXML
    private BarChart<?, ?> bs;
    
    @FXML
    private CategoryAxis x;

    @FXML
    private NumberAxis y;
    
    public void sbc() throws SQLException
    {
    	
    	/*XYChart.Series series1 = new XYChart.Series<>();
    	XYChart.Series series2 = new XYChart.Series<>();
    	XYChart.Series series3 = new XYChart.Series<>();
    	//series1.setName("Kursant1");
    	series1.getData().add(new XYChart.Data("Kursant 1", 50.55));
    	series1.getData().add(new XYChart.Data("Kursant 2", 20.55));
    	series1.getData().add(new XYChart.Data("Kursant 3", 30.55));
    	bs.getData().addAll(series1);
    	*/
    	DBConnector db = new DBConnector();
        Connection conn = db.Connection();
    	String sql="select concat(l.imie,' ',l.nazwisko), avg(t.wynik) from testy t inner join loginy l on l.email=t.kursant where t.wynik is not null group by concat(l.imie,' ',l.nazwisko);";
	 	
	 	ResultSet rs = conn.createStatement().executeQuery(sql);
        System.out.println(sql);
        XYChart.Series s = new XYChart.Series<>();
        while(rs.next()){
        	
        	s.getData().add(new XYChart.Data(rs.getString(1), rs.getDouble(2)));    	
        	
        	
        }   
        bs.getData().addAll(s);
	 	
	 	
	 	conn.close();
    	
    	
    	
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
