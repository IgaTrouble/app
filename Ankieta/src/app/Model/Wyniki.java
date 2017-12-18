package app.Model;

public class Wyniki {
	
	private String data;
	private Integer liczba;
	private Integer wynik;
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public Integer getLiczba() {
		return liczba;
	}
	public void setLiczba(Integer liczba) {
		this.liczba = liczba;
	}
	public Integer getWynik() {
		return wynik;
	}
	public void setWynik(Integer wynik) {
		this.wynik = wynik;
	}
	public Wyniki(String data, Integer liczba, Integer wynik) {
		super();
		this.data = data;
		this.liczba = liczba;
		this.wynik = wynik;
	}
	

}
