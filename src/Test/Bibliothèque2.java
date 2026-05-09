package Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Bibliothèque2 implements Iterable<Parchemin>{
	private int size;
	private Parchemin[] parchemins;
	private int nbParch;
	private int nombreOp =0;

	public Bibliothèque2(int size) {
		this.size = size;
		this.parchemins = new Parchemin[size];
	}
	
	public boolean ajouterParchemin(Parchemin parchemin) {
		boolean ajout = nbParch<size;
		if (ajout) {
			parchemins[nbParch]=parchemin;
			nbParch++;
			nombreOp++;
		}
		return ajout;

	}

	public boolean enleverParchemin(Parchemin parchemin) {
		boolean removed = false;
		for (int i =0 ; i< nbParch && !removed ;i++) {
			if (parchemins[i].equals(parchemin)) {
				for (int j = i; i<nbParch-1;j++) {
					parchemins[j]=parchemins[j+1];
				}
				nbParch--;
				nombreOp++;
				removed = true;
			}
		}
		return removed;
	}
	
	public Iterator<Parchemin> iterator(){return new Iterateur();}
	
	private class Iterateur implements Iterator<Parchemin>{
		private int indiceIt = 0;
		private boolean nextEffectue = false;
		private int nombreOpRef = nombreOp;
		
		private void verificationConcurrence() {
			if (nombreOp != nombreOpRef) {
				throw new ConcurrentModificationException();
			}
		}

		@Override
		public boolean hasNext() {
			return indiceIt<nbParch;
		}

		@Override
		public Parchemin next() {
			verificationConcurrence();
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
			verificationConcurrence();
			if (nbParch<1||!nextEffectue) {
				throw new IllegalStateException();
			}
			for (int i = indiceIt-1; i <nbParch-1;i++) {
				parchemins[i]=parchemins[i+1];
			}
			nbParch--;
			nombreOp++;
			nombreOpRef++;
			nextEffectue=false;
		}
		
	}
	
	public static void main(String[] args) {
		Bibliothèque2 biblio = new Bibliothèque2(15);
		biblio.ajouterParchemin(new Parchemin("Mein",new Student(3,"Hitlerette"),new Date(13,7,1945)));
		biblio.ajouterParchemin(new Parchemin("1984",new Student(3,"Jojo Oh Well"),new Date(6,7,1983)));
		biblio.ajouterParchemin(new Parchemin("Use of Weapons",new Student(3,"Philipp K dong"),new Date(19,9,1989)));
		Iterator<Parchemin> it = biblio.iterator();
		while (it.hasNext()) {
		    System.out.println(it.next());
		}
		
		for (Parchemin parchemin : biblio) {
			System.out.println(parchemin.toString());
		}
		
		for (Iterator<Parchemin> iter = biblio.iterator();iter.hasNext();) {
			Parchemin current = iter.next();
			if (current.getTitre()=="1984") {
				iter.remove();
			}
		}
		for (Parchemin parchemin : biblio) {
			System.out.println(parchemin.toString());
		}

	}

}
