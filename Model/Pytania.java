package app.Model;

import java.sql.Connection;
import java.sql.SQLException;

import app.Database.DBConnector;

public class Pytania {
	DBConnector db;
	private Integer idp;
	private String zakres;
	private String pytanie;
	private String odp1;
	private String odp2;
	private String odp3;
	private String odp4;
	private Integer odppopr;
	
	public Integer getIdp() {
		return idp;
	}
	public void setIdp(Integer idp) {
		this.idp = idp;
	}
	public String getZakres() {
		return zakres;
	}
	public void setZakres(String zakres) {
		this.zakres = zakres;
	}
	public String getPytanie() {
		return pytanie;
	}
	public void setPytanie(String pytanie) {
		this.pytanie = pytanie;
	}
	public String getOdp1() {
		return odp1;
	}
	public void setOdp1(String odp1) {
		this.odp1 = odp1;
	}
	public String getOdp2() {
		return odp2;
	}
	public void setOdp2(String odp2) {
		this.odp2 = odp2;
	}
	public String getOdp3() {
		return odp3;
	}
	public void setOdp3(String odp3) {
		this.odp3 = odp3;
	}
	public String getOdp4() {
		return odp4;
	}
	public void setOdp4(String odp4) {
		this.odp4 = odp4;
	}
	public Integer getOdppopr() {
		return odppopr;
	}
	public void setOdppopr(Integer odppopr) {
		this.odppopr = odppopr;
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
	/*public Pytania(String text, String text2, String text3, String text4, String text5, Integer odp,
			String value) {
		
	}*/
	public boolean savedotb() throws SQLException {
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
			System.out.println("B��d zapisu do DB : "+e.getMessage());
			conn.close();
			
			return false;
		}
	}

}
