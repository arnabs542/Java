//package KMKTeam2;

import java.util.Scanner;
import java.text.DecimalFormat;
public class Q2C_TELCO {

	public static void main(String[]args) {
		
		Scanner scan = new Scanner(System.in);
		DecimalFormat formatter = new DecimalFormat("0.00");
		
		int numCases = scan.nextInt();
		
		for (int i = 0; i < numCases; i ++ ) {
			
			char service = scan.next().toLowerCase().charAt(0);
			int normalUsage = scan.nextInt();
			int nightUsage = 0;
			if (service == 'p')
				nightUsage = scan.nextInt();
			
			double fee = 0;
			if (service == 's') {
				if (normalUsage < 50)
					normalUsage = 50;
				fee = 38 + (normalUsage - 50) * 0.2;
			}
			//For some reason, the RM48 charge seems to charge double for both night session and day session
			else if (service == 'p') {
				if (normalUsage < 75)
					normalUsage = 75;
				if (nightUsage < 100)
					nightUsage = 100;
				fee = 96 + (normalUsage - 75) * 0.1 + (nightUsage - 100) * 0.05;
			}
			
			
			System.out.println("Case #" + (i + 1) + ": RM" + formatter.format(fee));
		}
		
	}		//end of main()
	
	
	
}		//end of class
