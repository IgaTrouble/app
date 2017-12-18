package app.Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import app.Database.DBConnector;
import app.Model.App;
import app.Model.Loginy;
import app.Model.Wyniki;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

public class PoTescieController {

	public ObservableList<Wyniki> data;
	
    @FXML
    private Label lblGratulacje;

    @FXML
    private Label lblWynik;

    @FXML
    private ProgressBar pbWynik;

    @FXML
    private TableView<Wyniki> tvWyniki;

    @FXML
    private TableColumn<Wyniki, String> cData;

    @FXML
    private TableColumn<Wyniki, Integer> cLiczba;

    @FXML
    private TableColumn<Wyniki, Integer> cWynik;

    @FXML
    private Button btnZamknij;

    @FXML
    void actionZamknij(MouseEvent event) throws IOException {   	
		btnZamknij.getScene().getWindow().hide();
    	Stage stage = new Stage();
    	Parent parent = (Parent) FXMLLoader.load(getClass().getResource("/app/View/ZakresView.fxml"));
    	Scene scene = new Scene(parent);
    	stage.setScene(scene);
    	stage.setResizable(false);
    	stage.setTitle("Zakres pytań");    
    	stage.show();	   
    }
    
        
    public void initialize() throws SQLException {
    	DBConnector db;
    	db = new DBConnector();
    	Connection conn = db.Connection();
    	data = FXCollections.observableArrayList();
    	String sql="select t.data_testu, count(o.ido), t.wynik, t.idt "
    			+ "from testy t "
    			+ "left join odpowiedzi o  on o.test=t.idt "
    			+ "where t.kursant='"+App.email+"' and t.wynik is not null " 
    			+ "group by t.idt "
    			+ "order by t.data_testu desc; ";
    	
    	System.out.println(sql);
    	ResultSet rs = conn.createStatement().executeQuery(sql);   	   	
    	
    	cData.setCellValueFactory(new PropertyValueFactory<Wyniki,String>("data"));
    	cData.setCellFactory(TextFieldTableCell.<Wyniki>forTableColumn());
    	cLiczba.setCellValueFactory(new PropertyValueFactory<Wyniki,Integer>("liczba"));
    	cLiczba.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
    	cWynik.setCellValueFactory(new PropertyValueFactory<Wyniki,Integer>("wynik"));
    	cWynik.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
    	
    	while(rs.next()){
    		data.add(new Wyniki(
    				rs.getString(1),
    				rs.getInt(2),
    				rs.getInt(3)));
    	}
    	tvWyniki.setItems(null);
    	tvWyniki.setItems(data);
    	conn.close();
    	
    	lblWynik.setText(""+App.wynik_testu+"%");
    	pbWynik.setProgress(App.wynik_testu/100.0);
    	if (App.wynik_testu>90) lblGratulacje.setText("Gratulacje !!!");
    	else if (App.wynik_testu>80) lblGratulacje.setText("Bardzo dobry wynik !!!");
    	else if (App.wynik_testu>70) lblGratulacje.setText("Dobry wynik !");
    	else if (App.wynik_testu>60) lblGratulacje.setText("Wynik całkiem przeciętny");
    	else if (App.wynik_testu>50) lblGratulacje.setText("Wynik taki sobie...");
    	else if (App.wynik_testu>40) lblGratulacje.setText("Trochę poniżej oczekiwań...");
    	else if (App.wynik_testu>25) lblGratulacje.setText("Poniżej oczekiwań...");
    	else lblGratulacje.setText("Słabiutko :-(");
   
    }

}
