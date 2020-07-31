import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;

//AUTHOR: GORKEM TOPRAK

public class Student extends UniversityPerson {
	
	public String major;
	private static byte typeID;

	public Student(String name, String surname, String major) {
		super(name, surname);
		this.major = major;
	}

	@Override
	public String toString() {
		return "Student [major=" + major + ", name=" + name + ", surname=" + surname + ", id=" + id + "]";
	}

	@Override
	public Student read() {
		
		Student std = null;

		try {
			byte secondByte = (byte) Main.fis.read(); // read second byte, length of name

			Integer second = new Integer(secondByte); // int name length

			byte allBytes[] = new byte[second + 1]; // read length of name byte
			Main.fis.read(allBytes);

			String third = new String(allBytes); // str name

			byte byte4 = (byte) Main.fis.read(); // read length of surname

			Integer b4 = new Integer(byte4); // read length of surname byte

			byte allBytes2[] = new byte[b4 + 1];
			Main.fis.read(allBytes2);

			String b5 = new String(allBytes2); // str surname

			byte byte6 = (byte) Main.fis.read();

			Integer b6 = new Integer(byte6);

			byte allBytes3[] = new byte[b6 + 1];
			Main.fis.read(allBytes3);

			String b7 = new String(allBytes3); // str third variable

		
			
			std = new Student(third, b5, b7);
			
		} catch (EOFException e) {
			System.out.println("EOF");
		} catch (FileNotFoundException e) {
			System.out.println("File not found" + e);
		} catch (IOException ioe) {
			System.out.println("Exception while reading file " + ioe);
		} 
		return std;
	}

	@Override
	public void write() {
		try {
			//FileOutputStream output_file = new FileOutputStream("university_persons.bin", true);

			UniversityPerson.output_file.write(getTypeID());

			int name_length = getName().length();
			UniversityPerson.output_file.write(name_length);
			UniversityPerson.output_file.write(name_length >> 8);
			UniversityPerson.output_file.write(getName().getBytes());

			int surname_length = getSurname().length();
			UniversityPerson.output_file.write(surname_length);
			UniversityPerson.output_file.write(surname_length >> 8);
			UniversityPerson.output_file.write(getSurname().getBytes());

			int majorlength = getMajor().length();
			UniversityPerson.output_file.write(majorlength);
			UniversityPerson.output_file.write(majorlength >> 8);
			UniversityPerson.output_file.write(getMajor().getBytes());

			//output_file.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public static byte getTypeID() {
		return typeID;
	}

	public static void setTypeID(byte typeID) {
		Student.typeID = typeID;
	}
	

}
