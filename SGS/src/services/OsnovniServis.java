package services;

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
		
		@Context
		ServletContext servContext;
		
		public OsnovniServis() {
			
		}
		
		@PostConstruct
		public void init() {
			
			if(servContext.getAttribute("podaci") == null) {
				baza = new ResursiDAO();
				servContext.setAttribute("podaci", baza);
			}else {
				baza = (ResursiDAO)servContext.getAttribute("podaci");
			}
			
			baza.ispisiBazu();
			
		}
		
		@POST
		@Path("/proba")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public void proba(@QueryParam("text") String text) {
			System.out.println("Poslao: " + text);
		}
		
}

