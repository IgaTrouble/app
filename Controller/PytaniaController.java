package app.Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;

import app.Database.DBConnector;
import app.Model.Pytania;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class PytaniaController {
	public DBConnector db;
	public ObservableList<Pytania> data;

    @FXML
    private TableView<Pytania> tvPytania;

    @FXML
    private TableColumn<Pytania, Integer> colid;

    @FXML
    private TableColumn<Pytania, String> colpytanie;

    @FXML
    private TableColumn<Pytania, String> colodp1;

    @FXML
    private TableColumn<Pytania, String> colodp2;

    @FXML
    private TableColumn<Pytania, String> colodp3;

    @FXML
    private TableColumn<Pytania, String> colodp4;

    @FXML
    private TableColumn<Pytania, String> colodp5;

    @FXML
    private TableColumn<Pytania, Integer> colnrodp;

    @FXML
    private Button btnZamknij;


    @FXML
    private TextField tfPytanie; // coś

    @FXML
    private TextField tfOdp1;

    @FXML
    private TextField tfOdp2;

    @FXML
    private TextField tfOdp3;

    @FXML
    private TextField tfOdp4;

    @FXML
    private Button btnAdd;

    @FXML
    private RadioButton rbOdp1;

    @FXML
    private ToggleGroup G1;

    @FXML
    private RadioButton rbOdp2;

    @FXML
    private RadioButton rbOdp3;

    @FXML
    private RadioButton rbOdp4;

    @FXML
    void actionDodaj(MouseEvent event) {

    }

    @FXML
    void actionZamknij(MouseEvent event) throws IOException {
    	Stage stage = new Stage();
    	Parent parent = (Parent) FXMLLoader.load(getClass().getResource("/app/View/LoginView.fxml"));
    	Scene scene = new Scene(parent);
    	stage.setScene(scene);
    	stage.setTitle("Logowanie");
    	stage.show();
    	((Node)(event.getSource())).getScene().getWindow().hide();
    }
    
    
    public void initialize() {
    	//btnZamknij.setText(LoginController.name + "\nDziekujemy za zalogowanie");
    	db = new DBConnector();
    	tvPytania.setEditable(true);
    }
    
    

    public void select() {
    Connection conn = db.Connection();
	data = FXCollections.observableArrayList();
	ResultSet rs = conn.createStatement().executeQuery("select * from pytania");
	while(rs.next()){
		data.add(new Pytania(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(6), rs.getInt(1)));
	}
	col_id.setCellValueFactory(new PropertyValueFactory<Users,Integer>("id"));
	col_name.setCellValueFactory(new PropertyValueFactory<Users,String>("name"));
	col_last.setCellValueFactory(new PropertyValueFactory<Users,String>("last"));
	col_salary.setCellValueFactory(new PropertyValueFactory<Users,Double>("salary"));
	col_position.setCellValueFactory(new PropertyValueFactory<Users,String>("position"));
	table_view.setItems(null);
	table_view.setItems(data);

}

    
    
    
}
