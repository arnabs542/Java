package KMKTeam2;

import java.util.Scanner;
public class Q2G_THE_DATE {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		int numCases = scan.nextInt();
		
		for (int i = 0; i < numCases; i ++ ) {
			int day = scan.nextInt();
			int month = scan.nextInt();
			int year = scan.nextInt();
			
			String monthWord = "";
			switch (month) {
				case 1: monthWord = "January";
						break;
				case 2: monthWord = "February";
						break;
				case 3: monthWord = "March";
						break;
				case 4: monthWord = "April";
						break;
				case 5: monthWord = "May";
						break;
				case 6: monthWord = "June";
						break;
				case 7: monthWord = "July";
						break;
				case 8: monthWord = "August";
						break;		
				case 9: monthWord = "September";
						break;
				case 10: monthWord = "October";
						break;
				case 11: monthWord = "November";
						break;
				case 12: monthWord = "December";
						break;		
			}
			
			System.out.println("Case #" + (i + 1) + ": " + day + " " + monthWord + " " + year);
			
		}
		
	}		//end of main()

}		//end of class
