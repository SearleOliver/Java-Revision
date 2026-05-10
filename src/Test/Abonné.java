package Test;

public class Abonné implements Comparable<Abonné>{
	private int id;
	private String nom;
	

	public Abonné(int id, String nom) {
		this.id=id;this.nom=nom;
	}
	
	public String getNom() {
		return this.nom;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Abonné abo) {
			return id==abo.getId();		}
		return false;
	}

	@Override
	public int compareTo(Abonné o) {
		return id-o.getId();
	}

	private int getId() {
		return this.id;
	}
	
	

}
