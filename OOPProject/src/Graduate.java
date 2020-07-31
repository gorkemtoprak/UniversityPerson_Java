// AUTHOR: GORKEM TOPRAK

public class Graduate extends Student{
	
	public static final byte typeID = 1;

	public Graduate(String name, String surname, String major) {
		super(name, surname, major);
		super.setTypeID(Graduate.typeID);
	}

	@Override
	public String toString() {
		return "Graduate [major=" + major + ", name=" + name + ", surname=" + surname + ", id=" + id + "]";
	}
	
	
	
}
