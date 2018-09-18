package dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

import beans.Adresa;
import beans.Artikal;
import beans.Artikal.Tip;
import beans.Kompanija;
import beans.Popularnost;

/*details.txt za kompaniju treba da izgleda:
 * naziv
 * adresa.mesto
 * adresa.ulica
 * adresa.broj
 * email
 * popularnost.brojPoseta
 * popularnost.procenatPopularnosti
 */

/*details.txt za artikal
 * naziv
 * cena
 * tip
 */

public class ResursiDAO {
	
	private HashMap<Integer,Kompanija> kompanije;
	private String putanja;
	
	public ResursiDAO(String path) {
		kompanije = new HashMap<Integer,Kompanija>();
		ucitajPutanju(path);
		ucitajResurse();
	}
	
	private void ucitajPutanju(String path) {
		File f = new File(path);
		if(!f.exists()) {
			System.out.println("Fajl sa putanjom nije pronadjen!");
			return;
		}
		
		try {
			FileInputStream fstream = new FileInputStream(path);
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
			String strLine = br.readLine();
			System.out.println("PUTANJA: " + strLine);
			putanja = strLine;
			br.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void ucitajResurse() {
		
		File baza = new File(putanja);
		if(!baza.exists()) {
			System.out.println("Baza nije pronadjena!");
			return;
		}else if(baza.exists() && !baza.isDirectory()) {
			System.out.println("Baza nije direktorijum!");
			return;
		}
		
		File[] kompanijeLista = baza.listFiles();
		
		for(File f : kompanijeLista) {
			if(!f.isDirectory()) {
				System.out.println("Kompanija " + f.getName() + " nije dobro uskladistena!");
				continue;
			}
			
			ucitajKompaniju(f);
		}
	}
	
	private void ucitajKompaniju(File fKompanija) {
		Kompanija pomKomp = new Kompanija();
		String sID = fKompanija.getName();
		Integer id;
		
		try {
			id = Integer.parseInt(sID);
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("Greska u pretvaranju id-a!");
			return;
		}
		
		pomKomp.setId(id);
		
		kompanije.put(pomKomp.getId(), pomKomp);
		
		File[] listaArtikala = fKompanija.listFiles();
		
		for(File f : listaArtikala) {
			
			if(f.getName().contains("image")){
				pomKomp.setSlika(f.getPath());
				continue;
			}
			
			if(f.getName().contains("details")){
				ucitajDetaljeKompanije(pomKomp.getId(),f);
				continue;
			}
			
			if(!f.isDirectory()) {
				System.out.println("Artikal " + f.getName() + "nije dobro uskladisten!");
				continue;
			}
			
			ucitajArtikal(pomKomp.getId(),f);
			
			
		}
	}
	
	private void ucitajDetaljeKompanije(Integer id,File f) {

		Kompanija k = kompanije.get(id);
		
		if(k == null)
			return;
		
		int brojac = 0;
		String[] ucitani = new String[7];
		Integer pomBr = -1;
		double pomBrD = -1;
		
		try {
			FileInputStream fstream = new FileInputStream(f.getPath());
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
			String strLine;
			
			while ((strLine = br.readLine()) != null && brojac < 8)   {
			  strLine = strLine.trim();
			  ucitani[brojac] = strLine;
			  ++brojac;
			}
			
			br.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		k.setNaziv(ucitani[0]);
		
		try {
			pomBr = Integer.parseInt(ucitani[3]);
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("Broj adrese ne moze da se parsira!");
		}
		
		k.setAdresa(new Adresa(ucitani[1],ucitani[2],pomBr));
		k.setEmail(ucitani[4]);
		
		try {
			pomBr = Integer.parseInt(ucitani[5]);
			pomBrD = Double.parseDouble(ucitani[6]);
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("Popularnost ne moze da se parsira!");
		}
		
		k.setPopularnost(new Popularnost(pomBr,pomBrD));
	}
	
	private void ucitajArtikal(Integer id,File fArtikal) {

		Kompanija k = kompanije.get(id);
		if(k == null) {
			System.out.println("Lose poslata kompanija!");
			return;
		}
		
		File[] artikalList = fArtikal.listFiles();
		
		Integer idPom;
		
		try {
			idPom = Integer.parseInt(fArtikal.getName());
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("Greska u pretvaranju id-a!");
			return;
		}
		
		Artikal pomArt = new Artikal();
		pomArt.setId(idPom);
		
		k.getArtikli().put(pomArt.getId(), pomArt);
		
		for(File f : artikalList) {
			if(f.getName().contains("image")){
				pomArt.setSlika(f.getPath());
				continue;
			}
			
			if(f.getName().contains("details")){
				ucitajDetaljeArtikla(id,pomArt.getId(),f);
				continue;
			}
		}
	}
	
	private void ucitajDetaljeArtikla(Integer idK,Integer idA,File f) {
		Kompanija kPom = kompanije.get(idK);
		Artikal aPom = kPom.getArtikli().get(idA);
		
		if(kPom == null || aPom == null) {
			System.out.println("Lose preuzeti parametri!");
			return;
		}
		
		String[] ucitani = new String[3];
		int brojac = 0;
		double pomBrD = -1;
		
		try {
			FileInputStream fstream = new FileInputStream(f.getPath());
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
			String strLine;
			
			while ((strLine = br.readLine()) != null && brojac < 4)   {
			  strLine = strLine.trim();
			  ucitani[brojac] = strLine;
			  ++brojac;
			}
			
			br.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(ucitani[0] + " " + ucitani[1] + " " + ucitani[2]);
		
		aPom.setNaziv(ucitani[0]);
		
		try {
			System.out.println("Pokusavam da parsiram: " + ucitani[1]);
			pomBrD = Double.parseDouble(ucitani[1]);
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("Neuspela konverzija cene!");
		}
		
		aPom.setCena(pomBrD);
		aPom.setTip(pronadjiTip(ucitani[2]));
		
	}
	
	private Tip pronadjiTip(String tip){
		
		if(tip.equals("HRANA"))
			return Tip.HRANA;
		if(tip.equals("PICE"))
			return Tip.PICE;
		
		return null;
	}
	
	public void ispisiBazu() {
		for(Kompanija k : kompanije.values()) {
			System.out.println("\nKOMPANIJA");
			System.out.println(k.getNaziv());
			System.out.println(k.getAdresa().toString());
			System.out.println(k.getEmail());
			System.out.println(k.getId());
			System.out.println(k.getPopularnost().toString());
			System.out.println(k.getSlika());
			System.out.println("ARTIKLI");
			for(Artikal a : k.getArtikli().values()) {
				System.out.println(a.getNaziv());
				System.out.println(a.getCena());
				System.out.println(a.getSlika());
				System.out.println(a.getId());
				System.out.println(a.getTip());
			}
			System.out.println("/ARTIKLI");
			System.out.println("/KOMPANIJA\n");
			
		}
	}

	public String getPutanja() {
		return putanja;
	}

	public void setPutanja(String putanja) {
		this.putanja = putanja;
	}

	public HashMap<Integer, Kompanija> getKompanije() {
		return kompanije;
	}

	public void setKompanije(HashMap<Integer, Kompanija> kompanije) {
		this.kompanije = kompanije;
	}

}
