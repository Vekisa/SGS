package beans;

public class Adresa {
	private String mesto;
	private String ulica;
	private int broj;
	
	public Adresa() {}

	public Adresa(String mesto, String ulica, int broj) {
		super();
		this.mesto = mesto;
		this.ulica = ulica;
		this.broj = broj;
	}

	public String getMesto() {
		return mesto;
	}

	public void setMesto(String mesto) {
		this.mesto = mesto;
	}

	public String getUlica() {
		return ulica;
	}

	public void setUlica(String ulica) {
		this.ulica = ulica;
	}

	public int getBroj() {
		return broj;
	}

	public void setBroj(int broj) {
		this.broj = broj;
	}
	
	
}
