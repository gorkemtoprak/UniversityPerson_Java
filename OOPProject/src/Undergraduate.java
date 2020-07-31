// AUTHOR: GORKEM TOPRAK

public class Undergraduate extends Student{

	public static final byte typeID = 0;
	
	
	public Undergraduate(String name, String surname, String major) {
		super(name, surname, major);
		super.setTypeID(Undergraduate.typeID);
	}


	@Override
	public String toString() {
		return "Undergraduate [major=" + major + ", name=" + name + ", surname=" + surname + ", id=" + id + "]";
	}

	

}
