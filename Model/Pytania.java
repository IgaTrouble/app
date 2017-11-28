package app.Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import app.Controller.PytaniaController;
import app.Database.DBConnector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Pytania {
	private Integer idp;
	private String zakres;
	private String pytanie;
	private String odp1;
	private String odp2;
	private String odp3;
	private String odp4;
	private Integer odppopr;
	static String sqlUpd;
	DBConnector db;
	PytaniaController pc = new PytaniaController();
	
	
	@Override
	public String toString() {
		return "Pytania [idp=" + idp + ", zakres=" + zakres + ", pytanie=" + pytanie + ", odp1=" + odp1 + ", odp2="
				+ odp2 + ", odp3=" + odp3 + ", odp4=" + odp4 + ", odppopr=" + odppopr + "]";
	}


	public Pytania(Integer idp, String zakres, String pytanie, String odp1, String odp2, String odp3, String odp4,
			Integer odppopr) {
		super();
		this.idp = idp;
		this.zakres = zakres;
		this.pytanie = pytanie;
		this.odp1 = odp1;
		this.odp2 = odp2;
		this.odp3 = odp3;
		this.odp4 = odp4;
		this.odppopr = odppopr;
	}
	
	
	public Integer getIdp() {
		return idp;
	}
	public void setIdp(Integer idp) {
		this.idp = idp;
	}
	public String getZakres() {
		return zakres;
	}
	public void setZakres(String zakres) throws SQLException {
		this.zakres = zakres;
		if (zakres.equals("SQL") || zakres.equals("Git") || zakres.equals("Front-End")
				|| zakres.equals("Python") || zakres.equals("Java") || zakres.equals("Spring"))  {
		sqlUpd="update pytania set zakres='"+this.zakres+"' where idp='"+this.idp+"';";
		System.out.println(sqlUpd);
		this.update();
		} else {
			Alert e = new Alert(AlertType.ERROR);
        	e.setContentText("Błąd");
        	e.setHeaderText("Błąd, podano błędny zakres pytań");
        	e.setTitle("Błąd");
        	e.showAndWait();
		}
		
		
	}
	public String getPytanie() {
		return pytanie;
	}
	public void setPytanie(String pytanie) throws SQLException {
		this.pytanie = pytanie;
		sqlUpd="update pytania set pytanie='"+this.pytanie+"' where idp='"+this.idp+"';";
		System.out.println(sqlUpd);
		this.update();
	}
	public String getOdp1() {
		return odp1;
	}
	public void setOdp1(String odp1) throws SQLException {
		this.odp1 = odp1;
		sqlUpd="update pytania set odp1='"+this.odp1+"' where idp='"+this.idp+"';";
		System.out.println(sqlUpd);
		this.update();
	}
	public String getOdp2() {
		return odp2;
	}
	public void setOdp2(String odp2) throws SQLException {
		this.odp2 = odp2;
		sqlUpd="update pytania set odp2='"+this.odp2+"' where idp='"+this.idp+"';";
		System.out.println(sqlUpd);
		this.update();
	}
	public String getOdp3() {
		return odp3;
	}
	public void setOdp3(String odp3) throws SQLException {
		this.odp3 = odp3;
		sqlUpd="update pytania set odp3='"+this.odp3+"' where idp='"+this.idp+"';";
		System.out.println(sqlUpd);
		this.update();
	}
	public String getOdp4() {
		return odp4;
	}
	public void setOdp4(String odp4) throws SQLException {
		this.odp4 = odp4;
		sqlUpd="update pytania set odp4='"+this.odp4+"' where idp='"+this.idp+"';";
		System.out.println(sqlUpd);
		this.update();
		
	}
	public Integer getOdppopr() {
		return odppopr;
	}
	public void setOdppopr(Integer odppopr) throws SQLException {
		this.odppopr = odppopr;
		if (odppopr < 5) {
			sqlUpd="update pytania set odppopr='"+this.odppopr+"' where idp='"+this.idp+"';";
			System.out.println(sqlUpd);
			this.update();
		} else {
			Alert e = new Alert(AlertType.ERROR);
        	e.setContentText("Błąd"); //to powinno być w pytania controlerze przy edit
        	e.setHeaderText("Błąd, poprawna odpowiedz może zawierać się w przedziale 1-4");
        	e.setTitle("Błąd");
        	e.showAndWait();
		}
		
	}

	public boolean savePyt() throws SQLException {
		db = new DBConnector();
		String sql="insert into pytania(idp, zakres,pytanie,odp1,odp2,odp3,odp4, odppopr) "
				+ "values("+this.idp+",'"+this.zakres+"','"+this.pytanie+"','"+this.odp1+"','"+this.odp2+"','"+this.odp3+"','"+this.odp4+"',"+this.odppopr+")";
		Connection conn;
		conn=db.Connection();
		
		System.out.println(sql);
		try {	
			conn.createStatement().executeUpdate(sql);
			conn.close();
			return true;
		} catch (SQLException e) {
			System.out.println("Błąd zapisu do DB : "+e.getMessage());
			conn.close();
			
			return false;
		}
	}
	
	
	public boolean delete() throws SQLException{
		db = new DBConnector();
		String sql="delete from pytania where idp='"+this.idp+"';";
		Connection conn;
		conn=db.Connection();
		System.out.println(sql);
		try {	
			conn.createStatement().executeUpdate(sql);
			conn.close();
			return true;
		} catch (SQLException e) {
			System.out.println("Błąd kasowania z DB : "+e.getMessage());
			conn.close();
			
			return false;
		}
	}
	

	
	public void update( ) throws SQLException {
	db = new DBConnector();
	Connection conn=db.Connection();
	System.out.println(sqlUpd);
	try {	
		conn.createStatement().executeUpdate(sqlUpd);
		conn.close();			
	} catch (SQLException e) {
		System.out.println("Błąd zapisu do Bazy Danych : "+e.getMessage());
		conn.close();			
	}
	
	}
	
	

}
