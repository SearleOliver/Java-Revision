package Test;

import java.util.*;

public class Bibliotheque {

    private TreeMap<Student, TreeSet<Parchemin>> contenu;

    public Bibliotheque() {
        contenu = new TreeMap<>();
    }

    public void ajouterParchemin(Student auteur, Parchemin parchemin) {
        contenu.putIfAbsent(auteur, new TreeSet<>());
        contenu.get(auteur).add(parchemin);
    }

    /**
     * Renvoie la bibliothèque sous forme :
     * Auteur :
     *    (date) titre
     *    (date) titre
     * Auteur :
     *    ...
     */
    public String donnerParcheminsParAuteur() {
        StringBuilder sb = new StringBuilder();

        for (Map.Entry<Student, TreeSet<Parchemin>> entry : contenu.entrySet()) {
            Student auteur = entry.getKey();
            TreeSet<Parchemin> parchemins = entry.getValue();

            sb.append(auteur).append(" :\n");
            for (Parchemin p : parchemins) {
                sb.append("   - ").append(p).append("\n");
            }
        }

        return sb.toString();
    }
    
    public static void main (String a[]) {
    	Bibliotheque biblio = new Bibliotheque();
    	Student max = new Student(33,"Maxime");
    	Student marx = new Student(47,"Karl Marx");
    	Student bob = new Student(63,"Sally");
    	biblio.ajouterParchemin(bob,new Parchemin("dogs",bob, new Date(10,9,2023)));
    	biblio.ajouterParchemin(max,new Parchemin("Boring book",max, new Date(12,12,2012)));
    	biblio.ajouterParchemin(marx,new Parchemin("Marxism",marx, new Date(11,11,1988)));
    	biblio.ajouterParchemin(bob,new Parchemin("Horses",bob, new Date(10,10,2025)));
    	System.out.println(biblio.donnerParcheminsParAuteur());
    	
    }
}
