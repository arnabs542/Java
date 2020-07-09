//package KMKTeam2;

import java.util.Scanner;
import java.text.DecimalFormat;
public class Q2A_ZUCCHINI {
	
	public static void main(String[]args) {
		
		Scanner scan = new Scanner(System.in);
		DecimalFormat formatter = new DecimalFormat("0.00");
		
		int numCases = scan.nextInt();
		
		for (int i = 0; i < numCases; i ++ ) {
			
			int selection = scan.nextInt();
			int quantity = scan.nextInt();
			
			double pricePerUnit = 0;
			
			switch (selection) {
				case 1:	pricePerUnit = 7.5;
						break;
				case 2: pricePerUnit = 6.99;
						break;
				case 3: pricePerUnit = 4.7;
						break;
				case 4: pricePerUnit = 5.3;
						break;
				case 5: pricePerUnit = 5.99;
						break;
			}
			
			System.out.println("Case #" + (i + 1) + ": RM" + formatter.format( pricePerUnit * quantity ));
		}
		
		
		
		
	}		//end of main()
	
	
	
}		//end of class
