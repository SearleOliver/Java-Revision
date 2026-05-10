package Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.NavigableSet;
import java.util.TreeSet;

public class Film implements Comparable<Film> {
	private String titre;
	private int annee;
	private String realisateur;
	private double note;

	public Film(String titre,int annee,String realisateur,double note) {
		this.titre=titre;this.annee=annee;this.realisateur=realisateur;this.note=note;
	}
	
	public String getTitre() {
		return this.titre;
	}
	
	public int getAnnee() {
		return this.annee;
	}
	
	@Override
	public int hashCode() {
		return 31*(titre.hashCode()+annee);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Film film) {
			return titre.equals(film.getTitre())&&annee==film.getAnnee();
		}
		return false;
	}

	@Override
	public int compareTo(Film film) {
		int comparison = annee-film.getAnnee();
		if (comparison==0) {
			comparison = titre.compareTo(film.getTitre());
		}
		return comparison;
	}
	
	public static void main(String[] args) {
		NavigableSet<Film> films = new TreeSet<>(new Comparator<Film>() {
			@Override
			public int compare(Film o1, Film o2) {
				return o1.getAnnee()-o2.getAnnee();
			}
		});
		
	}
	
	

}
