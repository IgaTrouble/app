package app.Model;

import java.sql.SQLException;

import app.Database.DBConnector;

public class Odpowiedzi {
	private Integer ido;
	private Integer test;
	private Integer pytanie;
	private Integer odpowiedz;
	public Integer getIdo() {
		return ido;
	}
	public void setIdo(Integer ido) {
		this.ido = ido;
	}
	public Integer getTest() {
		return test;
	}
	public void setTest(Integer test) {
		this.test = test;
	}
	public Integer getPytanie() {
		return pytanie;
	}
	public void setPytanie(Integer pytanie) {
		this.pytanie = pytanie;
	}
	public Integer getOdpowiedz() {
		return odpowiedz;
	}
	public void setOdpowiedz(Integer odpowiedz) {
		this.odpowiedz = odpowiedz;
	}
	public Odpowiedzi(Integer test, Integer pytanie, Integer odpowiedz) {
		super();
		this.ido = 0;
		this.test = test;
		this.pytanie = pytanie;
		this.odpowiedz = odpowiedz;
	}
	
	public boolean save(){
		String sql="insert into odpowiedzi(test,pytanie,odpowiedz) values("+this.test+","+this.pytanie+","+this.odpowiedz+");";
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
	
}
