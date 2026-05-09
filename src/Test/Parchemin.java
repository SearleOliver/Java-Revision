package Test;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

class Date implements Comparable<Date> {
	private int day;
	private int month;
	private int year;
	
	public Date(int day, int month, int year) {
		this.day = day;
		this.month = month;
		this.year = year;
	}
	
	public String getDate() {
		return this.day+"/"+this.month+"/"+this.year;
	}
	

	
	@Override
    public int compareTo(Date other) {
        if (this.year != other.year)
            return Integer.compare(this.year, other.year);
        if (this.month != other.month)
            return Integer.compare(this.month, other.month);
        return Integer.compare(this.day, other.day);
    }

    @Override
    public int hashCode() {
        return 31*(day+month+year);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Date d = (Date) obj;
        return day == d.day && month == d.month && year == d.year;
    }
    
}

public class Parchemin implements Comparable<Parchemin> {

	private String titre;
    private Student auteur;
    private Date date;

    public Parchemin(String titre, Student auteur, Date date) {
        this.titre = titre;
        this.auteur = auteur;
        this.date = date;
    }
    
	public String getTitre() {
		return this.titre;
	}
    public Student getAuteur() {
    	return this.auteur;
    }
    
    @Override
    public String toString() {
    	return titre + ", " + auteur.getName() + ", " + date.getDate();
    }
    
    @Override
    public boolean equals(Object obj) {
    	if (obj instanceof Parchemin parchemin) {
    		Parchemin parch = (Parchemin) obj;
    		return date.equals(parch.date)&&titre.equals(parch.titre)&&auteur.equals(parch.auteur);
    	}
    	return false;
    }
    
    @Override
    public int compareTo(Parchemin other) {
        int cmp = this.date.compareTo(other.date);
        if (cmp != 0) return cmp;

        cmp = this.titre.compareTo(other.titre);
        if (cmp != 0) return cmp;

        return this.auteur.toString().compareTo(other.auteur.toString());
    }
    
    public void rendreParchemin (String name, Parchemin ...parchemins) {
    	for (Parchemin parchemin : parchemins) {
    		System.out.println(name+" a rendu "+parchemin.titre);
    	}
    }
    
    
    public int hashCode () {
    	return 31*(titre.hashCode()+auteur.hashCode());
    }
    
    public static String lented(Set<Parchemin> lent) {
    	return "Have we lent ? " + (lent.size()!=0);
    }
    
    
    public static void main (String a[]) {
    	Set<Parchemin> lib = new HashSet<>();
    	Parchemin parch1 = new Parchemin("Mein",new Student(3,"Hitlerette"),new Date(13,7,1945));
    	Parchemin parch2 = new Parchemin("Marc",new Student(99,"Marx"),new Date(12,12,1999));
    	Parchemin parch3 = new Parchemin("1984",new Student(22,"Jorj oh well"),new Date(1,1,1983));
    	lib.add(parch1);lib.add(parch2);lib.add(parch3);
    	System.out.println(lib);
    	Set<Parchemin> lend = new HashSet<>(lib);
    	Set<Parchemin> lent = new HashSet<>();
    	Parchemin parch4 = new Parchemin("drawings",new Student(4,"Kevin"),new Date (2,7,1999));
//    	lib.add(parch4);
    	System.out.println(lented(lent));
    	lend.remove(parch1);
    	lent.addAll(lib);
    	lent.removeAll(lend);
    	System.out.println(lent);
    	System.out.println(lented(lent));
    	System.out.println(parch1.hashCode());
    	System.out.println(lent.hashCode());
    	Set<Parchemin> tree = new TreeSet<Parchemin>();
    	
    	
    	 Set<Parchemin> ensembleTrie = new TreeSet<>();

         ensembleTrie.add(new Parchemin("De Bello Gallico", new Student(2,"Carl"), new Date(1, 3, -52)));
         ensembleTrie.add(new Parchemin("La résistance gauloise", new Student(24,"Fred"), new Date(15, 7, -51)));
         ensembleTrie.add(new Parchemin("Stratégies romaines", new Student(39,"Bella"), new Date(10, 1, -49)));

         System.out.println("=== ensembleTrie (tri natural order) ===");
         ensembleTrie.forEach(System.out::println);
    	
         
         Set<Parchemin> ensembleLie = new LinkedHashSet<>();
         ensembleLie.addAll(ensembleTrie);

         // Ajout du parchemin d’Abraracourcix
         Parchemin nouveau =
                 new Parchemin("L'art de diriger", new Student(99,"Dead"), new Date(1, 9, -48));

         ensembleLie.add(nouveau);

         System.out.println("\n=== ensembleLie (ordre d’insertion) ===");
         ensembleLie.forEach(System.out::println);

         System.out.println("\nLe parchemin d'Abraracourcix est bien en DERNIÈRE position.");
         rendreParchemin(new Student(13,"Kim"),new Parchemin("1",new Student(23,"Bobby"),new Date(12,12,2023)),new Parchemin("2",new Student(23,"Greg"),new Date(12,12,2022)));
    }
}
