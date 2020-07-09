package KMKTeam2;

import java.util.Scanner;
public class PCCA_EVERY_MINUTE_COUNTS {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		int numCases = scan.nextInt();
		
		for (int i = 0; i < numCases; i ++ ) {
			
			int rawMinutes = scan.nextInt();
			
			int minutes = rawMinutes % 60;
			//rawHours: Unprocessed hours that might be larger than 24 hours
			int rawHours = rawMinutes / 60;
			int hours = rawHours % 24;
			int days = rawHours / 24;
			
			System.out.printf("Case #%d: %d d %d h %d m ", (i + 1), days, hours, minutes);
			System.out.println("");
			
		}
		
		
		
	}		//end of main()

}		//end of class
