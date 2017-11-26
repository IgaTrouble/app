package app.Model;

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
	public Odpowiedzi(Integer ido, Integer test, Integer pytanie, Integer odpowiedz) {
		super();
		this.ido = ido;
		this.test = test;
		this.pytanie = pytanie;
		this.odpowiedz = odpowiedz;
	}
	
}
