package Test;

import java.util.*;

import Test.Merchant.Speciality;

public class Products implements Comparable<Products> {
	private String name;
	private int price;
	public enum Rarity {COMMON,RARE,EPIC;}
	private Rarity rarity;
	public Products(String name, int price,Rarity rarity) {
		this.name=name;
		this.price=price;
		this.rarity = rarity;
	}
	
	public int getPrice() {
		return this.price;
	}
	
	public String getName() {
		return this.name;
	}
	
	public Rarity getRarity() {
		return this.rarity;
	}
	
	public boolean equals (Object obj) {
		if (obj != null && getClass() == obj.getClass()) {
			Products prod = (Products) obj;
			if (name.equals(prod.name)&&(price==prod.price)&&(rarity==prod.rarity)) {
				return true;
			}
		}
		return false;
	}
	
	public int compareTo (Products prod) {
		if (price == prod.price) {
			return name.compareTo(prod.name);
		}
		if (price>prod.price)
			return 1;
		return -1;
	}
	
	public static void main (String a[]) {
		TreeSet<Products> products = new TreeSet<Products>();
		Products oil = new Products("oil",100,Rarity.RARE);
		Products water = new Products("water",20,Rarity.COMMON);
		products.add(oil);
		products.add(water);
		Merchant mercad = new Merchant(1,"mario",Speciality.FOOD);
		
		HashMap<Merchant,TreeSet<Products>> merkat = new LinkedHashMap<Merchant,TreeSet<Products>>();
		merkat.put(mercad,products);
	}
	
}
