package Test;

public class Game {
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
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Game game) {
			return this.name.equals(game.getName());
		}
		return false;
	}
	
	public static void main(String[] args) {
		Game gmod = new Game("gmod",new Student(21,"Oliver"),2008);
		Game gta6 = new Game("GTAVI",new Student(73,"Bob"),2067);
		System.out.println(gmod.equals(gta6));
	}

}
