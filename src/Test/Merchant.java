package Test;

import java.util.*;


public class Merchant	{
	public enum Speciality {FOOD,WEAPONS,FABRICS,POTIONS;}
	private int id;
	private String name;
	private Speciality speciality;
	
	public Merchant(int id,String name,Speciality speciality) {
		this.id = id;
		this.name = name;
		this.speciality = speciality;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj!=null&&(getClass()==obj.getClass())) {
			Merchant merc = (Merchant) obj;
			if (id==merc.id&&name.equals(merc.name)&&speciality==merc.speciality)
				return true;
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return 31*(id+name.hashCode()+speciality.hashCode());
	}
	
	public static void main (String a[]) {
		HashSet<Merchant> merchants = new LinkedHashSet<Merchant>();
		Merchant mercad = new Merchant(1,"mario",Speciality.FOOD);
		Merchant mercado = new Merchant(2,"luigi",Speciality.WEAPONS);
		merchants.add(mercad);
		merchants.add(mercado);
	}

	public int getId() {
		return this.id;
	}
}
