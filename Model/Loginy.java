package app.Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import app.Database.DBConnector;

public class Loginy {
	private String email;
	private String imie;
	private String nazwisko;
	private String grupa;
	private String pass;
	private String typ;
	DBConnector db;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getImie() {
		return imie;
	}
	public void setImie(String imie) throws SQLException {
		this.imie = imie;
		
		db = new DBConnector();
		String sql="update  loginy set imie='"+this.imie+"' where email='"+this.email+"';";
				
		Connection conn;
		conn=db.Connection();
		
		System.out.println(sql);
		try {	
			conn.createStatement().executeUpdate(sql);
			conn.close();			
		} catch (SQLException e) {
			System.out.println("B��d zapisu do DB : "+e.getMessage());
			conn.close();			
		}
	}
	public String getNazwisko() {
		return nazwisko;
	}
	public void setNazwisko(String nazwisko) throws SQLException {
		this.nazwisko = nazwisko;
		
		db = new DBConnector();
		String sql="update  loginy set nazwisko='"+this.nazwisko+"' where email='"+this.email+"';";
				
		Connection conn;
		conn=db.Connection();
		
		System.out.println(sql);
		try {	
			conn.createStatement().executeUpdate(sql);
			conn.close();			
		} catch (SQLException e) {
			System.out.println("B��d zapisu do DB : "+e.getMessage());
			conn.close();			
		}
	}
	public String getGrupa() {
		return grupa;
	}
	public void setGrupa(String grupa) throws SQLException {
		this.grupa = grupa;
		db = new DBConnector();
		String sql="update  loginy set grupa='"+this.grupa+"' where email='"+this.email+"';";
				
		Connection conn;
		conn=db.Connection();
		
		System.out.println(sql);
		try {	
			conn.createStatement().executeUpdate(sql);
			conn.close();			
		} catch (SQLException e) {
			System.out.println("B��d zapisu do DB : "+e.getMessage());
			conn.close();			
		}
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
		
	}
	public String getTyp() {
		return typ;
	}
	public void setTyp(String typ) throws SQLException {
		this.typ = typ;
		
		db = new DBConnector();
		String sql="update  loginy set typ='"+this.typ+"' where email='"+this.email+"';";
				
		Connection conn;
		conn=db.Connection();
		
		System.out.println(sql);
		try {	
			conn.createStatement().executeUpdate(sql);
			conn.close();			
		} catch (SQLException e) {
			System.out.println("B��d zapisu do DB : "+e.getMessage());
			conn.close();			
		}
	}
	
	public Loginy(String email, String imie, String nazwisko, String grupa, String pass, String typ) {
		super();
		this.email = email;
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.grupa = grupa;
		this.pass = pass;
		this.typ = typ;
	}
	
	public boolean savedotb() throws SQLException {
		db = new DBConnector();
		String sql="insert into loginy(email,imie,nazwisko,grupa,pass,typ) "
				+ "values('"+this.email+"','"+this.imie+"','"+this.nazwisko+"','"+this.grupa+"','"+this.pass+"','"+this.typ+"')";
		Connection conn;
		conn=db.Connection();
		
		System.out.println(sql);
		try {	
			conn.createStatement().executeUpdate(sql);
			conn.close();
			return true;
		} catch (SQLException e) {
			System.out.println("B��d zapisu do DB : "+e.getMessage());
			conn.close();
			
			return false;
		}
	}
	
	public boolean delete() throws SQLException{
		db = new DBConnector();
		String sql="delete from loginy where email='"+this.email+"';";

		Connection conn;
		conn=db.Connection();
		
		System.out.println(sql);
		try {	
			conn.createStatement().executeUpdate(sql);
			conn.close();
			return true;
		} catch (SQLException e) {
			System.out.println("B��d kasowania z DB : "+e.getMessage());
			conn.close();
			
			return false;
		}
	}
	
	
	
}
