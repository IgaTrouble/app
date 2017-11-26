package app.Model;

public class Loginy {
	private String email;
	private String imie;
	private String nazwisko;
	private String grupa;
	private String pass;
	private String typ;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getImie() {
		return imie;
	}
	public void setImie(String imie) {
		this.imie = imie;
	}
	public String getNazwisko() {
		return nazwisko;
	}
	public void setNazwisko(String nazwisko) {
		this.nazwisko = nazwisko;
	}
	public String getGrupa() {
		return grupa;
	}
	public void setGrupa(String grupa) {
		this.grupa = grupa;
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
	public void setTyp(String typ) {
		this.typ = typ;
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
	
	
	
}
