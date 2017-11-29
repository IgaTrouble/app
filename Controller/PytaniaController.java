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
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

public class PytaniaController {
	public DBConnector db;
	public ObservableList<Pytania> data;
    ObservableList<String> zakres = FXCollections.observableArrayList("SQL", "Git", "Front-End", "Python", "Java", "Spring");
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
    private Button btn_log;


    @FXML
    private TextField tfPytanie; 

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
    	if (czy_wypelnione()){
    		Integer odp =0;
    		String zakres = ((Node) G1.getSelectedToggle()).getId();
    		switch (zakres) {
    		case "rbOdp1": odp = 1;
    			break;
    		case "rbOdp2": odp = 2;
				break;
    		case "rbOdp3": odp = 3;
				break;
    		case "rbOdp4": odp = 4;
				break;
    		}
    		Pytania l=new Pytania(0, cb_zakres.getValue(), tfPytanie.getText(),  tfOdp1.getText(),  tfOdp2.getText(),
    				 tfOdp3.getText(),  tfOdp4.getText(), odp);
    			
    		try {
				l.savePyt();
				select();
				clearPyt();
			} catch (SQLException e) {
				System.out.println("Nie udało się zapisać");
				e.printStackTrace();
			}
    	}
    }
    public boolean czy_wypelnione(){
    	System.out.println("Weryfikacja czy wypełnione wszystkie pola");
    	String err="Nie wypełniłeś wymaganych pól:";
    	boolean ok=true;
    	if (tfPytanie.getText().equals("")) { err+="\n* pole 'pytanie'"; ok=false;}
    	if (tfOdp1.getText().equals("")) {err+="\n* pole 'odp 1'";ok=false;}
    	if (tfOdp2.getText().equals("")) {err+="\n* pole 'odp 2'";ok=false;}
    	if (tfOdp3.getText().equals("")) {err+="\n* pole 'odp 3'";ok=false;}
    	if (tfOdp4.getText().equals("")) {err+="\n* pole 'odp 4'";ok=false;}
    	if (G1.getSelectedToggle() == null) {err+="\n* pole 'poprawna odp'";ok=false;}
    	if ((cb_zakres.getValue() == null) || (cb_zakres.equals(""))) {err+="\n* pole 'zakres'";ok=false;}	
    	if (ok){
    		return true;
    		
    	} else{
    		Alert e = new Alert(AlertType.ERROR);
        	e.setContentText(err);
        	e.setHeaderText("Błąd, nie wypełniono wszytskich pól");
        	e.setTitle("Błąd");
        	e.showAndWait(); 
    		return false;
    	}
    }
    
    

	public void clearPyt() {
		tfPytanie.clear();
		tfOdp1.clear();
		tfOdp2.clear();
		tfOdp3.clear();
		tfOdp4.clear();
		G1.selectToggle(null);
		cb_zakres.setValue(null);		
	}
    
	public void refresh() { //czy się przyda?
		tvPytania.refresh();
	System.out.println("refresh");
	}
	
	public  void pobierz_zakres(ObservableList<String> zakres)  {
		ResultSet rs;
		//DBConnector db=new DBConnector();
		try {
			Connection conn = db.Connection();
			rs = conn.createStatement().executeQuery("select zakres from zakresy;");
			while(rs.next()){
				zakres.add(rs.getString(1));
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	}
	
    public void initialize() throws SQLException {
    	db = new DBConnector();
    	this.select();
    	this.editPytania();
    	tvPytania.setEditable(true);
    	
    	cb_zakres.setItems(zakres);
    	
    	/*
    	ObservableList<String> list = FXCollections.observableArrayList();
    	pobierz_zakres(list);
    	cb_zakres.setItems(list);
    	*/
    }
    

    public void select() throws SQLException {
System.out.println("robię select");
	    Connection conn = db.Connection();
		data = FXCollections.observableArrayList();
		ResultSet rs = conn.createStatement().executeQuery("select * from pytania");
		while(rs.next()){
			data.add(new Pytania(rs.getInt(1), rs.getString(2), rs.getString(3),
					rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8)));
		}

		colid.setCellValueFactory(new PropertyValueFactory<Pytania,Integer>("idp"));
		colpytanie.setCellValueFactory(new PropertyValueFactory<Pytania,String>("pytanie"));
		colodp1.setCellValueFactory(new PropertyValueFactory<Pytania,String>("odp1"));
		colodp2.setCellValueFactory(new PropertyValueFactory<Pytania,String>("odp2"));
		colodp3.setCellValueFactory(new PropertyValueFactory<Pytania,String>("odp3"));
		colodp4.setCellValueFactory(new PropertyValueFactory<Pytania,String>("odp4"));
		colodp5.setCellValueFactory(new PropertyValueFactory<Pytania,String>("zakres"));
		colnrodp.setCellValueFactory(new PropertyValueFactory<Pytania,Integer>("odppopr"));
		tvPytania.setItems(null);
		tvPytania.setItems(data);
		conn.close();
    }
    
    
    void editPytania() throws SQLException {
    	colpytanie.setCellFactory(TextFieldTableCell.<Pytania>forTableColumn());
    	colpytanie.setOnEditCommit(
	            (CellEditEvent<Pytania, String> t) -> {
	                try {
						((Pytania) t.getTableView().getItems().get(
						        t.getTablePosition().getRow())
						        ).setPytanie(t.getNewValue());
					} catch (SQLException e) {
						e.printStackTrace();
					}
	        });
    	colodp1.setCellFactory(TextFieldTableCell.<Pytania>forTableColumn());
    	colodp1.setOnEditCommit(
	            (CellEditEvent<Pytania, String> t) -> {
	                try {
						((Pytania) t.getTableView().getItems().get(
						        t.getTablePosition().getRow())
						        ).setOdp1(t.getNewValue());
					} catch (SQLException e) {
						e.printStackTrace();
					}
	        });
    	colodp2.setCellFactory(TextFieldTableCell.<Pytania>forTableColumn());
    	colodp2.setOnEditCommit(
	            (CellEditEvent<Pytania, String> t) -> {
	                try {
						((Pytania) t.getTableView().getItems().get(
						        t.getTablePosition().getRow())
						        ).setOdp2(t.getNewValue());
					} catch (SQLException e) {
						e.printStackTrace();
					}
	        });
    	colodp3.setCellFactory(TextFieldTableCell.<Pytania>forTableColumn());
    	colodp3.setOnEditCommit(
	            (CellEditEvent<Pytania, String> t) -> {
	                try {
						((Pytania) t.getTableView().getItems().get(
						        t.getTablePosition().getRow())
						        ).setOdp3(t.getNewValue());
					} catch (SQLException e) {
						e.printStackTrace();
					}
	        });
    	colodp4.setCellFactory(TextFieldTableCell.<Pytania>forTableColumn());
    	colodp4.setOnEditCommit(
	            (CellEditEvent<Pytania, String> t) -> {
	                try {
						((Pytania) t.getTableView().getItems().get(
						        t.getTablePosition().getRow())
						        ).setOdp4(t.getNewValue());
					} catch (SQLException e) {
						e.printStackTrace();
					}
	        });
    	
      	colodp5.setCellFactory(TextFieldTableCell.<Pytania>forTableColumn());
    	colodp5.setOnEditCommit(
	            (CellEditEvent<Pytania, String> t) -> {
	                try {
						((Pytania) t.getTableView().getItems().get(
						        t.getTablePosition().getRow())
						        ).setZakres(t.getNewValue());
						this.refresh();
					} catch (SQLException e) {
						e.printStackTrace();
					}
	            
	            });
    	
    	colnrodp.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
    	colnrodp.setOnEditCommit(
	            (CellEditEvent<Pytania, Integer> t) -> {
	                try {
						((Pytania) t.getTableView().getItems().get(
						        t.getTablePosition().getRow())
						        ).setOdppopr(t.getNewValue());
						
					} catch (SQLException e) {
						e.printStackTrace();
					}
	        }); 
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
						System.out.println("Błą przy usuwaniu "+e.getMessage());
						e.printStackTrace();
					}
	        		select();
	        	};			
	    	}
    	} 
    

    }
    
    
    @FXML
    void actionLog(MouseEvent event) throws IOException {
    	Stage stage = new Stage();
    	Parent parent = (Parent) FXMLLoader.load(getClass().getResource("/app/View/LoginView.fxml"));
    	Scene scene = new Scene(parent);
    	stage.setScene(scene);
    	stage.setResizable(false);
    	stage.setTitle("Logowanie");
    	stage.show();
    	((Node)(event.getSource())).getScene().getWindow().hide();
    }
    
    
    @FXML
    void actionZamknij(MouseEvent event) throws IOException {
    	Stage stage = new Stage();
    	Parent parent = (Parent) FXMLLoader.load(getClass().getResource("/app/View/PanelView.fxml"));
    	Scene scene = new Scene(parent);
    	stage.setScene(scene);
    	stage.setResizable(false);
    	stage.setTitle("Panel");
    	stage.show();
    	((Node)(event.getSource())).getScene().getWindow().hide();
    }


}
