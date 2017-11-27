package app.Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.Random;

import app.Database.DBConnector;
import app.Model.App;
import app.Model.Odpowiedzi;
import app.Model.Pytania;
import app.Model.Testy;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class TestController {

	private int wybor;
	private int pytanie;
	private int numertestu;
	private int current_question=0;
	private int ilepopr=0;
	private ArrayList<Pytania> pytania;
	private HashMap<Pytania,Integer> odpowiedzi;
	private HashMap<Pytania,String> zakresy;
	DBConnector db;
	Testy T;
	
    @FXML
    private ProgressBar pbProgress;

    @FXML
    private Label lblPytanie;

    @FXML
    private RadioButton rbOdp1;

    @FXML
    private ToggleGroup G2;

    @FXML
    private RadioButton rbOdp2;

    @FXML
    private RadioButton rbOdp3;

    @FXML
    private RadioButton rbOdp4;

    @FXML
    private Button btnNext;

    @FXML
    private Button btnPrzerwij;

    @FXML
    private Label lblKursant;

    @FXML
    private Label lblIleTestow;

    @FXML
    private Label lblSrednia;

    @FXML
    private Label lblZakresPytania;

    @FXML
    private Label lblPozostaloPytan;

    @FXML
    private Label lblNumerPytania;

    @FXML
    private Button btnKoniec;

    @FXML
    private Button btnSprawdz;
    
    @FXML
    private ImageView im1ok;

    @FXML
    private ImageView im2ok;

    @FXML
    private ImageView im3ok;

    @FXML
    private ImageView im4ok;

    @FXML
    private ImageView im1nieok;

    @FXML
    private ImageView im2nieok;

    @FXML
    private ImageView im3nieok;

    @FXML
    private ImageView im4nieok;
    
  

    @FXML
    void actionBreak(MouseEvent event) throws IOException {
    	System.out.println("Przerwij");
    	Alert a = new Alert(AlertType.CONFIRMATION);
    	a.setHeaderText("Przerwanie testu");
    	a.setContentText("Czy przerwaæ test ?\n Twoje dotychczasowe odpowiedzi zosta³y zapisane, ale nie wlicz¹ siê do œredniej.");
    	a.setTitle("Potwierdzenie przerwania testu");
    	ButtonType btTAK = new ButtonType("TAK, przerwij½");
    	ButtonType btNIE = new ButtonType("NIE");
    	a.getButtonTypes().setAll(btTAK, btNIE);
    	Optional<ButtonType> result = a.showAndWait();
    	if (result.get() == btTAK) {
    		zamknij();
    	}
    }

    @FXML
    void actionCheck(MouseEvent event) {
    	System.out.println("Sprawdz");
    	rbOdp1.setDisable(true);
    	rbOdp2.setDisable(true);
    	rbOdp3.setDisable(true);
    	rbOdp4.setDisable(true);
    	btnNext.setDisable(false);
    	btnKoniec.setDisable(false);
    	btnSprawdz.setDisable(true);
    	int wybor=0;
    	if (rbOdp1.isSelected()) wybor=1;
    	if (rbOdp2.isSelected()) wybor=2;
    	if (rbOdp3.isSelected()) wybor=3;
    	if (rbOdp4.isSelected()) wybor=4;
    	if (pytania.get(current_question).getOdppopr()!=wybor){
    		switch (wybor){
    		case 1:im1nieok.setVisible(true);break;
    		case 2:im2nieok.setVisible(true);break;
    		case 3:im3nieok.setVisible(true);break;
    		case 4:im4nieok.setVisible(true);break;
    		}
    		ilepopr++;
    	}
    	switch (pytania.get(current_question).getOdppopr()){
    	case 1:im1ok.setVisible(true);break;
		case 2:im2ok.setVisible(true);break;
		case 3:im3ok.setVisible(true);break;
		case 4:im4ok.setVisible(true);break;
    	}
    	
    	Odpowiedzi o = new Odpowiedzi(numertestu,current_question,wybor);
    	o.save();
    }

    @FXML
    void actionKoniec(MouseEvent event) {
    	System.out.println("Koniec");
    	Testy.update_wynik(numertestu,(int)(100.0*(double)ilepopr/(double)pytanie));
    	zamknij();
    }

    @FXML
    void actionNext(MouseEvent event) throws IOException {
    	System.out.println("Next");
    	im1nieok.setVisible(false);
    	im2nieok.setVisible(false);
    	im3nieok.setVisible(false);
    	im4nieok.setVisible(false);
    	im1ok.setVisible(false);
    	im2ok.setVisible(false);
    	im3ok.setVisible(false);
    	im4ok.setVisible(false);
    	nextQuestion();
    }

    @FXML
    void actionWybor1(MouseEvent event) {
    	System.out.println("Wybor1");
    	wybor=1;
    	btnSprawdz.setDisable(false);
    }

    @FXML
    void actionWybor2(MouseEvent event) {
    	System.out.println("Wybor2");
    	wybor=2;
    	btnSprawdz.setDisable(false);
    }

    @FXML
    void actionWybor3(MouseEvent event) {
    	System.out.println("Wybor3");
    	wybor=3;
    	btnSprawdz.setDisable(false);

    }

    @FXML
    void actionWybor4(MouseEvent event) {
    	System.out.println("Wybor4");
    	wybor=4;
    	btnSprawdz.setDisable(false);
    }
    
    private boolean next_random_question(){
    	Random r =new Random();
    	current_question=0;
    	int maxq=pytania.size();
    	int p;
    	int numtry=0;
    	do{
    		p=r.nextInt(maxq);
    		numtry++;
    	} while (odpowiedzi.containsKey(pytania.get(p))&&(numtry<2*App.liczba_pytan)); 
    	if (numtry>2*App.liczba_pytan)
    		return false;
    				
    	current_question=p;
    	System.out.println(pytania.get(p));
    	
    	return true;
    }
    
    private void nextQuestion() throws IOException{
    	if ((pytania==null)||(pytania.size()<App.liczba_pytan)){
			Alert e = new Alert(AlertType.ERROR);
        	e.setContentText("Niewystarczaj¹ca liczba pytañ do przeprowadzenia testu !");
        	e.setHeaderText("B³¹d");
        	e.setTitle("B³¹d");
        	e.showAndWait();
        	zamknij();
        	return;
		}
    	if (next_random_question()){
    		pytanie++;
     	
	    	rbOdp1.setDisable(false);
	    	rbOdp2.setDisable(false);
	    	rbOdp3.setDisable(false);
	    	rbOdp4.setDisable(false);
	    	rbOdp1.setSelected(false);
	    	rbOdp2.setSelected(false);
	    	rbOdp3.setSelected(false);
	    	rbOdp4.setSelected(false);
	    	btnNext.setDisable(true);
	    	btnKoniec.setDisable(true);
	    	btnSprawdz.setDisable(false);
	    	if (pytanie==App.liczba_pytan) {
	    		//btnKoniec.setDisable(false);
	    		//btnNext.setDisable(true);
	    		btnKoniec.setVisible(true);
	    		btnNext.setVisible(false);
	    	}else{
	    		//btnNext.setDisable(false);
	    		//btnKoniec.setDisable(true);
	    		btnKoniec.setVisible(false);
	    		btnNext.setVisible(true);
	    	}
	    	btnSprawdz.setDisable(true);
	    	lblNumerPytania.setText(String.format("%d", pytanie));
	    	lblPozostaloPytan.setText(String.format("%d", App.liczba_pytan-pytanie));
	    	pbProgress.setProgress(((double)pytanie)/((double)App.liczba_pytan));
	    	lblPytanie.setText(pytania.get(current_question).getPytanie());
	    	lblZakresPytania.setText(zakresy.get(pytania.get(current_question)));
	    	rbOdp1.setText(pytania.get(current_question).getOdp1());
	    	rbOdp2.setText(pytania.get(current_question).getOdp2());
	    	rbOdp3.setText(pytania.get(current_question).getOdp3());
	    	rbOdp4.setText(pytania.get(current_question).getOdp4());
    	} else
    	{
    		Alert e = new Alert(AlertType.ERROR);
        	e.setContentText("Nie ma ju¿ pytañ do zadania, koniec testu");
        	e.setHeaderText("B³¹d");
        	e.setTitle("B³¹d");
        	e.showAndWait();
        	btnKoniec.setVisible(false);
        	btnNext.setVisible(false);
        	btnSprawdz.setVisible(false);
        	
    	}
    }
    
    public int ile_testow(){
    	int ile=0;
    	String sql="select count(*) from testy where kursant='"+App.email+"';";
    	System.out.println(sql);
 	
    	try {
			ResultSet rs = db.Connection().createStatement().executeQuery(sql);
			if (rs.next()){
				// coœ siê policzy³o
				ile=rs.getInt(1);
			}else{
				// nic nie ma
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Wystapi³ b³¹d "+e.getMessage());
			e.printStackTrace();
		}
    	
    	return ile;
    }
    
    public int srednia(){
    	int ile=0;
    	String sql="select coalesce(avg(wynik),0) from testy where kursant='"+App.email+"';";
    	System.out.println(sql);
 	
    	try {
			ResultSet rs = db.Connection().createStatement().executeQuery(sql);
			if (rs.next()){
				// coœ siê policzy³o
				ile=rs.getInt(1);
			}else{
				// nic nie ma
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Wystapi³ b³¹d "+e.getMessage());
			e.printStackTrace();
		}
    	
    	return ile;
    }
    
    public void load_pytania() throws SQLException{
    	Connection conn = db.Connection();
    	pytania=new ArrayList<Pytania>();
    	odpowiedzi=new HashMap<Pytania,Integer>();
    	zakresy=new HashMap<Pytania,String>();
    	String zakres="(''";
    	if (App.zakres_sql) zakres+=",'SQL'";
    	if (App.zakres_java) zakres+=",'Java'";
    	if (App.zakres_python) zakres+=",'Python'";
    	if (App.zakres_git) zakres+=",'GiT'";
    	if (App.zakres_frontend) zakres+=",'FE'";
    	if (App.zakres_spring) zakres+=",'SPRING'";
    	zakres+=")";
    	String sql="select p.*,z.opis_zakresu from pytania p inner join zakresy z on z.zakres=p.zakres where z.zakres in "+zakres+";";
    	
    	System.out.println(sql);
		ResultSet rs = conn.createStatement().executeQuery(sql);
		while(rs.next()){
			try {
				Pytania p=new Pytania(rs.getInt(1), rs.getString(2), rs.getString(3),
						rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8));
				pytania.add(p);
				System.out.println(p);
				zakresy.put(p,rs.getString(9));
							
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
    }

    public void zamknij() {
    	try{
    		System.out.println(1);
    		btnNext.getScene().getWindow().hide();
    		System.out.println(2);
	    	Stage stage = new Stage();
	    	Parent parent = (Parent) FXMLLoader.load(getClass().getResource("/app/View/ZakresView.fxml"));
	    	Scene scene = new Scene(parent);
	    	stage.setScene(scene);
	    	stage.setTitle("Logowanie");
	    	System.out.println(3);
	    	stage.show();	    
	    	System.out.println(4);
    	} catch (Exception e) {
    		System.out.println("Zamykanie przed otwarciem");
    	}
    }
    
    public void initialize() throws IOException {
    	db = new DBConnector();
    	pytanie=0;
    	lblKursant.setText(String.format("%s %s",App.imie,App.nazwisko));
    	lblIleTestow.setText(String.format("%d",ile_testow()));
    	lblSrednia.setText(String.format("%d", srednia()));
    	numertestu=Testy.createTest(App.email);
    	if (numertestu<0){
    		System.out.println("Wyst¹pi³ nieprzewidziany b³¹d, nie da siê dodaæ testu do bazy danych !");
    		System.exit(100);
    	}
    	;
    	try {
			load_pytania();			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Nieprzewidziany b³¹d, nie uda³o siê wczytaæ listy pytañ !");
			System.exit(100);
		}
    	    	
    	nextQuestion();
    } 
}
