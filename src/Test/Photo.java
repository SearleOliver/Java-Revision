package Test;

public class Photo{
	private String titre;
	private Date date;
	private String fichier;
	
	
	public Photo(String titre,Date date,String fichier) {
		this.titre=titre;
		this.date=date;
		this.fichier=fichier;
	}
	
	public String getTitre() {
		return this.titre;
	}
	
	public Date getDate()
	{
		return this.date;
	}
	
	public String getFichier() {
		return  this.fichier;
	}
	
	public String toString() {
		return titre+" fichier : "+fichier+" date : "+date.getDate();
	}

}
