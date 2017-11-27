package app.Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Optional;

import app.Database.DBConnector;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class KursanciKontroller {

	public DBConnector db;
	public ObservableList<Loginy> data;
	
    @FXML
    private TableView<Loginy> tvKursanci;

    @FXML
    private TableColumn<Loginy, String> colEmail;

    @FXML
    private TableColumn<Loginy, String> colImie;

    @FXML
    private TableColumn<Loginy, String> colNazwisko;

    @FXML
    private TableColumn<Loginy, String> colGrupa;
    
    @FXML
    private TableColumn<Loginy, String> coltyp;

    @FXML
    private Button btnZamknij;

    @FXML
    private Button btnPokazHaslo;

    @FXML
    private Button btnUsun;

    @FXML
    private TextField txEmail;

    @FXML
    private TextField txImie;

    @FXML
    private TextField txNazwisko;

    @FXML
    private TextField txGrupa;

    @FXML
    private PasswordField pwdPass1;

    @FXML
    private PasswordField pwdPass2;

    @FXML
    private Button btnDodaj;

    @FXML
    private TextField txPassword;
    
    @FXML
    private Button btnAddAdmin;

    
    private void wyczysc(){
    	txEmail.clear();
    	txImie.clear();
    	txNazwisko.clear();
    	txNazwisko.clear();
    	txGrupa.clear();
    	pwdPass1.clear();
    	pwdPass2.clear();
    }

    void add(String typ){
    	System.out.println("Wej�cie w ADD");
    	if (czy_wypelnione()){
    		System.out.println("Zapis");
    		Loginy l=new Loginy( txEmail.getText(),  txImie.getText(),  txNazwisko.getText(),
    				txGrupa.getText(),  pwdPass1.getText(), typ);
    		try {
				l.savedotb();
				select();
				wyczysc();
			} catch (SQLException e) {
				System.out.println("Nie uda�o si� zapisa�");
				e.printStackTrace();
			}
    	}
    }
    
    @FXML
    void actionAdd(MouseEvent event) {
    	add("KURSANT");
    }
    
    @FXML
    void actionAddAdmin(MouseEvent event) {
    	add("EGZAMINATOR");
    }
    
    public boolean czy_wypelnione(){
    	System.out.println("Weryfikacja czy wype�nione wszystkie pola");
    	String err="Nie wype�ni�e� wymaganych p�l:";
    	boolean ok=true;
    	if (txEmail.getText().equals("")) { err+="\n* pole 'login/email'"; ok=false;}
    	if (txImie.getText().equals("")) {err+="\n* pole 'imi�'";ok=false;}
    	if (txNazwisko.getText().equals("")) {err+="\n* pole 'nazwisko'";ok=false;}
    	if (txNazwisko.getText().equals("")) {err+="\n* pole 'grupa'";ok=false;}
    	if (pwdPass1.getText().equals("")) {err+="\n* pole 'has�o'";ok=false;}
    	if (pwdPass2.getText().equals("")) {err+="\n* pole 'powt�rz has�o'";ok=false;}
    	if (!pwdPass1.getText().equals(pwdPass2.getText())) {err+="\n* Has�a nie s� zgodne !"; ok=false;}
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
    void actionPokazHaslo(MouseEvent event) {
    	if (!Objects.isNull(tvKursanci.getSelectionModel().getSelectedItem())){
	    	String selLogin = tvKursanci.getSelectionModel().getSelectedItem().getPass();
	    	if (!Objects.isNull(selLogin)){
	    			//System.out.println(selLogin);
	    			txPassword.setText(selLogin);		
	    	}
    	} 
    }
    
    @FXML
    void actionTableClick(MouseEvent event) {
    	txPassword.setText("");	
    }

    @FXML
    void actionUsun(MouseEvent event) {
    	if (!Objects.isNull(tvKursanci.getSelectionModel().getSelectedItem())){
	    	String selLogin = tvKursanci.getSelectionModel().getSelectedItem().getEmail();
	    	if (!Objects.isNull(selLogin)){
	    		Alert a = new Alert(AlertType.CONFIRMATION);
	        	a.setHeaderText("Usuwanie");
	        	a.setContentText("Czy chcesz usun�� kursanta "+selLogin);
	        	a.setTitle("Potwierdzenie usuni�cia");
	        	ButtonType btTAK = new ButtonType("TAK, Usu�");
	        	ButtonType btNIE = new ButtonType("NIE");
	        	a.getButtonTypes().setAll(btTAK, btNIE);
	        	Optional<ButtonType> result = a.showAndWait();
	        	if (result.get() == btTAK) {
	        		try {
	        			System.out.println("Usuwanie...");
						tvKursanci.getSelectionModel().getSelectedItem().delete();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						System.out.println("B��d przy usuwaniu "+e.getMessage());
						e.printStackTrace();
					}
	        		select();
	        	};			
	    	}
    	} 
    }
    
    public boolean select() {
    	
    	try {
    		System.out.println("Try SELECT KURSANCI");
	    	Connection conn = db.Connection();
	    	data = FXCollections.observableArrayList();
	    	ResultSet rs = conn.createStatement().executeQuery("select email, imie, nazwisko, grupa,pass,typ from loginy ");
	    	
	    	while(rs.next()){
	    		data.add(new Loginy
	    				(rs.getString(1), 
	    				 rs.getString(2), 
	    				 rs.getString(3), 
	    				 rs.getString(4),
	    				 rs.getString(5),
	    				 rs.getString(6)
	    				 ));
	    	}
	    	conn.close();
    	} catch (SQLException e){
    		System.out.println("Kursanci wyst�pi� b��d +"+e.getMessage());
    		e.printStackTrace();
    	}
    	
    	
    	tvKursanci.setItems(null);
    	tvKursanci.setItems(data);
    	return true;
    }

    @FXML
    void actionZamknij(MouseEvent event) throws IOException {
    	
		System.out.println("Wracamy do Panelu");
		Stage stage = new Stage();
    	Parent parent = (Parent) FXMLLoader.load(getClass().getResource("/app/View/PanelView.fxml"));
    	Scene scene = new Scene(parent);
    	stage.setScene(scene);
    	stage.setTitle("Logowanie");
    	stage.show();
        System.out.println("Zamykamy okienko bie��ce");
    	((Node)(event.getSource())).getScene().getWindow().hide();
    }
    
    public void initialize() {
    	db = new DBConnector();
    	 	
    	colEmail.setCellValueFactory(new PropertyValueFactory<Loginy,String>("email"));
    	colImie.setCellValueFactory(new PropertyValueFactory<Loginy,String>("imie"));
    	colNazwisko.setCellValueFactory(new PropertyValueFactory<Loginy,String>("nazwisko"));
    	colGrupa.setCellValueFactory(new PropertyValueFactory<Loginy,String>("grupa"));
    	coltyp.setCellValueFactory(new PropertyValueFactory<Loginy,String>("typ"));
    	
    	colEmail.setCellFactory(TextFieldTableCell.<Loginy>forTableColumn());
    	colEmail.setOnEditCommit(
	            (CellEditEvent<Loginy, String> t) -> {
	                ((Loginy) t.getTableView().getItems().get(
	                        t.getTablePosition().getRow())
	                        ).setEmail(t.getNewValue());
	        });
    	colImie.setCellFactory(TextFieldTableCell.<Loginy>forTableColumn());
    	colImie.setOnEditCommit(
	            (CellEditEvent<Loginy, String> t) -> {
	                try {
						((Loginy) t.getTableView().getItems().get(
						        t.getTablePosition().getRow())
						        ).setImie(t.getNewValue());
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	        });
    	colNazwisko.setCellFactory(TextFieldTableCell.<Loginy>forTableColumn());
    	colNazwisko.setOnEditCommit(
	            (CellEditEvent<Loginy, String> t) -> {
	                try {
						((Loginy) t.getTableView().getItems().get(
						        t.getTablePosition().getRow())
						        ).setNazwisko(t.getNewValue());
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	        });
    	colGrupa.setCellFactory(TextFieldTableCell.<Loginy>forTableColumn());
    	colGrupa.setOnEditCommit(
	            (CellEditEvent<Loginy, String> t) -> {
	                try {
						((Loginy) t.getTableView().getItems().get(
						        t.getTablePosition().getRow())
						        ).setGrupa(t.getNewValue());
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	        });
    	select();
    }

}
