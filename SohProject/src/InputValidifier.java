import java.util.Scanner;
import java.util.regex.*;
public class InputValidifier {

	static String getName() {
		Scanner scan = new Scanner(System.in);
		Pattern regex = Pattern.compile("^[a-zA-Z][a-zA-Z\\s]{1,}");
		Matcher match;
		boolean isValid;
		String name;
		
		do {
			name = scan.nextLine();
			match = regex.matcher(name);
			isValid = match.matches();
			
			if (!isValid)
				System.out.println("Invalid name! Please input again:");
		} while (!isValid);
		
		return name;
		
	}		//end of getName() method
	
	static int getAge() {
		Scanner scan = new Scanner(System.in);
		int age;
		
		do {
			try {
				age = scan.nextInt();
				
				if (age <= 0)
					throw new Exception("Age less than or equal to 0!");
			}
			catch (Exception e) {
				System.out.println("Invalid age! Please try again!");
				age = -1;
			}
		} while (age == -1);
		
		return age;
	}		//end of getAge() method
	
	
	static String getPracClass() {
		Scanner scan = new Scanner(System.in);
		String pracClass = "";
		Pattern regex = Pattern.compile("[SFBP][1-9]T[1-9]");
		Matcher match;
		
		do {
			pracClass = scan.next();
			match = regex.matcher(pracClass);
			
			if (!match.matches()) {
				System.out.println("Invalid Practiculum Class! Please try again! ");
			}
			
		} while (!match.matches());
		
		return pracClass;
		
	}
	
}
