package beans;

public class Artikal {
	public static enum Tip {HRANA, PICE};
	
	private String naziv;
	private double cena;
	private String slika;
	private Integer id;
	private Tip tip;
	
	public Artikal() {
	}

	public Artikal(String naziv, double cena, String slika) {
		super();
		this.naziv = naziv;
		this.cena = cena;
		this.slika = slika;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}

	public String getSlika() {
		return slika;
	}

	public void setSlika(String slika) {
		this.slika = slika;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Tip getTip() {
		return tip;
	}

	public void setTip(Tip tip) {
		this.tip = tip;
	}
	
}
