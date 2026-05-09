package Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Bibliothèque1 implements Iterator<Parchemin>{
	private int size;
	private Parchemin[] parchemins;
	private int nbParch;
	private int indiceIt = 0;
	private boolean nextEffectue=false;

	public Bibliothèque1(int size) {
		this.size = size;
		this.parchemins = new Parchemin[size];
	}
	
	public void ajouterParchemin(Parchemin parchemin) {
		if (nbParch<size) {
			parchemins[nbParch]=parchemin;
			nbParch++;
		} else {
			throw new IllegalStateException("Bibliothèque pleine");
		}
	}

	@Override
	public boolean hasNext() {
		return indiceIt<nbParch;
	}

	@Override
	public Parchemin next() {
		if (hasNext()) {
			Parchemin current = parchemins[indiceIt];
			indiceIt++;
			nextEffectue=true;
			return current;
		} else {
			throw new NoSuchElementException();
		}
	}
	
	public void remove() {
		if (nbParch<1||!nextEffectue) {
			throw new IllegalStateException();
		}
		for (int i = indiceIt-1; i < nbParch-1;i++) {
			parchemins[i]=parchemins[i+1];
		}
		nextEffectue = false;
		indiceIt--;
		nbParch--;
	}
	
	public void reset() {
	    indiceIt = 0;
	    nextEffectue = false;
	}
	
	
	public static void main(String[] args) {
		Bibliothèque1 biblio = new Bibliothèque1(15);
		biblio.ajouterParchemin(new Parchemin("Mein",new Student(3,"Hitlerette"),new Date(13,7,1945)));
		biblio.ajouterParchemin(new Parchemin("1984",new Student(3,"Jojo Oh Well"),new Date(6,7,1983)));
		biblio.ajouterParchemin(new Parchemin("Use of Weapons",new Student(3,"Philipp K dong"),new Date(19,9,1989)));
		while (biblio.hasNext()) {
			Parchemin current = biblio.next();
			System.out.println(current.toString());
		}
		biblio.reset();
		biblio.next();
		biblio.remove();
		while (biblio.hasNext()) {
			Parchemin current = biblio.next();
			System.out.println(current.toString());
		}
	}

}
