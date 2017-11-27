package app.Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Optional;

import app.Database.DBConnector;

import app.Model.Pytania;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class PytaniaController {
	public DBConnector db;
	public ObservableList<Pytania> data;
    ObservableList<String> zakres = FXCollections.observableArrayList("SQL", "Git", "Python", "Java");

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
    private ComboBox<String> cb_zakres;
    
    @FXML
    private Button btn_edit;

    @FXML
    private Button btn_delete;

    @FXML
    void actionDodaj(MouseEvent event) {
    	System.out.println("Wej�cie w ADD");
    	if (czy_wypelnione()){
    		System.out.println("Zapis");
    		System.out.println(cb_zakres.getValue());
    		System.out.println(G1.getSelectedToggle());
    		Integer odp =0;
    		if (((Node) G1.getSelectedToggle()).getId().equals("rbOdp1")) odp=1;
    		if (((Node) G1.getSelectedToggle()).getId().equals("rbOdp2")) odp=2;
    		if (((Node) G1.getSelectedToggle()).getId().equals("rbOdp3")) odp=3;
    		if (((Node) G1.getSelectedToggle()).getId().equals("rbOdp4")) odp=4;
    		System.out.println(odp);
    		Pytania l=new Pytania(0, cb_zakres.getValue(), tfPytanie.getText(),  tfOdp1.getText(),  tfOdp2.getText(),
    				 tfOdp3.getText(),  tfOdp4.getText(), odp);
    			
    		try {
				l.savedotb();
				select();
				// wyczysc();
			} catch (SQLException e) {
				System.out.println("Nie uda�o si� zapisa�");
				e.printStackTrace();
			}
    	}
    }
    
    
    public boolean czy_wypelnione(){
    	System.out.println("Weryfikacja czy wypełnione wszystkie pola");
    	String err="Nie wypełniłeś wymaganych pól:";
    	boolean ok=true;
    	if (tfPytanie.getText().equals("")) { err+="\n* pole 'pytanie'"; ok=false;}
    	if (tfOdp1.getText().equals("")) {err+="\n* pole 'imi�'";ok=false;}
    	if (tfOdp2.getText().equals("")) {err+="\n* pole 'nazwisko'";ok=false;}
    	if (tfOdp3.getText().equals("")) {err+="\n* pole 'grupa'";ok=false;}
    	if (tfOdp4.getText().equals("")) {err+="\n* pole 'has�o masło'";ok=false;}
    	if (G1.getSelectedToggle() == null) {err+="\n* pole 'random'";ok=false;}
    	if ((cb_zakres.getValue() == null) || (cb_zakres.equals(""))) {err+="\n* pole 'chceckbox'";ok=false;}	
    	if (ok){
    		System.out.println("Pola wype�nione");
    		return true;
    		
    	} else{
    		System.out.println("Pola niewype�nione");
    		Alert e = new Alert(AlertType.ERROR);
        	e.setContentText(err);
        	e.setHeaderText("B��d, nie wype�ni�e� p�l");
        	e.setTitle("B��d");
        	e.showAndWait(); 
    		return false;
    	}
    }
    
    
    
    

    @FXML
    void actionZamknij(MouseEvent event) throws IOException {
    	Stage stage = new Stage();
    	Parent parent = (Parent) FXMLLoader.load(getClass().getResource("/app/View/PanelView.fxml"));
    	Scene scene = new Scene(parent);
    	stage.setScene(scene);
    	stage.setTitle("Logowanie");
    	stage.show();
    	((Node)(event.getSource())).getScene().getWindow().hide();
    }
    
    
    public void initialize() throws SQLException {
    	db = new DBConnector();
    	this.select();
    	tvPytania.setEditable(true);
    	cb_zakres.setItems(zakres);
    }
    
    


    public void select() throws SQLException {

	    Connection conn = db.Connection();
		data = FXCollections.observableArrayList();
		ResultSet rs = conn.createStatement().executeQuery("select * from pytania");
		while(rs.next()){
			data.add(new Pytania(rs.getInt(1), rs.getString(3), rs.getString(2),
					rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8)));
		}


		colid.setCellValueFactory(new PropertyValueFactory<Pytania,Integer>("idp"));
		colpytanie.setCellValueFactory(new PropertyValueFactory<Pytania,String>("zakres"));
		colodp1.setCellValueFactory(new PropertyValueFactory<Pytania,String>("pytanie"));
		colodp2.setCellValueFactory(new PropertyValueFactory<Pytania,String>("odp1"));
		colodp3.setCellValueFactory(new PropertyValueFactory<Pytania,String>("odp2"));
		colodp4.setCellValueFactory(new PropertyValueFactory<Pytania,String>("odp3"));
		colodp5.setCellValueFactory(new PropertyValueFactory<Pytania,String>("odp4"));
		colnrodp.setCellValueFactory(new PropertyValueFactory<Pytania,Integer>("odppopr"));
		tvPytania.setItems(null);
		tvPytania.setItems(data);

    }

    @FXML
    void deleteAction(MouseEvent event) throws SQLException {
     	if (!Objects.isNull(tvPytania.getSelectionModel().getSelectedItem())){
	    	Integer idpyt = tvPytania.getSelectionModel().getSelectedItem().getIdp();
	    	if (!Objects.isNull(idpyt)){
	    		Alert a = new Alert(AlertType.CONFIRMATION);
	        	a.setHeaderText("Usuwanie");
	        	a.setContentText("Czy chcesz usunąć pytanie nr: "+idpyt);
	        	a.setTitle("Potwierdzenie usunięcia");
	        	ButtonType btTAK = new ButtonType("TAK, Usuń");
	        	ButtonType btNIE = new ButtonType("NIE");
	        	a.getButtonTypes().setAll(btTAK, btNIE);
	        	Optional<ButtonType> result = a.showAndWait();
	        	if (result.get() == btTAK) {
	        		try {
	        			System.out.println("Usuwanie...");
						tvPytania.getSelectionModel().getSelectedItem().delete();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						System.out.println("Błą przy usuwaniu "+e.getMessage());
						e.printStackTrace();
					}
	        		select();
	        	};			
	    	}
    	} 
    

    }

    @FXML
    void editAction(MouseEvent event) {

    }
    

}
