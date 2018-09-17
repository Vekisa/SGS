package beans;

import java.awt.Image;
import java.util.HashMap;

public class Kompanija {
	private String naziv;
	private Adresa adresa;
	private String email;
	private Integer id;
	private Popularnost popularnost;
	private String slika;
	private HashMap<Integer,Artikal> artikli;
	
	public Kompanija() {
		this.artikli = new HashMap<Integer,Artikal>();
	}

	public Kompanija(String naziv, Adresa adresa, String email) {
		super();
		this.naziv = naziv;
		this.adresa = adresa;
		this.email = email;
		this.artikli = new HashMap<Integer,Artikal>();
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public Adresa getAdresa() {
		return adresa;
	}

	public void setAdresa(Adresa adresa) {
		this.adresa = adresa;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public HashMap<Integer, Artikal> getArtikli() {
		return artikli;
	}

	public void setArtikli(HashMap<Integer, Artikal> artikli) {
		this.artikli = artikli;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Popularnost getPopularnost() {
		return popularnost;
	}

	public void setPopularnost(Popularnost popularnost) {
		this.popularnost = popularnost;
	}

	public String getSlika() {
		return slika;
	}

	public void setSlika(String slika) {
		this.slika = slika;
	}
}
