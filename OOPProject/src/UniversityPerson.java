import java.io.FileNotFoundException;
import java.io.FileOutputStream;

//AUTHOR: GORKEM TOPRAK

public abstract class UniversityPerson {
	
	public static int idAll = 1;
	public static FileOutputStream output_file;


	public String name;
	public String surname;
	public int id;
	
	
	public UniversityPerson() {
	}

	public UniversityPerson(String name, String surname) {
		this.name = name;
		this.surname = surname;
		this.id = idAll++;
		try {
			output_file = new FileOutputStream("university_persons.bin", true);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static boolean search(String lastName) {

		return true;
	}
	
	public abstract UniversityPerson read();
	
	public abstract void write();
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "UniversityPerson [name=" + name + ", surname=" + surname + ", id=" + id + "]";
	}

	
}
