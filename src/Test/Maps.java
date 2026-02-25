package Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Maps {
	public enum Appreciation {EXCELLENT,TRES_BIEN,BIEN,PASSABLE,MAUVAIS,HORRIBLE;}
	static Map<Parchemin,List <Appreciation>> parcheminNotee = new HashMap<>();
	
	public static Parchemin ajouterParchemin (String titre, Student auteur, Date date) {
		Parchemin parchemin = new Parchemin (titre,auteur,date);
		if (!parcheminNotee.containsKey(parchemin)) {
			parcheminNotee.put(parchemin,new ArrayList<Appreciation>() );
		}
		return parchemin;
	}
	
	public static List<Appreciation> ajouterAppreciation (Parchemin parchemin,Appreciation appreciation){
		List<Appreciation> note = new ArrayList<Appreciation>();
		if (parcheminNotee.containsKey(parchemin))
			note = parcheminNotee.get(parchemin);
		note.add(appreciation);
		parcheminNotee.put(parchemin, note);
		return note;
	}
	
	public static void main(String a[]) {
		Parchemin parchemin = ajouterParchemin("1984", new Student(45,"George"),new Date(12,12,1983));
		ajouterAppreciation(parchemin,Appreciation.EXCELLENT);
		Parchemin parchemin2= ajouterParchemin("Brave New World", new Student(33,"Bob"),new Date(13,9,1984));
		ajouterAppreciation(parchemin2,Appreciation.HORRIBLE);
		ajouterAppreciation(parchemin2,Appreciation.TRES_BIEN);
		System.out.println(parcheminNotee.entrySet());
		
	}
}
