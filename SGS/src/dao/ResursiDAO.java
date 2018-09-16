package dao;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

import beans.Kompanija;
@SuppressWarnings (value="unchecked")


public class ResursiDAO {
	
	private HashMap<Integer,Kompanija> kompanije;
	private String putanja = "D:\\SGS\\";
	private String imeFajla = "kljucevi.txt";
	
	public ResursiDAO() {}
	
	void snimiListuID() {
		
		ArrayList<Integer> kljucevi = new ArrayList<Integer>(kompanije.keySet());
		
		try{
	         FileOutputStream fos= new FileOutputStream(putanja+imeFajla);
	         ObjectOutputStream oos= new ObjectOutputStream(fos);
	         oos.writeObject(kljucevi);
	         oos.close();
	         fos.close();
	       }catch(IOException ioe){
	         ioe.printStackTrace();
	       }

	}
	
	void ucitajLstuID() {
		
		ArrayList<Integer> kljucevi = null;
		
		try
        {
            FileInputStream fis = new FileInputStream(putanja+imeFajla);
            ObjectInputStream ois = new ObjectInputStream(fis);
            kljucevi = (ArrayList<Integer>) ois.readObject();
            ois.close();
            fis.close();
         }catch(IOException ioe){
             ioe.printStackTrace();
             return;
          }catch(ClassNotFoundException c){
             System.out.println("Class not found");
             c.printStackTrace();
             return;
          }
		
		if(kljucevi!=null) {
			ucitajResurse(kljucevi);
		}else {
			System.out.println("Greska u citanju fajla!");
		}
	}
	
	void ucitajResurse(ArrayList<Integer> lista) {
		for(Integer i : lista) {
			//TREBA UCITATI POSEBNO SVAKI FOLDER KOMPANIJE, KAO I SADRZAJ UNUTAR SVAKOG
			//SAMO DA SE PROMENI
		}
	}

}
