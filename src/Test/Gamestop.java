package Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;
import java.util.NoSuchElementException;
import java.util.TreeMap;
import java.util.TreeSet;

public class Gamestop implements Iterable<Game>{
	private int numGames;
	private int capacity;
	private Game[] games;
	private int numOp;
	private List<Student> loaners = new ArrayList<>();
	private List<Game> shelf = new ArrayList<>();
	private NavigableSet<Game> tree = new TreeSet<>();
	private Map<Student,TreeSet<Game>> memberGames = new HashMap<>(); 
	private Map<Game,List<Grade>> gameRatings = new TreeMap<>();

	public Gamestop(int capacity) {
		this.capacity=capacity;
		this.games = new Game[capacity];
	}
	
	public Student addMember (String name, int age) {
		Student member = new Student(age,name);
		if (!memberGames.containsKey(member)) {
			memberGames.put(member, new TreeSet<Game>());
		}
		return member;
	}
	
	public void addToCollection (Student member,Game...games) {
		TreeSet<Game> collection;
		if (!memberGames.containsKey(member)) {
			collection = new TreeSet<>();
		} else {
			collection = memberGames.get(member);
		}
		for (Game game : games) {
			collection.add(game);
		}
		memberGames.put(member, collection);
	}
	
	public Game addGame (String name, int year,Student creator) {
		Game game = new Game(name,creator,year);
		if (!gameRatings.containsKey(game)) {
			gameRatings.put(game, new ArrayList<Grade>());
		}
		return game;
	}
	public void rateGame (Game game,Grade... grades) {
		List<Grade> collection;
		if (!gameRatings.containsKey(game)) {
			collection = new ArrayList<>();
		} else {
			collection = gameRatings.get(game);
		}
		for (Grade grade : grades) {
			collection.add(grade);
		}
		gameRatings.put(game, collection);
	}
	
	
	public enum Grade{ AMAZING,GREAT,GOOD,MEH,BAD,DISGUSTANG;}
	
	public void addLoaner(Student loaner) {
		if (!loaners.contains(loaner)) {
			loaners.add(loaner);
		}
	}
	
	public void shelfGame(Game game) {
		shelf.add(0,game);
	}
	
	public void reShelfGame(Game game) {
		shelf.add(game);
	}
	
	public Game remove(String title) {
		Game removed = null;
		Game copy =new Game(title,new Student(0,"g"),2000);
		if (shelf.contains(copy)) {
			int index = shelf.indexOf(copy);
			removed = shelf.remove(index);
		}
		return removed;
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
		for (int i =0; i < numGames;i++) {
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
			indexIt--;
			nextDone=false;
		}
		
		
	}
	
	public static void main(String[] args) {
		Gamestop gs = new Gamestop(17);
		Game gmod = new Game("gmod",new Student(21,"Oliver"),2008);
		Game gta6 = new Game("GTAVI",new Student(73,"Bob"),2067);
		Game gta5 = new Game("GTAV",new Student(45,"Greg"),2010);
		Game wt = new Game("WarThunder",new Student(14,"Ivan"),2014);
		Student oliver = new Student(23,"Oliver");
		Student clement = new Student(12,"Clement");
		Student antoine = new Student(22,"Antoine");
		gs.addGame(gmod); gs.addGame(gta6);
		
		gs.shelfGame(gmod);
		gs.shelfGame(gta6);
		gs.shelfGame(gta5);
		gs.reShelfGame(wt);
		for (Iterator<Game> it = gs.shelf.listIterator();it.hasNext();) {
			Game current = it.next();
			System.out.println(current.getName());
		}
		
		gs.tree.add(gmod);
		gs.tree.add(wt);
		gs.tree.add(gta6);
		gs.tree.add(gta5);
		for (Iterator<Game> it = gs.tree.iterator();it.hasNext();) {
			Game current = it.next();
			System.out.println(current.toString());
		}
		for(Game game : gs.tree) {
			System.out.println(game.toString());
		}
		
		NavigableSet<Game> treeImp = new TreeSet<>(new Comparator<Game>() {
			@Override
			public int compare(Game o1, Game o2) {
				return o1.getYear()-o2.getYear();
			}
		});
		
		treeImp.addAll(gs.tree);
		for(Game game : treeImp) {
			System.out.println(game.toString());
		}
		
		gs.addGame("plovdiv Sim",2003,new Student(76,"Tim"));
		gs.rateGame(gmod, Grade.GREAT, Grade.DISGUSTANG);
		gs.rateGame(gta5, Grade.MEH, Grade.GOOD);
		for (Map.Entry<Game,List<Grade>> entry : gs.gameRatings.entrySet()) {
			System.out.println(entry.getKey().toString()+" ratings = ");
			for (Grade grade : entry.getValue()) {
				System.out.println(grade);
			}
		}
		
	}
	
}
