package services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import dao.ResursiDAO;

@Path("/osnovniServis")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class OsnovniServis {
		
		
		private ResursiDAO baza;
		private String resursPath;
		
		@Context
		ServletContext servContext;
		
		public OsnovniServis() {
			
		}
		
		@PostConstruct
		public void init() {
			
			if(servContext.getAttribute("podaci") == null) {
					resursPath = servContext.getRealPath("/WEB-INF/putanja.txt");
					baza = new ResursiDAO(resursPath);
				
				servContext.setAttribute("podaci", baza);
			}else {
				baza = (ResursiDAO)servContext.getAttribute("podaci");
			}
			
			baza.ispisiBazu();
			
		}
		
		void preurediBazu() {
			baza = new ResursiDAO(resursPath);
		}
		
		@POST
		@Path("/proba")
		public void proba(@QueryParam("text") String text) {
			System.out.println("Poslao: " + text);
			
		
		}
		
		//Servis za promenu osnovne putanje fajl sistema aplikacije
		//Menja je u putanja.txt
		@POST
		@Path("/promeniPutanju")
		@Produces(MediaType.APPLICATION_JSON)
		public int promeniPutanju(@QueryParam("putanja") String putanja) {
			
			File f = new File(putanja);
			if(!f.exists()) {
				return 0;
			}else {
				resursPath = putanja;
			}
			
			try (PrintStream out = new PrintStream(new FileOutputStream(resursPath))) {
			    out.print(putanja);
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			preurediBazu();
			return 1;
		}
		
		
		
		
}

