package app.Model;

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
	public Testy(Integer idt, String kursant, String data_testu) {
		super();
		this.idt = idt;
		this.kursant = kursant;
		this.data_testu = data_testu;
	}
	
}
