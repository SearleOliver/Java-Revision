package Test;

public class Game implements Comparable<Game>{
	private String name;
	private Student creator;
	private int year;

	public Game(String name, Student creator, int year) {
		this.name=name;
		this.creator=creator;
		this.year=year;
	}
	
	public int getYear() {
		return this.year;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String toString() {
		return ("game : "+this.name+" creator : "+this.creator.getName()+" year : "+ this.getYear()+"\n");
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Game game) {
			return this.name.equals(game.getName())&& this.year==game.getYear();
		}
		return false;
	}
	
	@Override
	public int compareTo (Game game) {
		int comparison = this.name.compareTo(game.getName());
		if (comparison == 0) {
			comparison = this.year-game.getYear();
		}
		return comparison;
	}
	
	@Override
	public int hashCode() {
		return 31*this.name.hashCode()*this.year;
	}
	
	public static void main(String[] args) {
		Game gmod = new Game("gmod",new Student(21,"Oliver"),2008);
		Game gta6 = new Game("GTAVI",new Student(73,"Bob"),2067);
		System.out.println(gmod.equals(gta6));
	}

}
