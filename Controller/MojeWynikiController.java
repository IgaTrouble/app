package app.Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import app.Database.DBConnector;
import app.Model.App;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class MojeWynikiController {

    @FXML
    private Button btnZamknij;

    @FXML
    private Label lblKursant;

    @FXML
    private Label lblGrupa;

    @FXML
    private Label lblTestyAll;

    @FXML
    private Label lblTestyZakonczone;

    @FXML
    private Label lblOdpowiedziAll;

    @FXML
    private Label lblOdpowiedziPopr;

    @FXML
    private Label lblOdpowiedziNiep;

    @FXML
    private Label lblOdpowiedziNiep1;

    @FXML
    private PieChart pic;

    @FXML
    private Label lblOdpowiedziWynik;
    
    @FXML
    private ProgressBar pbWynik;
    
    @FXML
    void ActionZamknij(MouseEvent event) throws IOException {
    	System.out.println("Moje wyniki");
    	Stage stage = new Stage();
    	Parent parent = (Parent) FXMLLoader.load(getClass().getResource("/app/View/ZakresView.fxml"));
    	Scene scene = new Scene(parent);
    	stage.setScene(scene);
    	stage.setResizable(false);
    	stage.setTitle("Test");
    	stage.show();
    	((Node)(event.getSource())).getScene().getWindow().hide();

    }
    
    public void initialize() throws SQLException {
    	
    	String sql="select  count(*), 'Poprawne'"
    			+ "from odpowiedzi o "
    			+ "inner join pytania p on p.idp=o.pytanie "
    			+ "inner join testy t on t.idt=o.test "
    			+ "where t.kursant='"+App.email+"' and o.odpowiedz=p.odppopr "
    			+ "union "
    			+ "select  count(*), 'Niepoprawne' "
    			+ "from odpowiedzi o "
    			+ "inner join pytania p on p.idp=o.pytanie "
    			+ "inner join testy t on t.idt=o.test "
    			+ "where t.kursant='"+App.email+"' and o.odpowiedz<>p.odppopr ";
    	pic.setVisible(false);
    	ObservableList<Data> data = FXCollections.observableArrayList();
    	DBConnector db = new DBConnector();
        Connection conn = db.Connection();
        
        double niep=0;
        double pop=0;
        
        ResultSet rs = conn.createStatement().executeQuery(sql);
        System.out.println(sql);
        while(rs.next()){
        		
        		Double liczba=rs.getDouble(1);
        		String opis=rs.getString(2)+" ("+liczba+")";
        		if (rs.getString(2).toUpperCase().equals("POPRAWNE")) 
        			pop=liczba;
        		else
        			niep=liczba;
        				
                data.add(new PieChart.Data(opis,liczba));                            
        }       
        
        pic.getData().addAll(data);
	 	pic.setVisible(true);
	 	
	 	lblKursant.setText(App.imie+" "+App.nazwisko);
	 	lblOdpowiedziAll.setText(""+(int)(pop+niep));
	 	lblOdpowiedziPopr.setText(""+(int)(pop));
	 	lblOdpowiedziNiep.setText(""+(int)(niep));
	 	
	 	
	 	sql="select k.email, k.grupa, "
	 			+ "count(t.idt) as rozpoczete, "
	 			+ "sum(case when t.wynik is not null then 1 else 0 end) as zakonczone, "
	 			+ "coalesce(avg(t.wynik),0) as wynik "
	 			+ "from loginy k "
	 			+ "left join testy t on t.kursant=k.email "
	 			+ "where k.email='"+App.email+"' group by k.email, k.grupa; ";
	 	
	 	rs = conn.createStatement().executeQuery(sql);
        System.out.println(sql);
        while(rs.next()){
        	lblGrupa.setText(rs.getString(2));
        	lblTestyAll.setText(""+(rs.getInt(3)));
        	lblTestyZakonczone.setText(""+rs.getInt(4));
        	double es = rs.getDouble(5)*100;
        	es = Math.round(es);
    		es = es /100;
        	lblOdpowiedziWynik.setText(""+es+" %");
        	pbWynik.setProgress(rs.getDouble(5)/100.0);
        }   
	 	
	 	
	 	conn.close();
    }

}
