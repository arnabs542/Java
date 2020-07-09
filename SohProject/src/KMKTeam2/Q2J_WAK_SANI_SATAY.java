//package KMKTeam2;

import java.util.Scanner;
import java.text.DecimalFormat;
public class Q2J_WAK_SANI_SATAY {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		DecimalFormat formatter = new DecimalFormat("0.00");
		
		/* LOGIC: The question may seems complicated but after checking, we just have to find out how much each stick of satay
		 * 		  earns without having to worry about leftover meat or spoilage stuff
		 * 		  Thus the constants below is calculated profit per stick of satay by using
		 * 		  (Price per stick) - ( Marinate + cost ) / 85
		 */		 
		final double CHICKEN_PROFIT = 21.0 / 34.0;
		final double BEEF_PROFIT = 53.0 / 85.0;
		final double LAMB_PROFIT = 62.0 / 85.0;
		final double NASI_PROFIT = 0.6;
		
		int numDays = scan.nextInt();
		
		for (int caseNum = 1; numDays != 0; caseNum ++ ) {
			
			double weeklyProfit = 0;
			
			for (int i = 0; i < numDays; i ++ ) {
				int chickenSold = scan.nextInt();
				int beefSold = scan.nextInt();
				int lambSold = scan.nextInt();
				int nasiSold = scan.nextInt();
				
				weeklyProfit += (chickenSold * CHICKEN_PROFIT) + (beefSold * BEEF_PROFIT) + (lambSold * LAMB_PROFIT) 
								+ (nasiSold * NASI_PROFIT);
				
			}		//loop for each day in a week end here
			
			System.out.println("Case #" + (caseNum) + ": RM" + formatter.format(weeklyProfit));
			numDays = scan.nextInt();
			
		}		//loop for each case end here. Will repeat for new week
		
		
	}		//end of main()

}		//end of class
