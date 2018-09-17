package dao;

import java.io.File;
import java.util.HashMap;

import beans.Kompanija;

public class ResursiDAO {
	
	private HashMap<Integer,Kompanija> kompanije;
	private String putanja = "D:\\SGS\\";
	
	public ResursiDAO() {
		kompanije = new HashMap<Integer,Kompanija>();
		ucitajResurse();
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
		//UCITATI DETALJE 
		
		
	}
	
	private void ucitajArtikal(Integer id,File f) {}

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
