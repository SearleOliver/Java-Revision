package Test;

import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.TreeSet;

public class Mediatheque implements Iterable<Film> {
	private Map<Abonné,TreeSet<Film>> notations = new HashMap<>();
	private Film[] films;
	private int capacité;
	private int globalOp=0;
	private int numFilms=0;

	public Mediatheque(int capacité) {
		this.capacité=capacité;
		this.films = new Film[capacité];
	}
	
	public void ajouterFilm(Film film) {
		if (numFilms<capacité) {
			films[numFilms]=film;
			globalOp++;
		} else {
			throw new IllegalStateException();
		}
	}
	
	public void ajouterNotation(Abonné abonné, Film film) {
		if (!notations.containsKey(abonné)) {
			notations.put(abonné, new TreeSet<Film>());
		}
		notations.get(abonné).add(film);
	}
	
	public void supprimerFilm(String titre, int année) {
		Film delete = new Film(titre,année,"",0.0);
		for(TreeSet<Film> films : notations.values()) {
			for(Iterator<Film> it = films.iterator();it.hasNext();) {
				Film current = it.next();
				if (current.equals(delete)) {
					it.remove();
				}
			}
		}
	}
	
	public void afficherToutesNotations() {
		for (Map.Entry<Abonné,TreeSet<Film>> entry : notations.entrySet()) {
			System.out.println("Les films de "+entry.getKey().getNom()+" : ");
			for (Film film : entry.getValue()) {
				System.out.println(film.getTitre());
			}
		}
	}

	@Override
	public Iterator<Film> iterator() { return new Iterateur();}
	
	private class Iterateur implements Iterator<Film>{
		private int localOp = globalOp;
		private int index= 0;
		private boolean nextDone=false;
		
		public void check() {
			if (localOp!=globalOp) {
				throw new ConcurrentModificationException();
			}
		}
		
		@Override
		public boolean hasNext() {
			return index<numFilms;
		}
		
		@Override
		public Film next() {
			check();
			if (index<numFilms) {
				Film film = films[index];
				nextDone=true;
				index++;
				return film;
			} else {
				throw new NoSuchElementException();
			}
		}
		public void remove() {
			check();
			if (index>1&&nextDone==true) {
				for (int i = index-1;i<numFilms-1;i++) {
					films[i]=films[i+1];
				}
				index--;
				globalOp++;
				localOp++;
				numFilms--;
				nextDone=false;
			} else {
				throw new IllegalStateException();
			}
		}
	}

}
