//package KMKTeam2;

import java.util.Scanner;
public class Q2H_ISDN_10_CHECK_DIGIT {

	public static void main(String[]args) {
		
		Scanner scan = new Scanner(System.in);
		
		int numCases = scan.nextInt();
		
		for (int i = 0; i < numCases; i ++ ) {
			
			int[] iSBNCode = new int[9];
			int sum = 0;
			for (int j = 0; j < 9; j++ ) {
				iSBNCode[j] = scan.nextInt();
				sum += iSBNCode[j] * (10 - j);
			}
			
			int tenDigitCode = ( 11 - (sum % 11) )% 11;
			
			System.out.println("Case #" + (i + 1) + ": " + tenDigitCode);
		}
		
		
	}		//end of main()
	
	
	
}		//end of class
