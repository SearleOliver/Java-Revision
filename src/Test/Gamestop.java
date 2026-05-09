package Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Gamestop {
	private int numGames;
	private int capacity;
	private Game[] games;
	private int numOp;

	public Gamestop(int capacity) {
		this.capacity=capacity;
		this.games = new Game[capacity];
	}
	
	public boolean addGame(Game game) {
		boolean added = numGames<capacity;
		if (added) {
			games[numGames]=game;
			numGames++;
			numOp++;
		}
		return added;
	}
	
	public boolean removeGame(Game game) {
		boolean removed = false;
		for (int i =0; i < numGames-1;i++) {
			if (games[i].equals(game)) {
				for (int j =i; j < numGames-1;j++) {
					games[j]=games[j+1];
				}
				removed=true;
				numOp++;
				numGames--;
			}
		}
		return removed;
	}
	
	public Iterator<Game> iterator(){return new iterateur();}
	
	private class iterateur implements Iterator<Game>{
		private int numOpRef = numOp;
		private int indexIt = 0;
		private boolean nextDone =false;

		@Override
		public boolean hasNext() {
			return indexIt<numGames;
		}
		
		private void verifyConcurrence() {
			if (numOp != numOpRef) {
				throw new ConcurrentModificationException();
			}
		}

		@Override
		public Game next() {
			verifyConcurrence();
			if (hasNext()) {
				Game current = games[indexIt];
				indexIt++;
				nextDone=true;
				return current;
			}
			throw new NoSuchElementException();
		}
		
		public void remove() {
			verifyConcurrence();
			if (numGames<1||!nextDone) {
				throw new IllegalStateException();
			}
			for (int i = indexIt-1; i < numGames-1;i++) {
				games[i]=games[i+1];
			}
			numOp++;
			numOpRef++;
			numGames--;
			nextDone=false;
		}
		
		public static void main(String args[]) {
			Gamestop gs = new Gamestop(17);
			Game gmod = new Game("gmod",new Student(21,"Oliver"),2008);
			Game gta6 = new Game("GTAVI",new Student(73,"Bob"),2067);
			gs.addGame(gmod); gs.addGame(gta6);
			for (Iterator<Game> it = gs.iterator();it.hasNext();) {
				
			}
			
		}
		
	}

}
