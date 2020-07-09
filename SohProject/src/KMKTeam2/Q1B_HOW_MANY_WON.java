//package KMKTeam2;

import java.util.Scanner;
import java.text.DecimalFormat;
public class Q1B_HOW_MANY_WON {

	public static void main(String[]args) {
		
		Scanner scan = new Scanner(System.in);
		DecimalFormat formatter = new DecimalFormat("0.00");
		
		final double MYR_TO_KRW = 278.82;
		
		int numCases = scan.nextInt();
		
		for (int i = 0; i < numCases; i ++ ) {
			double rawMYR = scan.nextDouble();
			double convKRW = rawMYR * MYR_TO_KRW;
			
			System.out.println("Case #" + (i + 1) + ": " + formatter.format(convKRW) );
			
		}
		
	}		//end of main()
	
	
}		//end of class
