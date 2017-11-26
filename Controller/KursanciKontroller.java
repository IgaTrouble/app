package app.Controller;

import java.io.IOException;

import app.Model.Loginy;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class KursanciKontroller {

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

    @FXML
    void actionAdd(MouseEvent event) {
    	System.out.println("Wejœcie w ADD");
    	if (czy_wypelnione()){
    		System.out.println("Zapis");
    	}

    }
    
    public boolean czy_wypelnione(){
    	System.out.println("Weryfikacja czy wype³nione wszystkie pola");
    	String err="Nie wype³ni³eœ wymaganych pól:";
    	boolean ok=true;
    	if (txEmail.getText().equals("")) { err+="\n* pole 'login/email'"; ok=false;}
    	if (txImie.getText().equals("")) {err+="\n* pole 'imiê'";ok=false;}
    	if (txNazwisko.getText().equals("")) {err+="\n* pole 'nazwisko'";ok=false;}
    	if (txGrupa.getText().equals("")) {err+="\n* pole 'grupa'";ok=false;}
    	if (pwdPass1.getText().equals("")) {err+="\n* pole 'has³o'";ok=false;}
    	if (pwdPass2.getText().equals("")) {err+="\n* pole 'powtórz has³o'";ok=false;}
    	if (!pwdPass1.getText().equals(pwdPass2.getText())) {err+="\n* "; ok=false;}
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

    }

    @FXML
    void actionUsun(MouseEvent event) {

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

}
