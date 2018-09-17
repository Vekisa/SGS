package beans;

public class Popularnost {

	private int brojPoseta;
	private double procenatPopularnosti;
	
	public Popularnost() {}
	
	public Popularnost(int brojPoseta, double procenatPopularnosti) {
		super();
		this.brojPoseta = brojPoseta;
		this.procenatPopularnosti = procenatPopularnosti;
	}
	
	@Override
	public String toString() { 
	    return brojPoseta + ", " + procenatPopularnosti;
	} 

	public int getBrojPoseta() {
		return brojPoseta;
	}

	public void setBrojPoseta(int brojPoseta) {
		this.brojPoseta = brojPoseta;
	}

	public double getProcenatPopularnosti() {
		return procenatPopularnosti;
	}

	public void setProcenatPopularnosti(double procenatPopularnosti) {
		this.procenatPopularnosti = procenatPopularnosti;
	}
	
}
