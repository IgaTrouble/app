package app.Model;

import java.sql.ResultSet;
import java.sql.SQLException;

import app.Database.DBConnector;

public class Testy {
	private Integer idt;
	private String kursant;
	private String data_testu;
	public Integer getIdt() {
		return idt;
	}
	public void setIdt(Integer idt) {
		this.idt = idt;
	}
	public String getKursant() {
		return kursant;
	}
	public void setKursant(String kursant) {
		this.kursant = kursant;
	}
	public String getData_testu() {
		return data_testu;
	}
	public void setData_testu(String data_testu) {
		this.data_testu = data_testu;
	}
	
	private boolean save_to_db(){
		String sql="insert into testy(idt,kursant,data_testu) values("+this.idt+",'"+App.email+"','"+this.data_testu+"');";
		DBConnector db = new DBConnector();
		System.out.println(sql);
		try {
			db.Connection().createStatement().executeUpdate(sql);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean update_wynik(int test, int wynik){
		String sql="update testy set wynik="+wynik+" where idt="+test+";";
		DBConnector db = new DBConnector();
		System.out.println(sql);
		try {
			db.Connection().createStatement().executeUpdate(sql);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean newidt(){
		String sql="select coalesce(max(idt)+1,1),curdate() from testy;";
		DBConnector db = new DBConnector();
		System.out.println(sql);
		ResultSet rs;
		try {
			rs = db.Connection().createStatement().executeQuery(sql);
			if (rs.next()){
				this.idt=rs.getInt(1);
				this.data_testu=rs.getString(2);
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return false;
	}
	
	public static int createTest(String kursant) {
		Testy t=new Testy();
		if (t.newidt()){
			t.kursant = kursant;
			if (t.save_to_db()){
				return t.getIdt();
			};
		}
		return -1;
	}
	
}
