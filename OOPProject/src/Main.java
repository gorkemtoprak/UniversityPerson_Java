import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

// AUTHOR: GORKEM TOPRAK

public class Main {

	public static FileInputStream fis;

	public static void main(String[] args) {


		Student stuPerson = new Student("gorkem", "toprak", "ceng");
		stuPerson.write();
		/*
		Undergraduate undergradPerson = new Undergraduate("gorkem", "toprak", "cs");
		undergradPerson.write();
		
		Staff staffPerson = new Staff("volkan", "ozer", "security");
		staffPerson.write();

		Faculty facPerson = new Faculty("mert", "toprak", "0x45a1");
		facPerson.write();
		*/
		List<UniversityPerson> people = new ArrayList<UniversityPerson>();

		try {
			if (login() == true) {
				people = readAllDate(people);
				System.out.println(" ");
				System.out.println("File contents: ");	
				System.out.println("***************************************************************");
				for (UniversityPerson universityPerson : people) {
					System.out.println(universityPerson.toString());
				}

				modifyList(people);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	

	public static void modifyList(List<UniversityPerson> list) {
		Scanner input = new Scanner(System.in);
		System.out.println("***************************************************************\n");
		int modifyChoice;
		do {
			System.out.println("(1) Add UP, (2) Delete UP, (3) Update UP, (4) Search UP, (5) Exit to Save");
			modifyChoice = input.nextInt();

			switch (modifyChoice) {
			case 1:
				System.out.println("(1) Add Undergraduate, (2) Add Graduate, (3) Add Faculty, (4) Add Staff");
				int personType = input.nextInt();

				if (personType == 1) {
					System.out.println("Enter name: ");
					String name = input.next();
					System.out.println("Enter surname: ");
					String surname = input.next();
					System.out.println("Enter major: ");
					String major = input.next();

					UniversityPerson undergradPerson = new Undergraduate(name, surname, major);
					list.add(undergradPerson);

					// undergradPerson.write();
				} else if (personType == 2) {
					System.out.println("Enter name: ");
					String name = input.next();
					System.out.println("Enter surname: ");
					String surname = input.next();
					System.out.println("Enter major: ");
					String major = input.next();

					UniversityPerson gradPerson = new Graduate(name, surname, major);
					list.add(gradPerson);

					// gradPerson.write();
				} else if (personType == 3) {
					System.out.println("Enter name: ");
					String name = input.next();
					System.out.println("Enter surname: ");
					String surname = input.next();
					System.out.println("Enter office number: ");
					String officeNo = input.next();

					UniversityPerson facPerson = new Faculty(name, surname, officeNo);
					list.add(facPerson);

					// facPerson.write();
				} else if (personType == 4) {
					System.out.println("Enter name: ");
					String name = input.next();
					System.out.println("Enter surname: ");
					String surname = input.next();
					System.out.println("Enter job type: ");
					String jobTyoe = input.next();

					UniversityPerson staffPerson = new Staff(name, surname, jobTyoe);
					list.add(staffPerson);

					// staffPerson.write();
				}
				break;
				
			case 2:
				System.out.println("Enter the surname of the person to be deleted: ");
				String surname = input.next();
				for (Iterator<UniversityPerson> iterator = list.iterator(); iterator.hasNext(); ) {
					UniversityPerson value = iterator.next();
				    if (value.getSurname().trim().equals(surname.trim())) {
				        iterator.remove();
						System.out.println("Person deleted. [" + value.toString() + "]");
				    }
				}
				System.out.println(" ");
				break;
				
			case 3:
				System.out.println("Enter the surname of the person to be updated: ");
				String surnameUpdate = input.next();
				System.out.println("Enter the new surname: ");
				String newSurname = input.next();
				for (UniversityPerson universityPerson : list) {
					if (universityPerson.getSurname().trim().equals(surnameUpdate.trim())) {
						universityPerson.setSurname(newSurname);
						System.out.println("Surname updated. [" + universityPerson.toString() + "]");
					}
				}
				System.out.println(" ");
				break;
				
			case 4:
				System.out.println("Enter the surname of the person to be searched: ");
				String surnameSearch = input.next();
				for (UniversityPerson universityPerson : list) {
					if (universityPerson.getSurname().trim().equals(surnameSearch.trim())) {
						System.out.println("Person found..: [" + universityPerson.toString() + "]");
						universityPerson.toString();
					}
				}
				System.out.println(" ");
				break;
				
			case 5:
				try {
					PrintWriter pw = new PrintWriter("university_persons.bin");
					pw.close();
					System.out.println("New content to write to the file...");
					System.out.println("***************************************************************");
					for (UniversityPerson universityPerson : list) {
						System.out.println(universityPerson.toString());
						universityPerson.write();
					}

				} catch (IOException e) {
					e.printStackTrace();
				}
				System.out.println("***************************************************************\n");
				System.out.println("Program exited.");
				break;
			default:
				System.out.println("Invalid option.");
				break;
			}

		} while (modifyChoice != 5);

	}

	public static boolean login() throws IOException {
		boolean isLogIn = true;

		Scanner input = new Scanner(System.in);

		int signInChoice;
		String username, password, hashedPassword;
		System.out.println("\t-----WELCOME-----");
		System.out.println("*******************************************");
		System.out.println("Please Click 1 to log in, 2 to log out, and 3 to log out. \n");

		do {
			System.out.println("(1) Register, (2) Login, (3) Exit");
			signInChoice = input.nextInt();

			switch (signInChoice) {
			case 1:
	
				System.out.println("Enter a new username: ");
				username = input.next();
				System.out.println("Enter a new password: ");
				password = input.next();

				hashedPassword = sha256(password);
				writeToUsersFile(username, hashedPassword);
				System.out.println("*******************************************");
				break;
				
			case 2:
				System.out.println("Enter your username: ");
				username = input.next();
				System.out.println("Enter your password: ");
				password = input.next();

				hashedPassword = sha256(password);

				File file = new File("users.txt");
				Scanner sc_file = new Scanner(file);
				while (sc_file.hasNextLine()) {
					String line = sc_file.nextLine();
					String[] split = line.split("#");
					if (username.equals(split[0])) {
						if (hashedPassword.equals(split[1])) {
							System.out.println("Login successful.");
							isLogIn = true;
						}
					}
				}

				if (isLogIn) {
					return isLogIn;
				}
				break;
			case 3:
				System.out.println("Program exited.");
				break;
			default:
				System.out.println("Invalid option");
				// break;
			}

		} while (signInChoice != 3);

		return isLogIn;
	}

	public static void writeToUsersFile(String username, String password) throws IOException {
		PrintWriter pw = new PrintWriter(new FileOutputStream(new File("users.txt"), true));

		pw.printf("%s" + "%n", username + "#" + password);
		pw.close();
	}

	// Sha256 Hashing from google
	public static String sha256(String base) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(base.getBytes("UTF-8"));
			StringBuffer hexString = new StringBuffer();

			for (int i = 0; i < hash.length; i++) {
				String hex = Integer.toHexString(0xff & hash[i]);
				if (hex.length() == 1)
					hexString.append('0');
				hexString.append(hex);
			}

			return hexString.toString();

		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	public static List<UniversityPerson> readAllDate(List<UniversityPerson> list) {
		try {
			UniversityPerson[] person = { new Undergraduate("", "", ""), new Graduate("", "", ""),
					new Faculty("", "", ""), new Staff("", "", "") };

			fis = new FileInputStream("university_persons.bin");

			byte firstByte = (byte) fis.read();

			// System.out.println("firstByte " + firstByte);

			while (firstByte != -1) {
				// System.out.println("1--" + firstByte);
				UniversityPerson up = person[firstByte].read();

				list.add(up);

				firstByte = (byte) fis.read();
				// System.out.println("2--" +firstByte);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// close the streams using close method
			try {
				if (fis != null) {
					fis.close();
				}
			} catch (IOException ioe) {
				System.out.println("Error while closing stream: " + ioe);
			}
		}
		return list;
	}

}
