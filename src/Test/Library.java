package Test;

import java.util.HashSet;
import java.util.Set;

public class Library {
	Set<Parchemin> lib = new HashSet<>();
	
	public void library (Set<Parchemin> lib) {
		this.lib = lib;
	}
	
	public void addParch(String titre, Student auteur, Date date) {
    	Parchemin parchemin = new Parchemin(titre,auteur,date);
    	lib.add(parchemin);
    }
	
	public boolean exists(String titre, Student auteur) {
		Parchemin parch = new Parchemin(titre, auteur, new Date(0,0,0));
		return lib.contains(parch);
	}
	
	public boolean existkey(String titre, Student auteur) {
		Parchemin parchemin = new Keytest(titre, auteur);
		return lib.contains(parchemin);
	}
	
	public static void main(String[] args) {
		Library bong = new Library ();
		bong.addParch("Mein",new Student(3,"Hitlerette"),new Date(13,7,1945));
		bong.addParch("Marc",new Student(99,"Marx"),new Date(12,12,1999));
		bong.addParch("1984",new Student(22,"Jorj oh well"),new Date(1,1,1983));
		bong.addParch("drawings",new Student(4,"Kevin"),new Date (2,7,1999));
    	System.out.println(bong.exists("drawings",new Student(4,"Kevin")));
    	System.out.println(bong.existkey("1984",new Student(22,"Jorj oh well")));

	}

}
