package app.Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

import app.Database.DBConnector;
import app.Model.Loginy;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
    
    private void wyczysc(){
    	txEmail.clear();
    	txImie.clear();
    	txNazwisko.clear();
    	txNazwisko.clear();
    	pwdPass1.clear();
    	pwdPass2.clear();
    }

    @FXML
    void actionAdd(MouseEvent event) {
    	System.out.println("Wejœcie w ADD");
    	if (czy_wypelnione()){
    		System.out.println("Zapis");
    		Loginy l=new Loginy( txEmail.getText(),  txImie.getText(),  txNazwisko.getText(),
    				txGrupa.getText(),  pwdPass1.getText(),  "KURSANT");
    		try {
				l.savedotb();
				select();
				wyczysc();
			} catch (SQLException e) {
				System.out.println("Nie uda³o siê zapisaæ");
				e.printStackTrace();
			}
    	}
    }
    
    public boolean czy_wypelnione(){
    	System.out.println("Weryfikacja czy wype³nione wszystkie pola");
    	String err="Nie wype³ni³eœ wymaganych pól:";
    	boolean ok=true;
    	if (txEmail.getText().equals("")) { err+="\n* pole 'login/email'"; ok=false;}
    	if (txImie.getText().equals("")) {err+="\n* pole 'imiê'";ok=false;}
    	if (txNazwisko.getText().equals("")) {err+="\n* pole 'nazwisko'";ok=false;}
    	if (txNazwisko.getText().equals("")) {err+="\n* pole 'grupa'";ok=false;}
    	if (pwdPass1.getText().equals("")) {err+="\n* pole 'has³o'";ok=false;}
    	if (pwdPass2.getText().equals("")) {err+="\n* pole 'powtórz has³o'";ok=false;}
    	if (!pwdPass1.getText().equals(pwdPass2.getText())) {err+="\n* Has³a nie s¹ zgodne !"; ok=false;}
    	if (ok){
    		System.out.println("Pola wype³nione");
    		return true;
    		
    	} else{
    		System.out.println("Pola niewype³nione");
    		Alert e = new Alert(AlertType.ERROR);
        	e.setContentText(err);
        	e.setHeaderText("B³¹d, nie wype³ni³eœ pól");
        	e.setTitle("B³¹d");
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

    }
    
    public boolean select() {
    	
    	try {
    		System.out.println("Try SELECT KURSANCI");
	    	Connection conn = db.Connection();
	    	data = FXCollections.observableArrayList();
	    	ResultSet rs = conn.createStatement().executeQuery("select email, imie, nazwisko, grupa,pass,typ from loginy where typ='KURSANT'");
	    	
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
    		System.out.println("Kursanci wyst¹pi³ b³¹d +"+e.getMessage());
    		e.printStackTrace();
    	}
    	colEmail.setCellValueFactory(new PropertyValueFactory<Loginy,String>("email"));
    	colImie.setCellValueFactory(new PropertyValueFactory<Loginy,String>("imie"));
    	colNazwisko.setCellValueFactory(new PropertyValueFactory<Loginy,String>("nazwisko"));
    	colGrupa.setCellValueFactory(new PropertyValueFactory<Loginy,String>("grupa"));
    	
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
        System.out.println("Zamykamy okienko bie¿¹ce");
    	((Node)(event.getSource())).getScene().getWindow().hide();
    }
    
    public void initialize() {
    	//btnZamknij.setText(LoginController.name + "\nDziekujemy za zalogowanie");
    	db = new DBConnector();
    	select();
    }

}
