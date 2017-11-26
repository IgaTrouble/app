package app.Model;

public class Pytania {
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
	

}
